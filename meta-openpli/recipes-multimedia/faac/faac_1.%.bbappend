SRC_URI_append_mipsel = " \
	file://fix-build-with-gcc6.patch \
	"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
