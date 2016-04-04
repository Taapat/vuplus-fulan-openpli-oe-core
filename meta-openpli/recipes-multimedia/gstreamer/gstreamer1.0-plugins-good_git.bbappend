DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://revert-matroska-demux-streams-have-DTS.patch \
"

inherit gitpkgv
SRCREV_base = "fd7964e7462544a0c120ebf1d4c8e4b0174a1518"
PV = "1.9.0.1+git${SRCPV}"
PKGV = "1.9.0.1+git${GITPKGV}"
PR = "r1"
