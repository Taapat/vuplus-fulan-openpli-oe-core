DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.10;name=base "

SRCREV_base = "235d243dee04d8260774c524a985f58bf20ed61f"

RRECOMMENDS_${PN} = ""
