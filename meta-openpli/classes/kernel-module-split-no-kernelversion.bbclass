# This revert commit kernel-module-split: Append KERNEL_VERSION string to kernel module name
# https://github.com/openembedded/openembedded-core/commit/78cde87bb6e71ec5b603426879267874900d09f3
# On fulan KERNEL_VERSION is 2.6.32.71_stm24_0217
# "_" causes illegal character error in kernel module name

python split_kernel_module_packages () {
    import re

    modinfoexp = re.compile("([^=]+)=(.*)")

    def extract_modinfo(file):
        import tempfile, subprocess
        tempfile.tempdir = d.getVar("WORKDIR")
        tf = tempfile.mkstemp()
        tmpfile = tf[1]
        cmd = "%sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("HOST_PREFIX") or "", file, tmpfile)
        subprocess.call(cmd, shell=True)
        f = open(tmpfile)
        l = f.read().split("\000")
        f.close()
        os.close(tf[0])
        os.unlink(tmpfile)
        vals = {}
        for i in l:
            m = modinfoexp.match(i)
            if not m:
                continue
            vals[m.group(1)] = m.group(2)
        return vals

    def frob_metadata(file, pkg, pattern, format, basename):
        vals = extract_modinfo(file)

        dvar = d.getVar('PKGD')

        # If autoloading is requested, output /etc/modules-load.d/<name>.conf and append
        # appropriate modprobe commands to the postinst
        autoloadlist = (d.getVar("KERNEL_MODULE_AUTOLOAD") or "").split()
        autoload = d.getVar('module_autoload_%s' % basename)
        if autoload and autoload == basename:
            bb.warn("module_autoload_%s was replaced by KERNEL_MODULE_AUTOLOAD for cases where basename == module name, please drop it" % basename)
        if autoload and basename not in autoloadlist:
            bb.warn("module_autoload_%s is defined but '%s' isn't included in KERNEL_MODULE_AUTOLOAD, please add it there" % (basename, basename))
        if basename in autoloadlist:
            name = '%s/etc/modules-load.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            if autoload:
                for m in autoload.split():
                    f.write('%s\n' % m)
            else:
                f.write('%s\n' % basename)
            f.close()
            postinst = d.getVar('pkg_postinst_%s' % pkg)
            if not postinst:
                bb.fatal("pkg_postinst_%s not defined" % pkg)
            postinst += d.getVar('autoload_postinst_fragment') % (autoload or basename)
            d.setVar('pkg_postinst_%s' % pkg, postinst)

        # Write out any modconf fragment
        modconflist = (d.getVar("KERNEL_MODULE_PROBECONF") or "").split()
        modconf = d.getVar('module_conf_%s' % basename)
        if modconf and basename in modconflist:
            name = '%s/etc/modprobe.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            f.write("%s\n" % modconf)
            f.close()
        elif modconf:
            bb.error("Please ensure module %s is listed in KERNEL_MODULE_PROBECONF since module_conf_%s is set" % (basename, basename))

        files = d.getVar('FILES_%s' % pkg)
        files = "%s /etc/modules-load.d/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename)
        d.setVar('FILES_%s' % pkg, files)

        if "description" in vals:
            old_desc = d.getVar('DESCRIPTION_' + pkg) or ""
            d.setVar('DESCRIPTION_' + pkg, old_desc + "; " + vals["description"])

        rdepends = bb.utils.explode_dep_versions2(d.getVar('RDEPENDS_' + pkg) or "")
        modinfo_deps = []
        if "depends" in vals and vals["depends"] != "":
            for dep in vals["depends"].split(","):
                on = legitimize_package_name(dep)
                dependency_pkg = format % on
                modinfo_deps.append(dependency_pkg)
        for dep in modinfo_deps:
            if not dep in rdepends:
                rdepends[dep] = []
        d.setVar('RDEPENDS_' + pkg, bb.utils.join_deps(rdepends, commasep=False))

        # Avoid automatic -dev recommendations for modules ending with -dev.
        d.setVarFlag('RRECOMMENDS_' + pkg, 'nodeprrecs', 1)

    module_regex = '^(.*)\.k?o$'

    module_pattern_prefix = d.getVar('KERNEL_MODULE_PACKAGE_PREFIX')
    module_pattern = module_pattern_prefix + 'kernel-module-%s'

    postinst = d.getVar('pkg_postinst_modules')
    postrm = d.getVar('pkg_postrm_modules')

    modules = do_split_packages(d, root='/lib/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='kernel-%s' % (d.getVar("KERNEL_VERSION")))
    if modules:
        metapkg = d.getVar('KERNEL_MODULES_META_PACKAGE')
        d.appendVar('RDEPENDS_' + metapkg, ' '+' '.join(modules))

    # If modules-load.d and modprobe.d are empty at this point, remove them to
    # avoid warnings. removedirs only raises an OSError if an empty
    # directory cannot be removed.
    dvar = d.getVar('PKGD')
    for dir in ["%s/etc/modprobe.d" % (dvar), "%s/etc/modules-load.d" % (dvar), "%s/etc" % (dvar)]:
        if len(os.listdir(dir)) == 0:
            os.rmdir(dir)
}