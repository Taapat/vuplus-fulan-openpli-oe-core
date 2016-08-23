DEFAULT_PREFERENCE = "1"

inherit gitpkgv
SRCREV_base = "c8260a15459cc809b1b7390ba0bd19124b09f070"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
