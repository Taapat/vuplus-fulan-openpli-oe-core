DEFAULT_PREFERENCE = "1"

include gstreamer1.0-plugins-ugly.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068 "

S = "${WORKDIR}/git"

SRCREV = "7e2f2f9e5b0b0da16fad50f23d184a273d025386"
SRC_URI = "git://anongit.freedesktop.org/gstreamer/gst-plugins-ugly;branch=master"

inherit gitpkgv
PV = "1.7.1+git${SRCPV}"
PKGV = "1.7.1+git${GITPKGV}"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

