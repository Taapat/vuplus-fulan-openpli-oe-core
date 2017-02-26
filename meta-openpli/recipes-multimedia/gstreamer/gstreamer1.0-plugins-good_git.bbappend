DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=master;name=base "

SRCREV_base = "994b1ac3510731178777a1ad4c873ae1b011e4cc"

RRECOMMENDS_${PN} = ""
