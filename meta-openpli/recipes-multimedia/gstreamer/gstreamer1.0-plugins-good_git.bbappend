DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;name=last \
"

inherit gitpkgv
SRCREV_last = "990eeb22ba8f8cce9be184abd9c9cd6b30f893e4"
SRCREV_FORMAT = "last"
PV = "1.10.0+git${SRCPV}"
PKGV = "1.10.0+git${GITPKGV}"

RRECOMMENDS_${PN} = ""
