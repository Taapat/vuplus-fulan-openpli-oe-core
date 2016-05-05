SECTION = "console/network"

LICENSE = "GPL-3.0+ & LGPL-3.0+ & GPL-2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://${COREBASE}/meta/files/common-licenses/LGPL-3.0;md5=bfccfe952269fff2b407dd11f2f3083b \
                    file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 "

SAMBA_MIRROR = "http://samba.org/samba/ftp"
MIRRORS += "\
${SAMBA_MIRROR}    http://mirror.internode.on.net/pub/samba \n \
${SAMBA_MIRROR}    http://www.mirrorservice.org/sites/ftp.samba.org \n \
"

SRC_URI = "${SAMBA_MIRROR}/stable/samba-${PV}.tar.gz \
           file://14-fix-dnsupdate.patch  \
           file://16-do-not-check-xsltproc-manpages.patch \
           file://18-avoid-get-config-by-native-ncurses.patch \
           file://20-do-not-import-target-module-while-cross-compile.patch \
           file://21-add-config-option-without-valgrind.patch \
           file://0006-avoid-using-colon-in-the-checking-msg.patch \
          "

SRC_URI[md5sum] = "83b1af8a9899ab4de99f155fc42c83dd"
SRC_URI[sha256sum] = "031e6ada6d15deae6850845eed41497af32207fb679d6c6c74f19acc99d437ba"

inherit systemd waf-samba cpan-base perlnative
# remove default added RDEPENDS on perl
RDEPENDS_${PN}_remove = "perl"

DEPENDS += "readline virtual/libiconv zlib krb5 libbsd"

SYSVINITTYPE_linuxstdbase = "lsb"
SYSVINITTYPE = "sysv"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SYSVINITTYPE}', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'zeroconf', '', d)} \
                   acl aio cups ldap \
"

RDEPENDS_${PN}-base += "${@bb.utils.contains('PACKAGECONFIG', 'lsb', 'lsb', '', d)}"

PACKAGECONFIG[acl] = "--with-acl-support,--without-acl-support,acl"
PACKAGECONFIG[fam] = "--with-fam,--without-fam,gamin"
PACKAGECONFIG[pam] = "--with-pam --with-pammodulesdir=${base_libdir}/security,--without-pam,libpam"
PACKAGECONFIG[lsb] = ",,lsb"
PACKAGECONFIG[sysv] = ",,sysvinit"
PACKAGECONFIG[cups] = "--enable-cups,--disable-cups,cups"
PACKAGECONFIG[ldap] = "--with-ldap,--without-ldap,openldap"
PACKAGECONFIG[sasl] = ",,cyrus-sasl"
PACKAGECONFIG[systemd] = "--with-systemd,--without-systemd,systemd"
PACKAGECONFIG[dmapi] = "--with-dmapi,--without-dmapi,dmapi"
PACKAGECONFIG[zeroconf] = "--enable-avahi,--disable-avahi,avahi"
PACKAGECONFIG[valgrind] = ",--without-valgrind,valgrind,"

SAMBA4_IDMAP_MODULES="idmap_ad,idmap_rid,idmap_adex,idmap_hash,idmap_tdb2"
SAMBA4_PDB_MODULES="pdb_tdbsam,${@bb.utils.contains('PACKAGECONFIG', 'ldap', 'pdb_ldap,', '', d)}pdb_ads,pdb_smbpasswd,pdb_wbc_sam,pdb_samba4"
SAMBA4_AUTH_MODULES="auth_unix,auth_wbc,auth_server,auth_netlogond,auth_script,auth_samba4"
SAMBA4_MODULES="${SAMBA4_IDMAP_MODULES},${SAMBA4_PDB_MODULES},${SAMBA4_AUTH_MODULES}"

SAMBA4_LIBS="heimdal,!zlib,popt,talloc,pytalloc,pytalloc-util,tevent,pytevent,tdb,pytdb,ldb,pyldb"

PERL_VERNDORLIB="${libdir}/perl5/vendor_perl/${PERLVERSION}"

EXTRA_OECONF += "--enable-fhs \
                 --with-piddir=/run \
                 --with-sockets-dir=/run/samba \
                 --with-modulesdir=${libdir}/samba \
                 --with-lockdir=${localstatedir}/lib/samba \
                 --with-cachedir=${localstatedir}/lib/samba \
                 --disable-gnutls \
                 --disable-rpath-install \
                 --with-shared-modules=${SAMBA4_MODULES} \
                 --bundled-libraries=${SAMBA4_LIBS} \
                 --with-system-mitkrb5 \
                 --without-ad-dc \
                 ${@base_conditional('TARGET_ARCH', 'x86_64', '', '--disable-glusterfs', d)} \
                 --with-cluster-support \
                 --with-profiling-data \
                 --with-libiconv=${STAGING_DIR_HOST}${prefix} \
                "
