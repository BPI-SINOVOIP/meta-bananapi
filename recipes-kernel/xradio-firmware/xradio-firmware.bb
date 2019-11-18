DESCRIPTION = "Xradio xr819 WiFi firmware"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=0ceb3372c9595f0a8067e55da801e4a1"

PV = "1.0"
PR = "r0"

SRCREV = "bddd21b7f895be9f0c37e435f0a7ac84405c6091"

COMPATIBLE_MACHINE = "orange-pi-zero"

SRC_URI = "git://github.com/armbian/firmware.git;protocol=https"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${base_libdir}/firmware/xr819
    install -m 0644 ${S}/xr819/boot_xr819.bin ${D}${base_libdir}/firmware/xr819/
    install -m 0644 ${S}/xr819/sdd_xr819.bin ${D}${base_libdir}/firmware/xr819/
    install -m 0644 ${S}/xr819/fw_xr819.bin ${D}${base_libdir}/firmware/xr819/
}

FILES_${PN} = "${base_libdir}/*"

PACKAGES = "${PN}"
