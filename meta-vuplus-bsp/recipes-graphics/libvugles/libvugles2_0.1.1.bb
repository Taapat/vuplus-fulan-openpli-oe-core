DESCRIPTION = "shared library for E2 animation"
SECTION = "libs"
LICENSE = "CLOSED"

DEPENDS = "vuplus-libgles-${MACHINE}"

SRCDATE = "20170104"
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

SRC_URI[md5sum] = "6c72f84fe05973e10807c4b95687ef91"
SRC_URI[sha256sum] = "a3d3da15c88beab7d5e931458df5451ca1c972dd680ec23ece862dd432f32cf2"
