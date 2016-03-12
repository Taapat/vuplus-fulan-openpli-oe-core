# Get the cross-answers from cross-execute
CROSS_METHOD = "exec"

# Creates an empty cross-answers file to fix building error
WAF_CROSS_ANSWERS_PATH = "${S}/patches"

do_configure_prepend () {
    echo '' >> ${WAF_CROSS_ANSWERS_PATH}/cross-answers-${TARGET_ARCH}.txt
}
