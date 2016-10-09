DEFAULT_PREFERENCE = "1"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;name=last \
"

inherit gitpkgv
SRCREV_last = "628af4f091f7c08ce8551cb80a9eed808cf2ed6f"
SRCREV_FORMAT = "last"
PV = "1.9.9+git${SRCPV}"
PKGV = "1.9.9+git${GITPKGV}"

RRECOMMENDS_${PN} = ""
