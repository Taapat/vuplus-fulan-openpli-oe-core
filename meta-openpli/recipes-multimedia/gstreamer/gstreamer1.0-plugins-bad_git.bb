DEFAULT_PREFERENCE = "1"

include gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"
SRC_URI = "git://anongit.freedesktop.org/gstreamer/gst-plugins-bad;branch=master"
SRC_URI += "file://0001-rtmp-fix-seeking-and-potential-segfault.patch"

inherit gitpkgv
PV = "1.7.0+git${SRCPV}"
PKGV = "1.7.0+git${GITPKGV}"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

