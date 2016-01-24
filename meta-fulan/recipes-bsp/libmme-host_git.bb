DESCRIPTION = "MME image library"

require ddt-apps.inc

PR = "r1"

EXTRA_OECONF =+ " --with-multicom=${STAGING_KERNEL_DIR}/multicom"

FILES_${PN} += "${libdir}/libmme_host.so"
FILES_${PN}-dev = "${libdir}/libmme_host.la"

INSANE_SKIP_${PN} += "dev-so"

