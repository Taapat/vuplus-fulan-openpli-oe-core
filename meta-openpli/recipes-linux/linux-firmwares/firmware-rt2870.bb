require linux-firmware.inc

DESCRIPTION = "Firmware for Ralink RT2870"

SRCREV = "bf9f8648fdf1d1d63db471554781f897d219bd62"

PR = "r1"

SRC_URI += " \
    file://RT2870STA.dat \
"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 rt2870.bin ${D}${base_libdir}/firmware
	install -d ${D}${sysconfdir}/Wireless/RT2870STA
	install -m 0644 ../RT2870STA.dat ${D}${sysconfdir}/Wireless/RT2870STA
}
