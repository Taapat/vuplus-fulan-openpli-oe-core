# Remove acl, cups etc. support.
PACKAGECONFIG = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
                 ${@bb.utils.contains('DISTRO_FEATURES', 'zeroconf', 'zeroconf', '', d)} \
"

DEPENDS = "readline virtual/libiconv zlib krb5 libbsd libtalloc"

SAMBA4_LIBS = "heimdal,!zlib,popt,talloc,pytalloc,pytalloc-util,tevent,pytevent,tdb,pytdb,ldb,pyldb"

EXTRA_OECONF += " \
                 --without-cluster-support \
                 --without-profiling-data \
                 --with-piddir=/var/run \
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
                       --with-sockets-dir=/run/samba \
                      "

# Remove unused, add own config
SRC_URI += " \
           file://smb.conf \
           file://samba.sh \
           file://22-disable-python.patch \
           file://23-fix-idmap-building-without-ldap.patch \
           "

SRC_URI_remove = " \
           file://internal_tevent_to_0.9.31.patch \
"

do_install_prepend() {
    # create fake ctdb-tests dirs and files if not exist to fix error on install
    for d in onnode takeover tool eventscripts cunit simple complex; do
        test -d ${S}/ctdb/tests/$d/scripts || mkdir ${S}/ctdb/tests/$d/scripts
        test -d ${S}/ctdb/tests/$d/stubs || mkdir ${S}/ctdb/tests/$d/stubs
    done
    install -d ${D}${bindir}
    echo "Fake file" > ${D}${bindir}/onnode
}

do_install_append() {
	rm -fR ${D}/var
	rm -fR ${D}/run
	rm -fR ${D}${bindir}
	rm -fR ${D}${sysconfdir}/tmpfiles.d
	rm -fR ${D}${sysconfdir}/sysconfig
	install -d ${D}/var/lib/samba/private
	install -d ${D}${sysconfdir}/samba
	install -m 644 ${WORKDIR}/smb.conf ${D}${sysconfdir}/samba
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/samba.sh ${D}${sysconfdir}/init.d/samba
}

CONFFILES_${BPN}-common = "${sysconfdir}/samba/smb.conf"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# workaround to get rid of perl dependency
DEPENDS_remove = "perl"
