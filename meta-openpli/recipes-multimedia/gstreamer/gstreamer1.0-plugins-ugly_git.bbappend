DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
    git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;name=last \
"

inherit gitpkgv
SRCREV_last = "c18ed0bee6953c19ea6b23362e671551489157b8"
SRCREV_FORMAT = "last"
PV = "1.9.2.1+git${SRCPV}"
PKGV = "1.9.2.1+git${GITPKGV}"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
