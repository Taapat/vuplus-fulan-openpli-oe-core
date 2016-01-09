SUMMARY = "rtmpdump Real-Time Messaging Protocol"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${OPENPLI_BASE}/LICENSE;md5=eb723b61539feef013de476e68b5c50a"

DEPENDS = "openssl zlib"

inherit autotools-brokensep gitpkgv

SRCREV = "${AUTOREV}"
PKGV = "2.48+git${GITPKGV}"
PV = "2.48+git${SRCPV}"
PR = "r6"

SRC_URI = "git://github.com/oe-alliance/rtmpdump.git;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = " \
    CC='${CC}' LD='${LD} ${STAGING_LIBDIR}' \
    SYS=posix INC=-I=/usr/include DESTDIR=${D} \
    prefix=${prefix} libdir=${libdir} incdir=${includedir}/librtmp bindir=${bindir} mandir=${mandir}"


do_install() {
    install -d ${D}${bindir}
        install rtmpdump ${D}${bindir}/
}
