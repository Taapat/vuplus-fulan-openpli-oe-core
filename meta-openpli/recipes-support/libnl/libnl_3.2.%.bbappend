FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_sh4 = " \
    file://add-linux-socket.patch \
    file://remove-in6-include.patch \
"
