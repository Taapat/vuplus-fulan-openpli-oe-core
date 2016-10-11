DESCRIPTION = "STM Specific script on enter or end of standby"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"

PR = "r2"

SRC_URI = "file://vdstandby"

FILES_${PN} = "/bin/vdstandby"

do_install () {
    install -d ${D}/bin
    install -m 755 ${WORKDIR}/vdstandby ${D}/bin
}
