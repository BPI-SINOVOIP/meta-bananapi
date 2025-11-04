SUMMARY = "Rfkill Unblock Service"
DESCRIPTION = "Rfkill Unblock Service"
SECTION = "admin"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "file://rfkill-unblock.service"

inherit systemd

do_install() {
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/rfkill-unblock.service ${D}${systemd_system_unitdir}
}

SYSTEMD_SERVICE:${PN} = "rfkill-unblock.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
