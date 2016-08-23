DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_base = "1e1ea38b8eabedc97a9a6629246c9371fc1d7858"
PV = "1.9.1.1+git${SRCPV}"
PKGV = "1.9.1.1+git${GITPKGV}"
