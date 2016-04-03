DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit gitpkgv
SRCREV_base = "1af733a6366d0f148874bd10958a38ceb79b9be4"
PV = "1.9.0.1+git${SRCPV}"
PKGV = "1.9.0.1+git${GITPKGV}"
PR = "r1"

CFLAGS_append = " -Wno-deprecated-declarations "

PACKAGECONFIG = "libav"

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@base_contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
