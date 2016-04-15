DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit gitpkgv
SRCREV_base = "46a3c9ac8b2f8182ef8f211f4be09a67e4d42a61"
PV = "1.9.0.4+git${SRCPV}"
PKGV = "1.9.0.4+git${GITPKGV}"
PR = "r2"

RRECOMMENDS_${PN} = ""
