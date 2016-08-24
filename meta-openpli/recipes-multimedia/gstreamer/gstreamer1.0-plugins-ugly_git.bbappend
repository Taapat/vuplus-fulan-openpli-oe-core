DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
    git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;name=last \
"

inherit gitpkgv
SRCREV_last = "c8260a15459cc809b1b7390ba0bd19124b09f070"
SRCREV_FORMAT = "last"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
