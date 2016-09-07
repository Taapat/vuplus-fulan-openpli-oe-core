DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-libav;name=last \
"

inherit gitpkgv
SRCREV_last = "f6c31d1cb69a3ce907bc0513cfcb5025f5b97345"
SRCREV_FORMAT = "last"
PV = "1.9.2.1+git${SRCPV}"
PKGV = "1.9.2.1+git${GITPKGV}"

DEPENDS =+ "ffmpeg"

PACKAGECONFIG = "libav"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
