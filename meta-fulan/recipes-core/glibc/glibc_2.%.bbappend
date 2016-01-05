# Try fix errors on building with gcc 5.3 on sh4
SRC_URI_append_sh4 = " \
    file://gcc5-sh4-fix.patch \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

