DESCRIPTION = "Start, stop, restart SoftCams, change setting path"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-alternativesoftcammanager"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://src/plugin.py;md5=4c01537e8142d710373757945cc068b8"
SRC_URI = "git://github.com/Taapat/enigma2-plugin-alternativesoftcammanager.git"
S = "${WORKDIR}/git"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit allarch distutils-openplugins

