DEFAULT_PREFERENCE = "1"

include gstreamer1.0-libav.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://ext/libav/gstav.h;beginline=1;endline=18;md5=a752c35267d8276fd9ca3db6994fca9c"

SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-libav;branch=master \
	file://0001-Disable-yasm-for-libav-when-disable-yasm.patch \
"

S = "${WORKDIR}/git"

SRCREV = "7a8828f68a838eef6616fa39bbaba46c8029b4a6"

inherit gitpkgv
PV = "1.7.91+git${SRCPV}"
PKGV = "1.7.91+git${GITPKGV}"

PR = "r0"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG = "--target-os=linux \
  --cc='${CC}' --as='${CC}' --ld='${CC}' --nm='${NM}' --ar='${AR}' \
  --ranlib='${RANLIB}' \
  ${@base_contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
  ${GSTREAMER_1_0_DEBUG} \
  --cross-prefix='${HOST_PREFIX}'"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

