SUMMARY = "shared library for GIF images"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=ae11c61b04b2917be39b11f78d71519a"
PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/giflib/${BP}.tar.bz2"

inherit autotools

PACKAGES += "${PN}-utils"
FILES_${PN} = "${libdir}/libgif.so.*"
FILES_${PN}-utils = "${bindir}"

BBCLASSEXTEND = "native"

RDEPENDS_${PN}-utils = "perl"

SRC_URI[md5sum] = "323a9f11ab56c8a2d1715376410ce376"
SRC_URI[sha256sum] = "76c0a084c3b02f9315ff937b8be6096186002fea26f33e2123081ba2be6e2a7c"
