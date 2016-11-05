DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gstreamer;name=last \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_last = "81ee25ad4e8db836528a8be1ac85655506fd8eb4"
SRCREV_FORMAT = "last"
PV = "1.10.0+git${SRCPV}"
PKGV = "1.10.0+git${GITPKGV}"
