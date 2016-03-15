DEPENDS := "${@oe_filter_out('udev', '${DEPENDS}', d)}"

RDEPENDS_${PN} = ""
