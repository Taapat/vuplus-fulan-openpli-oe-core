FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
    file://login-blank-password.patch \
    file://0005-whitespaces.patch \
    file://0006-greedy.patch \
    file://0010-remote-dos.patch \
    file://enable-writable-root.patch \
"

SRC_URI_remove = "\
    file://init \
    file://vsftpd.user_list \
    file://vsftpd.ftpusers \
    file://change-secure_chroot_dir.patch \
    file://volatiles.99_vsftpd \
    file://vsftpd.service \
    file://vsftpd-2.1.0-filter.patch \
"

INHERIT_remove = "update-rc.d useradd systemd"
PACKAGECONFIG = " "

# Remove -lwrap from flags
do_compile() {
   oe_runmake "LIBS=-L${STAGING_LIBDIR} -lcrypt -lcap ${PAMLIB}"
}

do_install() {
	install -d ${D}${sbindir}
    install -d ${D}${mandir}/man8
    install -d ${D}${mandir}/man5
    oe_runmake 'DESTDIR=${D}' install
    install -d ${D}${sysconfdir}
    install -m 600 ${WORKDIR}/vsftpd.conf ${D}${sysconfdir}/vsftpd.conf
	install -d ${D}${localstatedir}/share/empty
}
