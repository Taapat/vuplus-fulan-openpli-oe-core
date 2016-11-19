DEFAULT_PREFERENCE = "1"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-libav;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-libav;branch=1.10;name=base "

inherit gitpkgv
SRCREV_base = "2fb577896d83aa8ee52c85fb6ed368d684f65cfc"
PV = "1.10.1+git${SRCPV}"
PKGV = "1.10.1+git${GITPKGV}"

DEPENDS =+ "ffmpeg"

PACKAGECONFIG = "libav"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
