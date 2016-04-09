DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_base = "636aa20f283d576fff4c290b5c20effe86710be3"
PV = "1.9.0.1+git${SRCPV}"
PKGV = "1.9.0.1+git${GITPKGV}"
