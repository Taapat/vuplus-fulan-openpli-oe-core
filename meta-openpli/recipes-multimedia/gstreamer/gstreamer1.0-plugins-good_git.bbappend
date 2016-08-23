DEFAULT_PREFERENCE = "1"

SRC_URI += " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;name=last \
"

inherit gitpkgv
SRCREV_last = "1ef896b29d52f9505ba26f25a2505849ae568bba"
SRCREV_FORMAT = "last"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"

RRECOMMENDS_${PN} = ""
