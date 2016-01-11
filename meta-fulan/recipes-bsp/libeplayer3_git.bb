DESCRIPTION = "Library to play files in enigma2 using ffmpeg"

require ddt-apps.inc

DEPENDS = "libass ffmpeg"
RDEPENDS_${PN} = "libass ffmpeg"

do_install_append () {
	install -d ${D}${includedir}/libeplayer3/include
	install -m 644 ${S}/libeplayer3/include/*.h ${D}${includedir}/libeplayer3/include
}

FILES_${PN}-dev += "${includedir}/libeplayer3/include"
FILES_${PN}-dbg += "${bindir}/meta"

