DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=master;name=base "
SRC_URI_append = " \
	file://revert-always-snap-to-the-start-of-the-keyframe.patch \
"

SRCREV_base = "3a6900df4575866d9e6aa1b41de0817696f0f6e7"

RRECOMMENDS_${PN} = ""
