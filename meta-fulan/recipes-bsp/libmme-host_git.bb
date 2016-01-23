DESCRIPTION = "MME image library"

require ddt-apps.inc

EXTRA_OECONF =+ " --with-multicom=${STAGING_KERNEL_DIR}/multicom"

