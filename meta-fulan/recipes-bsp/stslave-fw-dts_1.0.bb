DESCRIPTION = "STM ST-231 Coprocessor firmware"
LICENSE = "CLOSED"
SECTION = "base"

RPROVIDES_${PN} = "stslave-fw"
RREPLACES_${PN} = "stslave-fw-ac3"
RCONFLICTS_${PN} = "stslave-fw-ac3"

# fix architecture mismatch QA error
INSANE_SKIP_${PN} = "arch"

SRC_URI = "file://${MACHINE}/audio.elf \
    file://${MACHINE}/video.elf \
"

FILES_${PN} += "/boot"

do_install () {
    install -d ${D}/boot
    install -m 644 ${WORKDIR}/${MACHINE}/audio.elf  ${D}/boot
    install -m 644 ${WORKDIR}/${MACHINE}/video.elf  ${D}/boot
}

