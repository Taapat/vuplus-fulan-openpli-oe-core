DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gstreamer;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gstreamer;branch=1.10;name=base "
SRC_URI_append = " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"
SRCREV_base = "9222028aaa57ba28beac7103146491a2c3a45d66"
