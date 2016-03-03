SUMMARY = "Enigma2 plugin to manage your youtube account and watch videos"
DESCRIPTION = "Small plugin to manage your account, search and watch videos \
from youtube"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-blurayplayer"
SECTION = "multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"
SRC_URI = "git://github.com/Taapat/enigma2-plugin-blurayplayer.git"
FILESEXTRAPATHS_prepend := "/disks2/alien/e2taapat:"
S = "${WORKDIR}/git"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "h1+git${SRCPV}"
PKGV = "h1+git${GITPKGV}"

inherit distutils-openplugins

DEPENDS = "libbluray"
RDEPENDS_${PN} = "libbluray"
