SUMMARY = "Enigma2 plugin to watch MTG play online services"
DESCRIPTION = "Watch MTG play online services"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-mtgplay"
MAINTAINER = "Taapat <taapat@gmail.com>"
SECTION = "multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

SRC_URI = "git://github.com/Taapat/enigma2-plugin-mtgplay;protocol=git"

S = "${WORKDIR}/git"

inherit allarch distutils-openplugins

RDEPENDS_${PN} = " \
	python-json \
	python-netclient \
	python-twisted-web \
	"
