DESCRIPTION = "Library to play files in enigma2 using ffmpeg"

require ddt-apps.inc

DEPEND += "libass ffmpeg"
RDEPENDS_${PN} = "libass ffmpeg"

FILES_${PN}-dbg += "${bindir}/meta"

