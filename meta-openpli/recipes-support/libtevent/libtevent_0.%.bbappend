# Use cross-answers-mips file for mipsel to fix building error
ORIG_WAF_CROSS_ANSWERS_PATH := "${WAF_CROSS_ANSWERS_PATH}"
WAF_CROSS_ANSWERS_PATH = "${S}/patches"

do_configure_prepend () {
    cp ${ORIG_WAF_CROSS_ANSWERS_PATH}/cross-answers-mips.txt ${WAF_CROSS_ANSWERS_PATH}/cross-answers-mipsel.txt
}
