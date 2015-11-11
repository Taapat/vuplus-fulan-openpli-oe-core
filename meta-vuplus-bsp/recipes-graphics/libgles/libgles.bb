SECTION = "base"
LICENSE = "CLOSED"

PV="15.1"
SRCDATE = "20150623"
SRCDATE_PR = "r0"
PR="${SRCDATE}.${SRCDATE_PR}"

SRC_URI = "http://archive.vuplus.com/download/build_support/vuplus/libgles-${MACHINE}-${PV}-${PR}.tar.gz"

S = "${WORKDIR}/libgles-${MACHINE}"

PRIVATE_LIBS_${PN} = "libEGL.so \
libGLESv2.so \
libv3ddriver.so \
"

do_install() {
	install -d ${D}${libdir}
	install -m 0755 ${S}/lib/*.so ${D}${libdir}
	ln -s libv3ddriver.so ${D}/usr/lib/libEGL.so
	ln -s libv3ddriver.so ${D}/usr/lib/libGLESv2.so
	install -d ${D}${includedir}
	cp -a ${S}/include/* ${D}${includedir}/
}

do_package_qa() {
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dev"
FILES_${PN} = "/usr/lib/*so"
FILES_${PN}-dev = "/usr/include"

SRC_URI[md5sum] = "cec0ffb9ef13febd74bd99eed55d821f"
SRC_URI[sha256sum] = "0ef6748934a5e3305c77025617a5a71855d6b438a0419b287b2447b54cc8347a"
