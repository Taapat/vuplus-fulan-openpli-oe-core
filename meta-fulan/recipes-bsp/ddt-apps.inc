HOMEPAGE = "https://github/Taapat/apps"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"
DEPENDS = "fulan-dvb-modules"

SRCREV = "${AUTOREV}"

inherit gitpkgv

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = " \
    git://github.com/Taapat/apps.git;protocol=git \
    file://libtool_dont_stop.patch \
"

S = "${WORKDIR}/git/tools"

inherit autotools 

EXTRA_OECONF = "--enable-silent-rules --prefix="

LDFLAGS += "-lpthread -lrt"

do_configure() {
    cd ${S}
    ./autogen.sh
    oe_runconf
}

do_compile() {
    cd ${S}
    oe_runmake  'DESTDIR=${D}' 'SUBDIRS=${@d.getVar('PN',1).replace('-', '_')}' 
}

do_install() {
    cd ${S}
    oe_runmake  'DESTDIR=${D}' 'SUBDIRS=${@d.getVar('PN',1).replace('-', '_')}' install
}

