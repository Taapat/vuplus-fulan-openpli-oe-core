DEFAULT_PREFERENCE = "1"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=1.10;name=base "

inherit gitpkgv
SRCREV_base = "8bc5142a88be4b502f1f18440b0292208583afa1"
PV = "1.10.1+git${SRCPV}"
PKGV = "1.10.1+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
