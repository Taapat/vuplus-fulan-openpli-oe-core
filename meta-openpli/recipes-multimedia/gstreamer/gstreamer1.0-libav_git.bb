DEFAULT_PREFERENCE = "1"

require gstreamer1.0-libav.inc
include gstreamer1.0-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d"

SRC_URI = " \
    git://anongit.freedesktop.org/gstreamer/gst-libav;branch=master;name=base \
"

SRCREV_base = "d3cb08dded4d7580b74d2c733453605f2d5e6bc7"

DEPENDS =+ " ffmpeg"

PACKAGECONFIG = "libav"

CFLAGS_append = " -Wno-deprecated-declarations "

LIBAV_EXTRA_CONFIGURE_COMMON_ARG =+ " \
  ${@bb.utils.contains('TARGET_FPU', 'soft', ' --disable-mipsfpu', ' --enable-mipsfpu', d)} \
  --disable-mipsdsp \
  --disable-mipsdspr2 \
"
