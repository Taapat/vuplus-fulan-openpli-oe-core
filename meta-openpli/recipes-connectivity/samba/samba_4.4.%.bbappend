PR = "r0"

# Remove acl, cups etc. support.
PACKAGECONFIG = "${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SYSVINITTYPE}', '', d)} \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'zeroconf', '', d)} \
                "

DEPENDS = "readline virtual/libiconv zlib krb5 libbsd"

SAMBA4_LIBS = "heimdal,!zlib,popt,talloc,pytalloc,pytalloc-util,tevent,pytevent,tdb,pytdb,ldb,pyldb"

EXTRA_OECONF += " \
                 --without-cluster-support \
                 --without-profiling-data \
                 --with-sockets-dir=${localstatedir}/run \
                 --with-logfilebase=${localstatedir}/log \
                 --nopyc \
                 --disable-iprint \
                 --without-ads \
                 --without-dnsupdate \
                 --without-quotas \
                 --without-winbind \
                 --without-syslog \
                 --disable-python \
                "

EXTRA_OECONF_remove = " \
                       --with-cluster-support \
                       --with-profiling-data \
                       --with-sockets-dir=${localstatedir}/run/samba \
                      "

# Remove unused, add own config, init script
SRC_URI += " \
           file://smb.conf \
           file://samba.sh \
           file://22-disable-python.patch \
           file://23-fix-idmap-building-without-ldap.patch \
           "

FILES_${PN}-base += "${sysconfdir}/samba/smb.conf"

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
                    ${libdir}/samba/libreplace-samba4.so \
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

FILES_libwbclient += "${libdir}/samba/libwinbind-client-samba4.so \
                     ${libdir}/samba/libreplace-samba4.so \
"

FILES_libnetapi = ""

do_install_append() {
	rm -fR ${D}/var
	rm -fR ${D}/run
	rm -fR ${D}${bindir}
	rm -fR ${D}${libdir}/tmpfiles.d
	rm -fR ${D}${sysconfdir}/sysconfig
	install -d ${D}/var/lib/samba/private
	install -d ${D}${sysconfdir}/samba
	install -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/samba.sh ${D}${sysconfdir}/init.d
}

INITSCRIPT_PACKAGES = "${PN}-base"

CONFFILES_${PN}-base = "${sysconfdir}/samba/smb.conf"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# workaround to get rid of perl dependency
DEPENDS_remove = "perl"
