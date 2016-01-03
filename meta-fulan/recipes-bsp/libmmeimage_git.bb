DESCRIPTION = "MME image library"

require ddt-apps.inc

DEPEND += "jpeg"

do_install_append () {
	install -d ${D}${includedir}/libmmeimage
	install -m 644 ${S}/libmmeimage/*.h ${D}${includedir}/libmmeimage
}

FILES_${PN}-dev += "${includedir}/libmmeimage"

