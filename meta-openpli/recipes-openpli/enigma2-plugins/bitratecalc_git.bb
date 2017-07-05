DESCRIPTION = "Library that implements bitrate calculations from enigma2"
HOMEPAGE = "https://github/Taapat/bitratecalc"
LICENSE = "CC-BY-NC-ND-4.0"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/meta-openpli/licenses/CC-BY-NC-ND-4.0;md5=8009795292660aa9c8da059e5b1581c1"

DEPENDS = "enigma2"

inherit gitpkgv autotools pythonnative pkgconfig

SRCREV = "${AUTOREV}"
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://github.com/Taapat/bitratecalc.git"

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/bitratecalc.so"

