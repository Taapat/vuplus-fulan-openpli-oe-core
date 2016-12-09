DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = "git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;branch=1.8;name=base"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;branch=1.10;name=base "
SRC_URI_append = " \
	file://0001-rtmp-fix-seeking-and-potential-segfault.patch \
	file://mpegtsdemux-only-wait-for-PCR-when-PCR-pid.patch \
	file://hls-use-max-playlist-quality.patch \
"

SRCREV_base = "c4b50182687c08b503ccf1be22afb3963defe2bc"

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
"

python handle_hls_rename () {
    d.setVar('RPROVIDES_gstreamer1.0-plugins-bad-hls', 'gstreamer1.0-plugins-bad-fragmented')
}
