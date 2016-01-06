DESCRIPTION = "Mount spark root as usb."
LICENSE = "CLOSED"

PR = "r1"

RDEPENDS_${PN} = "kernel-module-yaffs"

SRC_URI = "file://mountspark"

FILES_${PN} = "${sysconfdir}"

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install ${WORKDIR}/mountspark ${D}${sysconfdir}/init.d
}

INITSCRIPT_NAME = "mountspark"
INITSCRIPT_PARAMS = "defaults"

inherit update-rc.d

