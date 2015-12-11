DEFAULT_PREFERENCE = "1"

include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"
SRC_URI = " \
	git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=master \
	file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
	file://0001-fix-compile-error-261pay.patch \
"

inherit gitpkgv
PV = "1.7.0+git${SRCPV}"
PKGV = "1.7.0+git${GITPKGV}"

do_configure_prepend() {
	cd ${S}
	./autogen.sh --noconfigure
	cd ${B}
}

