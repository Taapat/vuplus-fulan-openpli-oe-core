DESCRIPTION = "Skin MetropolisHD by Franc modified by Taapat"
MAINTAINER = "Taapat"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://README.md;md5=78503eee4263584d3f878da381495a3e"

inherit gitpkgv allarch

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/Taapat/skin-MetropolisHD.git"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
	python -O -m compileall ${S}/usr/lib/enigma2/python/Components/
}

do_install() {
	install -d ${D}/usr
	cp -r ${S}/usr/* ${D}/usr/
}
