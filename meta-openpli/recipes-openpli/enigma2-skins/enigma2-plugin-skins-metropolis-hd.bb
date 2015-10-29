DESCRIPTION = "Skin MetropolisHD by Franc modified by Taapat"
MAINTAINER = "Taapat"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://README.md;md5=8a6e78776d75ad75429a878d767db4a8"

inherit gitpkgv allarch

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r1"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/Taapat/skin-MetropolisHD.git"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr
	cp -rp ${S}/usr/* ${D}/usr/
	chmod -R a+rX ${D}/usr/
}
