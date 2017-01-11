# Commit 664ae3dc52fd7fc8c6f64e6cf5e70f97dedd332d in OE-core force-feeds
# bash into our system, which we definitely don't want to happen. This
# bbappend basically reverses that commit.
#
RDEPENDS_${PN}-client = "rpcbind"
RDEPENDS_${PN} = "${PN}-client"

SRC_URI_append_sh4 = " \
           file://fix-ipv6-redefination-on-old-linux.patch \
"

# The startup script does a check that doesn't work, replace it. It's
# also overly complex, so simplified it too.
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
