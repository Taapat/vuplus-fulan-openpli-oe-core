DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "6b707c91f10a6cb492a5962ef21d52e193af7d6a"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"

PR = "r1"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
