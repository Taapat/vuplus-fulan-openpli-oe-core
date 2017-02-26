DEFAULT_PREFERENCE = "1"

include gstreamer1.0-git-version.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = " \
    git://anongit.freedesktop.org/gstreamer/gst-plugins-base;branch=1.8;name=base \
    file://0003-ssaparse-enhance-SSA-text-lines-parsing.patch \
    file://encodebin-Need-more-buffers-in-output-queue-for-bett.patch \
"
SRC_URI_prepend = "git://anongit.freedesktop.org/gstreamer/gst-plugins-base;branch=master;name=base "
SRC_URI_append = " \
	file://taglist-not-send-to-down-stream-if-all-the-frame-cor.patch \
	file://0001-riff-media-added-fourcc-to-all-mpeg4-video-caps.patch \
	file://0001-riff-media-added-fourcc-to-all-ffmpeg-mpeg4-video-ca.patch \
	file://subparse-avoid-false-negatives-dealing-with-UTF-8.patch \
"

SRCREV_base = "b078f0ad4044831b81dfd88cb61ec0d8daf094c6"

PACKAGECONFIG = "\
 ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
 ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
 orc ivorbis ogg theora vorbis cdparanoia \
 "

RRECOMMENDS_${PN} = ""
