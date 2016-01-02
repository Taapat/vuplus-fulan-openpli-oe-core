# Remove support for building musl configuration on spark
MUSL_PATCHES = "\
    file://0067-gcc-musl-support.patch \
    file://0068-musl-dynamic-linker.patch \
    file://0069-musl-no-fixincludes.patch \
    file://0070-libstdc-musl.patch \
"

SRC_URI_remove_spark = "${MUSL_PATCHES}"
SRC_URI_remove_spark7162 = "${MUSL_PATCHES}"

