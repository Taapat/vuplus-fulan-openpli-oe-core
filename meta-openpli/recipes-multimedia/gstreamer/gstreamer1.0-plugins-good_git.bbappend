DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=master;name=base "

SRCREV_base = "23f4cd0c4ed66d1881d262668f35d708b4d0feee"

RRECOMMENDS_${PN} = ""
