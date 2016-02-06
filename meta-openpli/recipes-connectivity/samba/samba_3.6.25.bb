LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://../COPYING;md5=d32239bcb673463ab874e80d47fae504"

SECTION = "console/network"
DEPENDS = "readline virtual/libiconv zlib popt attr"

PR = "r0"

inherit autotools-brokensep update-rc.d

SAMBA_MIRROR = "http://samba.org/samba/ftp"

MIRRORS += "\ 
${SAMBA_MIRROR}    http://mirror.internode.on.net/pub/samba \n \
${SAMBA_MIRROR}    http://www.mirrorservice.org/sites/ftp.samba.org \n \
"

SRC_URI = "${SAMBA_MIRROR}/stable/samba-${PV}.tar.gz \
           file://config-h.patch \
           file://fhs-filespaths.patch;patchdir=.. \
           file://undefined-symbols.patch;patchdir=.. \
           file://dont-build-VFS-examples.patch;patchdir=.. \
           file://only_export_public_symbols.patch;patchdir=.. \
           file://configure-disable-getaddrinfo-cross.patch;patchdir=.. \
           file://configure-disable-core_pattern-cross-check.patch;patchdir=.. \
           file://100-configure_fixes.patch;patchdir=.. \
           file://120-add_missing_ifdef.patch;patchdir=.. \
           file://200-remove_printer_support.patch;patchdir=.. \
           file://210-remove_ad_support.patch;patchdir=.. \
           file://220-remove_services.patch;patchdir=.. \
           file://230-remove_winreg_support.patch;patchdir=.. \
           file://240-remove_dfs_api.patch;patchdir=.. \
           file://250-remove_domain_logon.patch;patchdir=.. \
           file://260-remove_samr.patch;patchdir=.. \
           file://270-remove_registry_backend.patch;patchdir=.. \
           file://280-strip_srvsvc.patch;patchdir=.. \
           file://290-remove_lsa.patch;patchdir=.. \
           file://300-assert_debug_level.patch;patchdir=.. \
           file://310-remove_error_strings.patch;patchdir=.. \
           file://320-debug_level_checks.patch;patchdir=.. \
           file://330-librpc_default_print.patch;patchdir=.. \
           file://smb.conf \
           file://samba.sh \
"

SRC_URI[md5sum] = "76da2fa64edd94a0188531e7ecb27c4e"
SRC_URI[sha256sum] = "8f2c8a7f2bd89b0dfd228ed917815852f7c625b2bc0936304ac3ed63aaf83751"

SAMBAMMAP = "no"
SAMBAMMAP_libc-glibc = "yes"

EXTRA_OECONF = " \
    --exec-prefix=/usr \
    --with-readline=${STAGING_LIBDIR}/.. \
    --with-libiconv=${STAGING_LIBDIR}/.. \
    --disable-pie \
    --disable-avahi \
    --disable-cups \
    --disable-relro \
    --disable-swat \
    --disable-shared-libs \
    --disable-socket-wrapper \
    --disable-nss-wrapper \
    --disable-smbtorture4 \
    --disable-fam \
    --disable-iprint \
    --disable-dnssd \
    --disable-pthreadpool \
    --disable-dmalloc \
    --with-included-iniparser \
    --with-included-popt \
    --with-sendfile-support \
    --without-aio-support \
    --without-cluster-support \
    --without-ads \
    --without-krb5 \
    --without-dnsupdate \
    --without-automount \
    --without-ldap \
    --without-pam \
    --without-pam_smbpass \
    --without-winbind \
    --without-wbclient \
    --without-syslog \
    --without-nisplus-home \
    --without-quotas \
    --without-sys-quotas \
    --without-utmp \
    --without-acl-support \
    --disable-external-libtalloc \
    --without-libtalloc \
    --with-configdir=${sysconfdir}/samba \
    --with-privatedir=${sysconfdir}/samba \
    --with-mandir=no \
    --with-piddir=${localstatedir}/run \
    --with-logfilebase=${localstatedir}/log \
    --with-lockdir=${localstatedir}/lock \
    ac_cv_path_PYTHON=/not/exist \
    ac_cv_path_PYTHON_CONFIG=/not/exist \
    ac_cv_file__proc_sys_kernel_core_pattern=yes \
    libreplace_cv_HAVE_C99_VSNPRINTF=yes \
    libreplace_cv_HAVE_GETADDRINFO=yes \
    libreplace_cv_HAVE_GETTIMEOFDAY_TZ=yes \
    libreplace_cv_HAVE_IFACE_AIX=no \
    libreplace_cv_HAVE_IFACE_GETIFADDRS=yes \
    libreplace_cv_HAVE_IFACE_IFCONF=yes \
    libreplace_cv_HAVE_IPV6=yes \
    libreplace_cv_HAVE_MMAP=yes \
    libreplace_cv_HAVE_SECURE_MKSTEMP=yes \
    libreplace_cv_REPLACE_INET_NTOA=no \
    samba_cv_CC_NEGATIVE_ENUM_VALUES=yes \
    samba_cv_HAVE_BROKEN_GETGROUPS=no \
    samba_cv_HAVE_FTRUNCATE_EXTEND=yes \
    samba_cv_have_setresuid=yes \
    samba_cv_have_setresgid=yes \
    samba_cv_HAVE_WRFILE_KEYTAB=yes \
    samba_cv_linux_getgrouplist_ok=yes \
    samba_cv_struct_timespec=yes \
    samba_path_PYTHON="" \
    SMB_BUILD_CC_NEGATIVE_ENUM_VALUES=yes \
    linux_getgrouplist_ok=no \
"

