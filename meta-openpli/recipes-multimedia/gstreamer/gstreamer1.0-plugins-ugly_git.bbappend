DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "ee966c4dd298c9bd4c81678d5aa7e6ea9f93aee3"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
