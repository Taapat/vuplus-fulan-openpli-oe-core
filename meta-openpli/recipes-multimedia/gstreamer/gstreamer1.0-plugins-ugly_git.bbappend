DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "5a78bac10ed3df807d19794df72976ce2ad541dc"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
