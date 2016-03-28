DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_base = "c2507ac86e2ee81f160192771a8ec44ded9b4a1a"
PV = "1.9.0.1+git${SRCPV}"
PKGV = "1.9.0.1+git${GITPKGV}"
