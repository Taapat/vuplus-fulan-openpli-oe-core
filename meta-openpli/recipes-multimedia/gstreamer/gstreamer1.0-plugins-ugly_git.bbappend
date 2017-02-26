DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=master;name=base "

SRCREV_base = "1f1339913fb88f061899633105ab21745791ca6b"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    a52dec lame mpeg2dec \
    cdio dvdread amrnb amrwb x264 \
"

EXTRA_OECONF_remove = " \
    --disable-mad \
"

RRECOMMENDS_${PN} = ""
