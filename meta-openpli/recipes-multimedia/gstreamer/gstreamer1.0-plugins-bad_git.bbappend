DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

DEPENDS += " libdca"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM_remove = " \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50 \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;branch=master;name=base "
SRC_URI_append = " \
	file://0001-rtmp-fix-seeking-and-potential-segfault.patch \
	file://mpegtsdemux-only-wait-for-PCR-when-PCR-pid.patch \
	file://hls-use-max-playlist-quality.patch \
"

SRCREV_base = "ce9c82af471db588ef5d47a38bdb1581a641e542"

PACKAGECONFIG = "\
 ${PACKAGECONFIG_GL} \
 ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
 ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez', '', d)} \
 ${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)} \
 orc curl neon sndfile \
 hls sbc dash bz2 smoothstreaming \
 faac faad libmms dash webp rtmp \
 "

RRECOMMENDS_${PN} = ""

EXTRA_OECONF += " \
    --enable-dts \
    --enable-mpegdemux \
"
