DESCRIPTION = "MME image library"

require ddt-apps.inc

PR = "r1"

DEPENDS += " jpeg"

do_install_append () {
	install -d ${D}${includedir}/libmmeimage
	install -m 644 ${S}/libmmeimage/*.h ${D}${includedir}/libmmeimage
}

FILES_${PN} += "${libdir}/libmmeimage.so"
FILES_${PN}-dev = "${includedir}/libmmeimage ${libdir}/libmmeimage.la"

INSANE_SKIP_${PN} += "dev-so"

