DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_base = "3bd5aeac52a7e88860270a3e4a460409d68b50cd"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"