DISABLE_STATIC = ""

LDFLAGS += "-Wl,-z,relro,-z,now"

do_install_append() {
    if [ -d "${D}/run" ]; then
        if [ -d "${D}/run/samba" ]; then
            rmdir --ignore-fail-on-non-empty "${D}/run/samba"
        fi
        rmdir --ignore-fail-on-non-empty "${D}/run"
    fi

    if ${@bb.utils.contains('PACKAGECONFIG', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        for i in nmb smb winbind; do
            install -m 0644 packaging/systemd/$i.service ${D}${systemd_unitdir}/system
        done
        sed -i 's,\(ExecReload=\).*\(/kill\),\1${base_bindir}\2,' ${D}${systemd_unitdir}/system/*.service

	install -d ${D}${sysconfdir}/tmpfiles.d
        install -m644 packaging/systemd/samba.conf.tmp ${D}${sysconfdir}/tmpfiles.d/samba.conf
        echo "d ${localstatedir}/log/samba 0755 root root -" \
            >> ${D}${sysconfdir}/tmpfiles.d/samba.conf
    elif ${@bb.utils.contains('PACKAGECONFIG', 'lsb', 'true', 'false', d)}; then
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 packaging/LSB/samba.sh ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} samba.sh start 20 3 5 .
	update-rc.d -r ${D} samba.sh start 20 0 1 6 .
    elif ${@bb.utils.contains('PACKAGECONFIG', 'sysv', 'true', 'false', d)}; then
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 packaging/sysv/samba.init ${D}${sysconfdir}/init.d/samba.sh
	update-rc.d -r ${D} samba.sh start 20 3 5 .
	update-rc.d -r ${D} samba.sh start 20 0 1 6 .
    fi

    install -d ${D}${sysconfdir}/samba
    echo "127.0.0.1 localhost" > ${D}${sysconfdir}/samba/lmhosts
    install -m644 packaging/LSB/smb.conf ${D}${sysconfdir}/samba/smb.conf

    install -d ${D}${sysconfdir}/sysconfig/
    install -m644 packaging/systemd/samba.sysconfig ${D}${sysconfdir}/sysconfig/samba
}

PACKAGES += "${PN}-python ${PN}-python-dbg ${PN}-pidl libwinbind libwinbind-dbg libwinbind-krb5-locator"
PACKAGES =+ "libwbclient libnss-winbind winbind winbind-dbg libsmbsharemodes \
             libsmbclient libsmbclient-dev lib${PN}-base ${PN}-base"

RDEPENDS_${PN} += "${PN}-base"

FILES_${PN}-base = "${sbindir}/nmbd \
                    ${sbindir}/smbd \
                    ${sysconfdir}/init.d \
                    ${localstatedir}/lib/samba \
                    ${localstatedir}/log/samba \
                    ${localstatedir}/nmbd \
                    ${localstatedir}/spool/samba \
"

# figured out by
# FILES="tmp/work/mips32el-oe-linux/samba/4.4.0-r0/image/usr/sbin/smbd tmp/work/mips32el-oe-linux/samba/4.4.0-r0/image/usr/sbin/nmbd"
#
# while [ "${FILES}" != "${OLDFILES}" ]
# do
#     OLDFILES="${FILES}"
#     NEEDED=`tmp/sysroots/x86_64-linux/usr/libexec/mipsel-oe-linux.gcc-cross-initial-mipsel/gcc/mipsel-oe-linux/5.3.0/objdump -x ${FILES} | grep NEEDED | egrep -E 'so(.[0-9]|$)' | sort -u | perl -MData::Dumper -le 'while (<>) {chomp; push @lib, (split)[1]}; print "(", join("|", @lib), ")"'`
#     NF=`find tmp/work/mips32el-oe-linux/samba/4.4.0-r0/image/usr/lib -type f | egrep "${NEEDED}" | sort -u`
#
#     FILES=`perl -le 'foreach (@ARGV) { $f{$_}++ }; print join(" ", sort keys %f)' ${FILES} ${NF}`
# done
#
# LIBS=`echo ${FILES} | sed -e 's,tmp/work/mips32el-oe-linux/samba/4.4.0-r0/image/usr/lib,${libdir},g' -e 's,.so.[0-9]+.*$,.so.*,g'`
# for l in ${LIBS}
# do
#     echo $l
# done

FILES_lib${PN}-base = "\
                    ${libdir}/libdcerpc-binding.so.* \
                    ${libdir}/libndr-krb5pac.so.* \
                    ${libdir}/libndr-nbt.so.* \
                    ${libdir}/libndr-standard.so.* \
                    ${libdir}/libndr.so.* \
                    ${libdir}/libnetapi.so.* \
                    ${libdir}/libsamba-credentials.so.* \
                    ${libdir}/libsamba-errors.so.* \
                    ${libdir}/libsamba-hostconfig.so.* \
                    ${libdir}/libsamba-passdb.so.* \
                    ${libdir}/libsamba-util.so.* \
                    ${libdir}/libsamdb.so.* \
                    ${libdir}/libsmbconf.so.* \
                    ${libdir}/libtevent-unix-util.so.* \
                    ${libdir}/libtevent-util.so.* \
                    ${libdir}/libwbclient.so.* \
                    ${libdir}/samba/libCHARSET3-samba4.so \
                    ${libdir}/samba/libaddns-samba4.so \
                    ${libdir}/samba/libads-samba4.so \
                    ${libdir}/samba/libasn1util-samba4.so \
                    ${libdir}/samba/libauth-sam-reply-samba4.so \
                    ${libdir}/samba/libauth-samba4.so \
                    ${libdir}/samba/libauthkrb5-samba4.so \
                    ${libdir}/samba/libcli-cldap-samba4.so \
                    ${libdir}/samba/libcli-ldap-common-samba4.so \
                    ${libdir}/samba/libcli-nbt-samba4.so \
                    ${libdir}/samba/libcli-smb-common-samba4.so \
                    ${libdir}/samba/libcli-spoolss-samba4.so \
                    ${libdir}/samba/libcliauth-samba4.so \
                    ${libdir}/samba/libdbwrap-samba4.so \
                    ${libdir}/samba/libdcerpc-samba-samba4.so \
                    ${libdir}/samba/libflag-mapping-samba4.so \
                    ${libdir}/samba/libgenrand-samba4.so \
                    ${libdir}/samba/libgensec-samba4.so \
                    ${libdir}/samba/libgse-samba4.so \
                    ${libdir}/samba/libinterfaces-samba4.so \
                    ${libdir}/samba/libiov-buf-samba4.so \
                    ${libdir}/samba/libkrb5samba-samba4.so \
                    ${libdir}/samba/libldb.so.* \
                    ${libdir}/samba/libldbsamba-samba4.so \
                    ${libdir}/samba/liblibcli-lsa3-samba4.so \
                    ${libdir}/samba/liblibcli-netlogon3-samba4.so \
                    ${libdir}/samba/liblibsmb-samba4.so \
                    ${libdir}/samba/libmessages-dgm-samba4.so \
                    ${libdir}/samba/libmessages-util-samba4.so \
                    ${libdir}/samba/libmsghdr-samba4.so \
                    ${libdir}/samba/libmsrpc3-samba4.so \
                    ${libdir}/samba/libndr-samba-samba4.so \
                    ${libdir}/samba/libndr-samba4.so \
                    ${libdir}/samba/libnpa-tstream-samba4.so \
                    ${libdir}/samba/libpopt-samba3-samba4.so \
                    ${libdir}/samba/libpopt-samba4.so \
                    ${libdir}/samba/libprinting-migrate-samba4.so \
                    ${libdir}/samba/libsamba-cluster-support-samba4.so \
                    ${libdir}/samba/libsamba-debug-samba4.so \
                    ${libdir}/samba/libsamba-modules-samba4.so \
                    ${libdir}/samba/libsamba-security-samba4.so \
                    ${libdir}/samba/libsamba-sockets-samba4.so \
                    ${libdir}/samba/libsamba3-util-samba4.so \
                    ${libdir}/samba/libsamdb-common-samba4.so \
                    ${libdir}/samba/libsecrets3-samba4.so \
                    ${libdir}/samba/libserver-id-db-samba4.so \
                    ${libdir}/samba/libserver-role-samba4.so \
                    ${libdir}/samba/libsmb-transport-samba4.so \
                    ${libdir}/samba/libsmbd-base-samba4.so \
                    ${libdir}/samba/libsmbd-conn-samba4.so \
                    ${libdir}/samba/libsmbd-shim-samba4.so \
                    ${libdir}/samba/libsmbregistry-samba4.so \
                    ${libdir}/samba/libsocket-blocking-samba4.so \
                    ${libdir}/samba/libsys-rw-samba4.so \
                    ${libdir}/samba/libtalloc-report-samba4.so \
                    ${libdir}/samba/libtalloc.so.* \
                    ${libdir}/samba/libtdb-wrap-samba4.so \
                    ${libdir}/samba/libtdb.so.* \
                    ${libdir}/samba/libtevent.so.* \
                    ${libdir}/samba/libtime-basic-samba4.so \
                    ${libdir}/samba/libutil-cmdline-samba4.so \
                    ${libdir}/samba/libutil-reg-samba4.so \
                    ${libdir}/samba/libutil-setid-samba4.so \
                    ${libdir}/samba/libutil-tdb-samba4.so \
                    ${libdir}/samba/pdb/*.so \
"

FILES_winbind-dbg = "${libdir}/idmap/.debug/*.so \
                     ${libdir}/security/.debug/pam_winbind.so \
"

FILES_${PN} += "${libdir}/vfs/*.so \
                ${libdir}/charset/*.so \
                ${libdir}/*.dat \
                ${libdir}/auth/*.so \
                ${libdir}/security/pam_smbpass.so \
"

FILES_${PN}-dbg += "${libdir}/vfs/.debug/*.so \
                    ${libdir}/charset/.debug/*.so \
                    ${libdir}/auth/.debug/*.so \
                    ${libdir}/security/.debug/pam_smbpass.so \
"

FILES_libwbclient = "${libdir}/libwbclient.so.* \
                     ${libdir}/samba/libwinbind-client.so \
                     ${libdir}/samba/libwinbind-client-samba4.so \
                     ${libdir}/samba/libreplace-samba4.so \
"

FILES_libsmbsharemodes = "${libdir}/libsmbsharemodes.so.*"
FILES_libsmbclient = "${libdir}/libsmbclient.so.*"
FILES_libsmbclient-dev = "${libdir}/libsmbclient.so ${includedir}"
FILES_winbind = "${sbindir}/winbindd \
                 ${bindir}/wbinfo \
                 ${bindir}/ntlm_auth \
                 ${sysconfdir}/init.d/winbind \
                 ${systemd_unitdir}/system/winbind.service \
"

FILES_libnss-winbind = "${libdir}/libnss_*${SOLIBS} \
                        ${libdir}/nss_info \
"

FILES_${PN} += "${base_libdir}/security/pam_smbpass.so \
"

SMB_SERVICE="${systemd_unitdir}/system/nmb.service ${systemd_unitdir}/system/smb.service"
SMB_SYSV="${sysconfdir}/init.d ${sysconfdir}/rc?.d"
FILES_${PN}-base +="${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${SMB_SERVICE}', '', d)}"
FILES_${PN}-base +="${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SMB_SYSV}', '', d)}"

FILES_${PN}-dbg += "${libdir}/samba/idmap/.debug/* \
                    ${libdir}/samba/pdb/.debug/* \
                    ${libdir}/samba/auth/.debug/* \
                    ${libdir}/samba/nss_info/.debug/* \
                    ${libdir}/samba/ldb/.debug/* \
                    ${libdir}/samba/vfs/.debug/* \
                    ${base_libdir}/security/.debug/pam_smbpass.so \
"

FILES_libwinbind = "${base_libdir}/security/pam_winbind.so"
FILES_libwinbind += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_unitdir}/system/winbind.service', '', d)}"
FILES_libwinbind-dbg = "${base_libdir}/security/.debug/pam_winbind.so"
FILES_libwinbind-krb5-locator = "${libdir}/winbind_krb5_locator.so"

FILES_${PN}-python = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/dcerpc/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/dcerpc/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/external/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/kcc/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/netcmd/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/provision/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/samba3/*.py \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/samba3/*.so \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/subunit/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/tests/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/third_party/* \
                      ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/web_server/* \
"

FILES_${PN}-python-dbg = "${libdir}/python${PYTHON_BASEVERSION}/site-packages/.debug/* \
                          ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/.debug/* \
                          ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/samba3/.debug/* \
                          ${libdir}/python${PYTHON_BASEVERSION}/site-packages/samba/dcerpc/.debug/* \
"

RDEPENDS_${PN}-pidl_append = " perl"
FILES_${PN}-pidl = "${bindir}/pidl ${PERL_VERNDORLIB}/* ${datadir}/perl5/*"
