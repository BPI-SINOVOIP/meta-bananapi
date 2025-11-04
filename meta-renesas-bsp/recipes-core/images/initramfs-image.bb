SUMMARY = "Custom initramfs with fsck and resize2fs tools"
DESCRIPTION = "Initramfs image based on core-image-minimal-initramfs with extra tools"
LICENSE = "MIT"

require recipes-core/images/core-image-minimal-initramfs.bb

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "u-boot-mkimage-native"

PACKAGE_INSTALL:append = " \
    initramfs-module-fsck \
    initramfs-module-resizefs \
    e2fsprogs-e2fsck \
    e2fsprogs-resize2fs \
"

IMAGE_FSTYPES = "cpio.gz"

# remove from initramfs
IMAGE_BOOT_FILES = ""

do_mkimage_uinitrd() {
    if [ ! -f "${IMGDEPLOYDIR}/${IMAGE_NAME}.cpio.gz" ]; then
        bbfatal "Error: ${IMGDEPLOYDIR}/${IMAGE_NAME}.cpio.gz not found"
    fi

    install -d ${IMAGE_ROOTFS}/boot

    mkimage -A ${UBOOT_ARCH} -O linux -T ramdisk -C gzip \
        -n "Initramfs Image" \
        -d ${IMGDEPLOYDIR}/${IMAGE_NAME}.cpio.gz \
        ${IMAGE_ROOTFS}/boot/uInitrd

    install -d ${DEPLOY_DIR_IMAGE}
    install -m 0644 ${IMAGE_ROOTFS}/boot/uInitrd ${DEPLOY_DIR_IMAGE}
}

addtask do_mkimage_uinitrd after do_image_cpio before do_image_wic
do_mkimage_uinitrd[depends] += "${PN}:do_image_cpio"

