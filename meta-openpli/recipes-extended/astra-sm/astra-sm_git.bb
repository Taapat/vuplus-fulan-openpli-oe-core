SUMMARY  = "A professional software to organize Digital TV Broadcasting Service for \
	TV operators and broadcasters, internet service providers, hotels, etc."
SECTION = "multimedia"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM="file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "openssl libdvbcsa"

SRC_URI = "git://gitlab.com/crazycat69/astra-sm.git;protocol=http"

inherit gitpkgv autotools-brokensep pkgconfig

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

S="${WORKDIR}/git"

do_install_append() {
	rm -fR ${D}${sysconfdir}
}

FILES_${PN}-dev += "${datadir}"
