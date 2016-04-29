DESCRIPTION = "Library to play files in enigma2 using ffmpeg"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"

DEPENDS = "ffmpeg"

SRCREV = "${AUTOREV}"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/samsamsam-iptvplayer/exteplayer3.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_install_append () {
	install -d ${D}${includedir}
	install -m 644 ${S}/include/*.h ${D}${includedir}
}
