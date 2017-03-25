include gstreamer1.0-git-version.inc

SRC_URI_append = "\
    git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
"

SRCREV_common = "93ae13f2c3c58a4c2b7c111817b720a272d504d7"
SRCREV_FORMAT = "base"

S = "${WORKDIR}/git"

UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>(\d+(\.\d+)+))"

do_configure_prepend() {
	${S}/autogen.sh --noconfigure
}
