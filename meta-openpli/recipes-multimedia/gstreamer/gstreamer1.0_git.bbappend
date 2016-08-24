DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gstreamer;name=last \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_last = "1e1ea38b8eabedc97a9a6629246c9371fc1d7858"
SRCREV_FORMAT = "last"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"