PACKAGES =+ "${PN}-base smbclient ${PN}-base-dbg ${PN}-advanced winbind libwinbind libnss-winbind libsmbclient"

FILES_${PN}-base       = "${sbindir}/smbd ${sbindir}/nmbd \
                          ${sysconfdir}/samba/smb.conf \
                          ${sysconfdir}/init.d/samba.sh"
FILES_${PN}-dbg       += "${libdir}/*.dat"

FILES_smbclient        = "${bindir}/smbclient"
FILES_libsmbclient     = "${libdir}/libsmbclient.so.*"

FILES_winbind         += "${bindir}/ntlm_auth"
FILES_${PN}-advanced   = "${bindir}/net ${bindir}/profiles ${bindir}/rpcclient ${bindir}/smbcacls ${bindir}/smbcquotas ${bindir}/smbget ${bindir}/smbtar ${libdir}/pdb ${libdir}/rpc/*"
FILES_libpopt          = ""
FILES_libwinbind       = ""
FILES_libnss-winbind   = ""

ALLOW_EMPTY_libpopt         = "1"
ALLOW_EMPTY_libwinbind      = "1"
ALLOW_EMPTY_libnss-winbind  = "1"

CFLAGS      += "-fPIC -DHAVE_IPV6=1 -DMAX_DEBUG_LEVEL=-1 -ffunction-sections -fdata-sections"
LDFLAGS     += "-Wl,--gc-sections"
EXTRA_OEMAKE+= "DYNEXP= PICFLAG= MODULES="

INITSCRIPT_PACKAGES = "${PN}-base"
INITSCRIPT_NAME_${PN}-base = "samba.sh"
INITSCRIPT_PARAMS = "defaults"
CONFFILES_${PN}-base = "${sysconfdir}/samba/smb.conf"

S = "${WORKDIR}/samba-${PV}/source3"

do_configure() {
    ./script/mkversion.sh
    if [ ! -e acinclude.m4 ]; then
        touch aclocal.m4    
        cat aclocal.m4 > acinclude.m4
    fi
    gnu-configize --force
    oe_runconf
}

do_compile () {
    base_do_compile
}

PACKAGECONFIG ??= ""

do_install_append() {
    rmdir "${D}${localstatedir}/lock"
    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
    rm -f ${D}${bindir}/*.old
    rm -f ${D}${sbindir}/*.old

    install -D -m 755 ${WORKDIR}/samba.sh ${D}${sysconfdir}/init.d/samba.sh
    install -D -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba/smb.conf

    rmdir --ignore-fail-on-non-empty ${D}${base_sbindir} || true
    sed -i -e '1s,#!.*perl,#!${USRBINPATH}/env perl,' ${D}${bindir}/findsmb

    # Silence warnings - Delete empty directories (Removed features)
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/auth || true
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/charset || true
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/vfs || true

    # Former package libwinbind
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/idmap || true
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/gpext || true
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/perfcount || true

    # Former package libnss-winbind
    rmdir --ignore-fail-on-non-empty ${D}${libdir}/nss_info || true
}

