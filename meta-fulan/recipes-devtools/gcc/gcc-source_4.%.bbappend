# Remove support for building musl configuration on sh4
SRC_URI_remove_sh4 = "\
    file://0067-gcc-musl-support.patch \
    file://0068-musl-dynamic-linker.patch \
    file://0069-musl-no-fixincludes.patch \
    file://0070-libstdc-musl.patch \
"

SRC_URI_append_sh4 = "\
    file://gcc-4.9.3-stm.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
