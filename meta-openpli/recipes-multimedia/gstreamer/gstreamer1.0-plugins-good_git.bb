DEFAULT_PREFERENCE = "1"

require gstreamer1.0-plugins-good.inc
include gstreamer1.0-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI = " \
    git://anongit.freedesktop.org/gstreamer/gst-plugins-good;branch=master;name=base \
"

SRCREV_base = "994b1ac3510731178777a1ad4c873ae1b011e4cc"

RRECOMMENDS_${PN} = ""
