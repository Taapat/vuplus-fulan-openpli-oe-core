DESCRIPTION = "serviceapp service for enigma2"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "gettext-native enigma2 uchardet"

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

SRC_URI = "git://github.com/mx3L/serviceapp.git;branch=master"

S = "${WORKDIR}/git"

inherit autotools pythonnative

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

do_install_append() {
	rm ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/ServiceApp/*.pyc
}

FILES_${PN} = "${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/"
FILES_${PN}-dev = "${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/serviceapp.la"
