# We know about rtc
include conf/machine/include/build-extras.inc

BRAND_OEM = "fulan"

MACHINE_NAME = "${@base_contains('MACHINEBUILD', 'dynaspark7162', 'SPARK 7162' , 'SPARK', d)}"
MACHINE_BRAND = "${@base_contains('MACHINEBUILD', 'dynaspark7162', 'Dynavision' , 'Spark', d)}"

IMAGEDIR ?= "enigma2"
MTD_KERNEL = "mtd5"
MTD_ROOTFS = "mtd6"
KERNEL_FILE = "uImage"
ROOTFS_FILE = "e2jffs2.img"

MACHINE_KERNEL_PR = "r14"

TARGET_ARCH = "sh4"

GCCVERSION = "4.9%"

PREFERRED_PROVIDER_virtual/kernel = "linux-fulan"
PREFERRED_VERSION_linux-libc-headers = "2.6.32"
PREFERRED_VERSION_directfb = "1.4.12"
SERIAL_CONSOLE = "115200 ttyAS0"
#PREFERRED_VERSION_lirc ?= "0.9.0"

DVBPROVIDER = "kernel"

MACHINE_FEATURES = "alsa hdmicec switchoff kernel26 ext2 usbhost serial dvb-c singlecore no-nmap omb skins1080 directfb sdl"

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dtsdownmix --with-eac3"

KERNEL_IMAGETYPE = "uImage"
#KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"


JFFS2_ERASEBLOCK = "0x20000"
JFFS2_ENDIANNESS = "${@base_conditional('SITEINFO_ENDIANNESS', 'le', '--little-endian', '--big-endian', d)}"
EXTRA_IMAGECMD_jffs2 = "--pad ${JFFS2_ENDIANNESS} --eraseblock=${JFFS2_ERASEBLOCK} --no-cleanmarkers"
MKUBIFS_ARGS = "--pad ${JFFS2_ENDIANNESS} --eraseblock=${JFFS2_ERASEBLOCK} --no-cleanmarkers"

IMAGE_FSTYPES ?= "jffs2"

# enable this for debug
#IMAGE_FSTYPES += "tar.gz "
#IMAGE_FSTYPES += "spark71xx-usbimg "
#INHERIT += "usb_image-spark71xx"

INITRAMFS_FSTYPES = "ext2.gz spark-updt"

IMAGE_CMD_jffs2_prepend = " \
    rm -rf ${IMAGE_ROOTFS}/tmp/*; \
    "

IMAGE_CMD_jffs2_append = " ; \
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2 ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${ROOTFS_FILE}; \
    cp ${DEPLOY_DIR_IMAGE}/uImage ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.jffs2; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.jffs2; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -rf ${IMAGEDIR}; \
    "
	
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
    stlinux24-sh4-stx7111-fdma-firmware \
    stlinux24-sh4-stmfb-firmware-stx7111 \
    stslave-fw \
    ustslave \
    stfbcontrol \
    fp-control \
    stlirc \
    stinit \
    fulan-dvb-modules \
    mtd-utils-jffs2 \
    "

MACHINE_EXTRA_RRECOMMENDS = " \
    gstreamer1.0-plugin-dvbmediasink \
    enigma2-plugin-systemplugins-sh4osdadjustment \
    "

include conf/machine/include/autoload-filesystems.inc

require conf/machine/include/tune-sh4.inc

# add support for extra feeds
PACKAGE_ARCHS += " ${MACHINEBUILD}"

