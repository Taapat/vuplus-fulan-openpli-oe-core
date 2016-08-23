DEFAULT_PREFERENCE = "1"

SRC_URI += " \
	git://anongit.freedesktop.org/gstreamer/gst-libav;name=last \
"

inherit gitpkgv
SRCREV_last = "e5ceb287d3e5c5e20ad7cf382c8ef237b267bc7f"
SRCREV_FORMAT = "last"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"

DEPENDS =+ "ffmpeg"

PACKAGECONFIG = "libav"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
