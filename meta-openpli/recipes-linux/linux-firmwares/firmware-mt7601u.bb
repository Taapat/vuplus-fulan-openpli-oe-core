DESCRIPTION = "Firmware for Ralink MT7601U"
LICENSE = "CLOSED"
PACKAGE_ARCH = "all"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git"

SRCREV = "${AUTOREV}"

SRC_URI += " \
    file://MT7601U.dat \
"

S = "${WORKDIR}/git"

FILES_${PN} += "${base_libdir}/firmware ${sysconfdir}/Wireless"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 mt7601u.bin ${D}${base_libdir}/firmware
	install -d ${D}${sysconfdir}/Wireless/MT7601U
	install -m 0644 ../MT7601U.dat ${D}${sysconfdir}/Wireless/MT7601U/RT2870STA.dat
}
