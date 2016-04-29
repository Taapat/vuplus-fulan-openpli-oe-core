DESCRIPTION = "Library to play files in enigma2 using ffmpeg"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ffmpeg"

SRCREV = "${AUTOREV}"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/Taapat/exteplayer3-mipsel.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_install_append () {
	install -d ${D}${includedir}
	install -m 644 ${S}/include/*.h ${D}${includedir}
}
