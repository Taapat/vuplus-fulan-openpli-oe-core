DEFAULT_PREFERENCE = "1"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=1.10;name=base "

inherit gitpkgv
SRCREV_base = "5670d66a53fac20d039c3a85a70b643fe9309fa7"
PV = "1.10.1+git${SRCPV}"
PKGV = "1.10.1+git${GITPKGV}"

RRECOMMENDS_${PN} = ""
