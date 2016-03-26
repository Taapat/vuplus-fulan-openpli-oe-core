DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV_base = "c441eddd568003bf8a22ebd57b9d0f4ceb6402b3"
PV = "1.8.0+git${SRCPV}"

CFLAGS_append = " -Wno-deprecated-declarations "

PACKAGECONFIG = "libav"

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@base_contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
