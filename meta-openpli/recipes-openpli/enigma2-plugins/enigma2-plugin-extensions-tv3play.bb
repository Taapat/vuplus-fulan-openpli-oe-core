DESCRIPTION = "Watch TV3 play online services"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-tv3play"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://src/plugin.py;md5=5a543b04f81e679a695bb818897916c3"
SRC_URI = "git://github.com/Taapat/enigma2-plugin-tv3play.git;protocol=git"
S = "${WORKDIR}/git"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit allarch distutils-openplugins

RDEPENDS_${PN} = " \
	python-json \
	python-netclient \
	python-twisted-web \
	python-compression \
	"

