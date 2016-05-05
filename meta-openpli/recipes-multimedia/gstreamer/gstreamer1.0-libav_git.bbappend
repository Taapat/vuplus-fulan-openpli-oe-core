DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "3fb49b60537c64999383e058b1bbf6ff3bec87de"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"

DEPENDS =+ "ffmpeg"

PACKAGECONFIG = "libav"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
