DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "f52343fcf85d3d700e78210ff6ee07de1ea42da1"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
