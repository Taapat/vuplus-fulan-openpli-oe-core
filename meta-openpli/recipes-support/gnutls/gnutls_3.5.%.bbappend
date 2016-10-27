FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_sh4 = " \
    file://added-check-for-SYS_getrandom-being-defined.patch \
"
