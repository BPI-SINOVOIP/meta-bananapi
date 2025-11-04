SUMMARY = "U-boot boot scripts for Bananapi board"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "u-boot-mkimage-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
    file://boot.cmd.${MACHINE} \
    file://env.txt.${MACHINE} \
"

do_compile:append() {
    mkimage -A ${UBOOT_ARCH} -T script -C none -n "Boot script" -d "${WORKDIR}/boot.cmd.${MACHINE}" boot.scr
}

do_install:append() {
    install -d ${D}/boot
    install -m 0644 boot.scr ${D}/boot/boot.scr
    install -m 0644 ${WORKDIR}/boot.cmd.${MACHINE} ${D}/boot/boot.cmd
    install -m 0644 ${WORKDIR}/env.txt.${MACHINE} ${D}/boot/env.txt
}

do_deploy:append() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${D}/boot/boot.scr ${DEPLOYDIR}/boot.scr
    install -m 0644 ${D}/boot/boot.cmd ${DEPLOYDIR}/boot.cmd
    install -m 0644 ${D}/boot/env.txt ${DEPLOYDIR}/env.txt
}

FILES:${PN} += "/boot"
