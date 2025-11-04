FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \
    file://fsck \
    file://resizefs \
"

do_install:append() {
    install -m 0755 ${WORKDIR}/fsck ${D}/init.d/00-fsck
    install -m 0755 ${WORKDIR}/resizefs ${D}/init.d/01-resizefs
}

PACKAGES:append = " \
    initramfs-module-fsck \
    initramfs-module-resizefs \
"

SUMMARY:initramfs-module-fsck = "Filesystem check for partitions"
RDEPENDS:initramfs-module-fsck = "${PN}-base e2fsprogs-e2fsck"
FILES:initramfs-module-fsck = "/init.d/00-fsck"

SUMMARY:initramfs-module-resizefs = "Resize system partition"
RDEPENDS:initramfs-module-resizefs = "${PN}-base e2fsprogs-resize2fs"
FILES:initramfs-module-resizefs = "/init.d/01-resizefs"
