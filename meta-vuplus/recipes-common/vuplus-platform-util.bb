SECTION = "base"
LICENSE = "CLOSED"

PV="15.1"
SRCDATE = "20150623"
SRCDATE_PR = "r0"
PR="${SRCDATE}.${SRCDATE_PR}"

SRC_URI = " \
    http://archive.vuplus.com/download/build_support/vuplus/platform-util-${MACHINE}-${PV}-${PR}.tar.gz \
"

S="${WORKDIR}/platform-util-${MACHINE}"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/* ${D}${bindir}
}

do_package_qa() {
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN}="/"

inherit update-rc.d

INITSCRIPT_PARAMS = "start 65 S . stop 90 0 ."
INITSCRIPT_NAME = "${PN}"

SRC_URI += "file://${INITSCRIPT_NAME}.sysvinit"

do_install_append() {
        if [ -f ${WORKDIR}/${INITSCRIPT_NAME}.sysvinit ]; then
                install -d ${D}${INIT_D_DIR}
                install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME}.sysvinit ${D}${INIT_D_DIR}/${INITSCRIPT_NAME}
        fi
}

SRC_URI[md5sum] = "fc3e5049241ac2783686ad24f0f5ccde"
SRC_URI[sha256sum] = "2f9cb59565273ceeaadb19d35b86a5a14e1dcfdfb28acb592141bab38025c6f4"

