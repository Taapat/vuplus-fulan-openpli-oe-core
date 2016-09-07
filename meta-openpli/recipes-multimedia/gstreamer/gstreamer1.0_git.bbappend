DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gstreamer;name=last \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_last = "7315a07bc8c60e55f008f75e8dfd8d156c6b93b3"
SRCREV_FORMAT = "last"
PV = "1.9.2.1+git${SRCPV}"
PKGV = "1.9.2.1+git${GITPKGV}"
