DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
    git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;name=last \
"

inherit gitpkgv
SRCREV_last = "7d864e8b45408af9764bf6f34174d1cd7cb283f6"
SRCREV_FORMAT = "last"
PV = "1.9.9+git${SRCPV}"
PKGV = "1.9.9+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
