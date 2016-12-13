DESCRIPTION = "shared library for E2 animation"
SECTION = "libs"
LICENSE = "CLOSED"

SRCDATE = "20161208"
SRCDATE_PR = "r1"
PR = "${SRCDATE}.${SRCDATE_PR}"

SRC_URI = "http://archive.vuplus.com/download/build_support/vuplus/${PN}-${PV}-${PR}.tar.gz"

S = "${WORKDIR}/${PN}-${PV}"

inherit pkgconfig

do_install() {
	install -d ${D}${includedir}
	cp ${S}/include/* ${D}${includedir}/
	install -d ${D}${libdir}
	cp ${S}/lib/*.so ${D}${libdir}/
	install -d ${D}${libdir}/pkgconfig
	cp ${S}/lib/pkgconfig/*.pc ${D}${libdir}/pkgconfig/
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
INSANE_SKIP_${PN} = "already-stripped file-rdeps"

PACKAGES = "${PN} ${PN}-dev"
FILES_${PN} = "/usr/lib/*so"
FILES_${PN}-dev = "/usr/include /usr/lib/pkgconfig"

SRC_URI[md5sum] = "e70d352a587b188fb26c43fd982c3200"
SRC_URI[sha256sum] = "46fa8c305ccd48c593e1eea001c44246b38f2e70baa0c2433bb779b220700bad"
