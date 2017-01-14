DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=master;name=base "

SRCREV_base = "b477f962a24b86d98b18bcd966137ba90c271300"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mad mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

RRECOMMENDS_${PN} = ""
