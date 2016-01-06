DESCRIPTION = "Tools for changing internal flash"
LICENSE = "CLOSED"

PR = "r3"

SRC_URI = "file://fw_env.config \
           file://fw_printenv \
           file://fw_setenv \
           file://setspark.sh \
           file://flash_erase \
          "

FILES_${PN} = "/var/* /bin/* /sbin/*"

do_install () {
    install -d ${D}/var
    install -m 644 ${WORKDIR}/fw_env.config ${D}/var/
    install -d ${D}/bin
    install -m 755 ${WORKDIR}/fw_printenv ${D}/bin/
    install -m 755 ${WORKDIR}/fw_setenv ${D}/bin/
    install -m 755 ${WORKDIR}/setspark.sh ${D}/bin/
    install -d ${D}/sbin
    install -m 755 ${WORKDIR}/flash_erase ${D}/sbin/
}
