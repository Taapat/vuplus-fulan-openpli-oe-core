DEFAULT_PREFERENCE = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_prepend = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-base;name=last \
	file://taglist-not-send-to-down-stream-if-all-the-frame-cor.patch \
	file://0001-riff-media-added-fourcc-to-all-mpeg4-video-caps.patch \
	file://0001-riff-media-added-fourcc-to-all-ffmpeg-mpeg4-video-ca.patch \
	file://subparse-avoid-false-negatives-dealing-with-UTF-8.patch \
"

inherit gitpkgv
SRCREV_last = "9c3043470e9a97b8e6f694d6551897108c936339"
SRCREV_FORMAT = "last"
PV = "1.10.0+git${SRCPV}"
PKGV = "1.10.0+git${GITPKGV}"

PACKAGECONFIG = "\
 ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
 ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
 orc ivorbis ogg theora vorbis cdparanoia \
 "

RRECOMMENDS_${PN} = ""
