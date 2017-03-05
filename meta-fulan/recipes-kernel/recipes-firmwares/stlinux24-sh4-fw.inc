LICENSE = "CLOSED"

SRC_URI = "http://archive.stlinux.com/stlinux/2.4/updates/RPMS/sh4/${STLINUX_FW_FILE_NAME}"

INC_PR = "r5"

S = "${WORKDIR}/opt/STM/STLinux-2.4/devkit/sh4/target"

inherit allarch

# fix architecture mismatch QA error
INSANE_SKIP_${PN} = "arch"

do_unpack() {
	rpm2cpio.sh ${DL_DIR}/${STLINUX_FW_FILE_NAME} | cpio -di
}

do_compile() {
	:
}

PACKAGES = "${PN}"
FILES_${PN} += "${base_libdir}/firmware"
