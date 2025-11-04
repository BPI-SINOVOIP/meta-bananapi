UBOOT_ARCH ?= "arm64"

WKS_FILE ?= "bananapi-rz-image-mmc.wks"
IMAGE_NAME_SUFFIX = ""

ROOTFS_POSTPROCESS_COMMAND += "copy_boot_files_to_rootfs;"

copy_boot_files_to_rootfs() {
    install -d ${IMAGE_ROOTFS}/boot
    for f in ${IMAGE_BOOT_FILES}; do
        src=$(echo $f | cut -d';' -f1)
        dst=$(echo $f | cut -s -d';' -f2)
        [ -z "$dst" ] && dst=$(basename $src)
        if [ -f "${DEPLOY_DIR_IMAGE}/${src}" ]; then
            install -m 0644 "${DEPLOY_DIR_IMAGE}/${src}" "${IMAGE_ROOTFS}/boot/${dst}"
        elif [ -f "${DEPLOY_DIR_IMAGE}/${MACHINE}/${src}" ]; then
            install -m 0644 "${DEPLOY_DIR_IMAGE}/${MACHINE}/${src}" "${IMAGE_ROOTFS}/boot/${dst}"
        else
            bbwarn "Boot file $src not found"
        fi
    done
}
