DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "8426bd88c289a1de9e7835176cfb5c9f0b1b614b"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
