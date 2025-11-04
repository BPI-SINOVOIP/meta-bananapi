# install all kernel modules
IMAGE_INSTALL:append = " kernel-modules"

# wifi&bluetooth
IMAGE_INSTALL:append = " \
	packagegroup-base-wifi \
	packagegroup-base-bluetooth \
"

# rtl8821cu
IMAGE_INSTALL:append = " \
	rtl8821cu \
	linux-firmware-rtl8821cu \
"

SYSTEMD_AUTO_ENABLE:bluez5 = "enable"
SYSTEMD_AUTO_ENABLE:wpa-supplicant = "enable"

# bootscript
IMAGE_INSTALL:append = " \
	bootscript \
"

# systemd
IMAGE_INSTALL:append = " \
	rfkill-unblock \
	resize-helper \
"

# utils
IMAGE_INSTALL:append = " \
	u-boot-tools \
"

# initramfs
#INITRAMFS_IMAGE = "initramfs-image"
#INITRAMFS_IMAGE_BUNDLE = "1"
#do_image[depends] += "initramfs-image:do_mkimage_uinitrd"

