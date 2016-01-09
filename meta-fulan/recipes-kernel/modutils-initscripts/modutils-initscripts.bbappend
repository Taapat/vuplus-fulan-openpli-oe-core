SRC_URI_append_spark = "\
    file://spark_modutils.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
