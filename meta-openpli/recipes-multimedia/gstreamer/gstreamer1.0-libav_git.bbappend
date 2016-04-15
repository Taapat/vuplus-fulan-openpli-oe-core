DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "9800b1135e3ff5e031655340bcdbfe89ad88e479"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"
PR = "r2"

PACKAGECONFIG = " "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@base_contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
  --disable-everything \
  --enable-decoder=wmalossless \
  --enable-decoder=wmapro \
  --enable-decoder=wmav1 \
  --enable-decoder=wmav2 \
  --enable-decoder=wmavoice \
"
