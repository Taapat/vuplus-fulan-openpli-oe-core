require openpli-image.bb

BB_HASH_IGNORE_MISMATCH = "1"

KERNEL_WIFI_DRIVERS = " \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-zd1211 \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "firmware-rt3070 firmware-mt7601u" , "", d)} \
	\
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-ath" , "kernel-module-ath9k-htc", d)} \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-ar9170usb" , "kernel-module-carl9170", d)} \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-8712u" , "kernel-module-r8712u", d)} \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt73usb \
	kernel-module-rtl8187 \
	kernel-module-zd1211rw \
	"

EXTRA_KERNEL_WIFI_DRIVERS = " \
	firmware-rtl8192cu \
	\
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-8188eu" , "kernel-module-r8188eu", d)} \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-8192cu" , "kernel-module-rtl8192cu", d)} \
	"

EXTERNAL_WIFI_DRIVERS = " \
	firmware-rtl8192cu \
	\
	rtl8192cu \
	rtl8188eu \
	rtl8192eu \
	"

ENIGMA2_PLUGINS = " \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-pictureplayer \
	\
	enigma2-plugin-extensions-alternativesoftcammanager \
	enigma2-plugin-extensions-mtgplay \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-skins-metropolis-hd \
	\
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-skinselector \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	\
	${@bb.utils.contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "hdmicec", "enigma2-plugin-systemplugins-hdmicec" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "osdposition", "enigma2-plugin-systemplugins-osdpositionsetup" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "wlan", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "uianimation", "enigma2-plugin-systemplugins-animationsetup" , "", d)} \
	\
	${@bb.utils.contains('OPENPLI_FEATURES', 'ci', 'enigma2-plugin-systemplugins-commoninterfaceassignment', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', 'dvd', 'enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', 'fan', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
	${@bb.utils.contains('OPENPLI_FEATURES', '7seg', 'enigma2-plugin-systemplugins-vfdcontrol', '', d)} \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "kernel-module-block2mtd libcrypto enigma2-plugin-systemplugins-autovolume" , "enigma2-plugin-extensions-audiosync enigma2-plugin-extensions-blurayplayer enigma2-plugin-extensions-serviceapp exteplayer3", d)} \
	"

DEPENDS += " \
	enigma2 \
	enigma2-pliplugins \
	enigma2-plugins \
	"

IMAGE_INSTALL += " \
	aio-grab \
	enigma2 \
	enigma2-src \
	libavahi-client \
	libbluray \
	settings-autorestore \
	tuxbox-common \
	${ENIGMA2_PLUGINS} \
	\
	${@bb.utils.contains("MACHINE_FEATURES", "transcoding", "streamproxy", "", d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'ctrlrc', "enigma2-plugin-systemplugins-remotecontrolcode", "", d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'colorlcd', "enigma2-plugin-extensions-lcd4linux", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "kernelwifi", "${KERNEL_WIFI_DRIVERS}", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "extrakernelwifi", "${EXTRA_KERNEL_WIFI_DRIVERS}", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "externalwifi", "${EXTERNAL_WIFI_DRIVERS}", "", d)} \
	\
	${@bb.utils.contains('OPENPLI_FEATURES', 'dvd', 'cdtextinfo', '', d)} \
	"

export IMAGE_BASENAME = "openpli-enigma2"
