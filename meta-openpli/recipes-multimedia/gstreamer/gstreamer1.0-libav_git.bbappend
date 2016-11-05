DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-libav;name=last \
"

inherit gitpkgv
SRCREV_last = "50ffd5a8057d40e573b4b15b62d3a3321e168d89"
SRCREV_FORMAT = "last"
PV = "1.10.0+git${SRCPV}"
PKGV = "1.10.0+git${GITPKGV}"

DEPENDS =+ "ffmpeg"

PACKAGECONFIG = "libav"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
