DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "eacdaa56b3ad388cd28c7ff04ae78aed82c9edd4"
PV = "1.9.0.1+git${SRCPV}"
PKGV = "1.9.0.1+git${GITPKGV}"

PR = "r1"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"
