DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;name=last \
	file://0001-rtmp-fix-seeking-and-potential-segfault.patch \
	file://mpegtsdemux-only-wait-for-PCR-when-PCR-pid.patch \
	file://hls-use-max-playlist-quality.patch \
	file://0002-mpegtsdemux-do-not-abort-playback-when-no-PCR-were-f.patch \
"

inherit gitpkgv
SRCREV_last = "dd118c4737a41a45e6f2d6ffbf5783a6b9c7fee3"
SRCREV_FORMAT = "last"
PV = "1.9.9+git${SRCPV}"
PKGV = "1.9.9+git${GITPKGV}"

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

python handle_hls_rename () {
    d.setVar('RPROVIDES_gstreamer1.0-plugins-bad-hls', 'gstreamer1.0-plugins-bad-fragmented')
}
