DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gstreamer;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gstreamer;branch=1.10;name=base "
SRC_URI_append = " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_base = "100671b42db3cb4571e296d5cdf567ae62d75b04"
PV = "1.10.1+git${SRCPV}"
PKGV = "1.10.1+git${GITPKGV}"
