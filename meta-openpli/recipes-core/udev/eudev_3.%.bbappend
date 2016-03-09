FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://add-sh4.patch \
    file://udev-builtin-input_id.patch \
"
