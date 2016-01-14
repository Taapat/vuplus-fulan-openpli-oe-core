require linux-firmware.inc

DESCRIPTION = "Firmware for Ralink RT3070"

SRCREV = "bf9f8648fdf1d1d63db471554781f897d219bd62"

SRC_URI += " \
    file://RT3070STA.dat \
"

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 0644 rt3070.bin ${D}${base_libdir}/firmware
	install -d ${D}${sysconfdir}/Wireless/RT3070STA
	install -m 0644 ../RT3070STA.dat ${D}${sysconfdir}/Wireless/RT3070STA
}
