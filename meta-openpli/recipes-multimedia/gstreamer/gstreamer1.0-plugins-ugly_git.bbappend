DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
    git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;name=last \
"

inherit gitpkgv
SRCREV_last = "534819a16c8e30529bb492dc0e2c5da4468b8fc8"
SRCREV_FORMAT = "last"
PV = "1.10.0+git${SRCPV}"
PKGV = "1.10.0+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
