
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR = "r90"

SRC_URI =+ "file://image-version"

conffiles =+ " ${sysconfdir}/image-version /${sysconfdir}/image-version"

do_install_append() {
	install -m 644 ${WORKDIR}/image-version  ${D}${sysconfdir}
	rm -rf ${D}/mnt
	rm -rf ${D}/hdd
	ln -sf media/hdd ${D}/hdd
	ln -sf media ${D}/mnt
	rm -rf ${D}/media/*
	rm -fr ${D}/tmp
}
