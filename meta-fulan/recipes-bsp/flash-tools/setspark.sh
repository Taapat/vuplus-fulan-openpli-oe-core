#!/bin/sh              
fw_setenv bootargs "console=ttyAS0,115200 rw ramdisk_size=6144 init=/linuxrc root=/dev/ram0 nwhwconf=device:eth0,hwaddr:00:80:E1:12:40:69 ip=${ipaddr}:${serverip}:${gatewayip}:${netmask}:lh:eth0:off stmmaceth=msglvl:0,phyaddr:1,watchdog:5000 bigphysarea=4000"
fw_setenv bootcmd "bootm  0x00080000"
fw_setenv boot_system "spark"
fw_setenv userfs_base "800000"
fw_setenv userfs_len "17800000"
fw_setenv kernel_base "0x00080000" 
fw_setenv kernel_name "spark/mImage"
fw_setenv userfs_name "spark/userfsub.img"
fw_setenv tftp_kernel_name "mImage"
fw_setenv tftp_userfs_name "userfsub.img"
reboot -f

