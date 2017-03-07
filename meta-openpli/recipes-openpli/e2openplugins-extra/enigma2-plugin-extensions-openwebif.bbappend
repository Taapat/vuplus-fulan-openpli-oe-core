python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/public/themes/.*$', 'enigma2-plugin-%s-themes', '%s (Additional themes for OpenWebif)', recursive=True, match_path=True, prepend=True, extra_depends="${PN}")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/public/webtv/.*$', 'enigma2-plugin-%s-webtv', '%s (WebTV for OpenWebif)', recursive=True, match_path=True, prepend=True, extra_depends="${PN}")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/public/vxg/.*$', 'enigma2-plugin-%s-vxg', '%s (WebTV for Google Chrome)', recursive=True, match_path=True, prepend=True, extra_depends="${PN}-webtv")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.pexe$', 'enigma2-plugin-%s-vxg', '%s (WebTV support for Google Chrome)', recursive=True, match_path=True, prepend=True, extra_depends="${PN}-webtv")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', '%s (source files)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)
}

INSANE_SKIP_${PN} += "build-deps"
INSANE_SKIP_${PN}-terminal += "build-deps"
INSANE_SKIP_${PN}-vxg += "build-deps"

# Required empty packages for build compatibility with distros still using OWIF 0.x.y - 1.0.z
PACKAGES =+ "${PN}-terminal ${PN}-themes ${PN}-webtv"
RDEPENDS_${PN}-terminal = "${PN}"
RDEPENDS_${PN}-themes = "${PN}"
RDEPENDS_${PN}-webtv = "${PN}"
ALLOW_EMPTY_${PN}-terminal = "1"
ALLOW_EMPTY_${PN}-themes = "1"
ALLOW_EMPTY_${PN}-webtv = "1"
