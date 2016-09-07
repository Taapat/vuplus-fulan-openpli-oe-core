DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;name=last \
"

inherit gitpkgv
SRCREV_last = "92075e0256584b3ce22ff00d4c7a835ab296ddb0"
SRCREV_FORMAT = "last"
PV = "1.9.2.1+git${SRCPV}"
PKGV = "1.9.2.1+git${GITPKGV}"

RRECOMMENDS_${PN} = ""
