DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-Fix-crash-with-gst-inspect.patch \
	file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"

inherit gitpkgv
SRCREV_base = "850510f9e8d9f8356f9f9d9f50333f322615df47"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"
