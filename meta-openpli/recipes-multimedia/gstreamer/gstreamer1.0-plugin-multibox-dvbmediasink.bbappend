FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_sh4 = " \
    file://0001-dvbmediasink_sh4_fix.patch;patch=1 \
"

SRC_URI += " \
    file://0001-added-aactranscode-plugin_v2.patch;patch=1 \
"

FILES_${PN} += "${sysconfdir}/gstreamer/aactranscode"

