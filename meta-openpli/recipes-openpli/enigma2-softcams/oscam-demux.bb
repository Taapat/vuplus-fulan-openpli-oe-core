require conf/license/openpli-gplv2.inc
inherit cmake

DESCRIPTION = "OScam ${PV} Open Source Softcam with demux patch"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "${AUTOREV}"
PV = "svn${SRCREV}"
PKGV = "${PV}"
SRC_URI = "svn://www.streamboard.tv/svn/oscam;protocol=http;module=trunk;scmdata=keep \
	file://oscam-demux.patch \
	file://oscam-config-camd35_tpc.patch \
"

S = "${WORKDIR}/trunk"
B = "${S}"

FILES_${PN} = "/var/emu/oscam-demux-gcc5"

EXTRA_OECMAKE += "\
	-DOSCAM_SYSTEM_NAME=Tuxbox \
	-DWITH_UTF8=1 \
"

do_install() {
	install -d ${D}/var/emu
	install -m 0755 ${B}/oscam ${D}/var/emu
	mv ${D}/var/emu/oscam ${D}/var/emu/oscam-demux-${TUNE_PKGARCH}
}
