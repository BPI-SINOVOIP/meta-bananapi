FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

KERNEL_DEVICETREE_OVERLAYS = " \
        renesas/overlay/bpi-ai2n-i2c0-mux.dtbo \
        renesas/overlay/bpi-ai2n-spi0.dtbo \
        renesas/overlay/bpi-ai2n-i2c1.dtbo \
        renesas/overlay/bpi-ai2n-i2c1-flash.dtbo \
        renesas/overlay/bpi-ai2n-i2c1-rtc.dtbo \
        renesas/overlay/bpi-ai2n-i2c2.dtbo \
        renesas/overlay/bpi-ai2n-i2s3-nxez-pcm512x.dtbo \
        renesas/overlay/bpi-ai2n-i2s3-waveshare-wm8960.dtbo \
        renesas/overlay/bpi-ai2n-pwm.dtbo \
        renesas/overlay/bpi-ai2n-pwmfan.dtbo \
        renesas/overlay/bpi-ai2n-spi2.dtbo \
        renesas/overlay/bpi-ai2n-spi2-flash.dtbo \
        renesas/overlay/bpi-ai2n-spi2-waveshare-st7789v.dtbo \
        renesas/overlay/bpi-ai2n-spi2-waveshare-tft24.dtbo \
        renesas/overlay/bpi-ai2n-spi2-waveshare-tft35c.dtbo \
        renesas/overlay/bpi-ai2n-uart0.dtbo \
        renesas/overlay/bpi-ai2n-uart2.dtbo \
"

SRC_URI:append:bananapi-ai2n = " \
	file://bananapi-ai2n.cfg \
	file://0001-add-bananapi-ai2n-board.patch \
	file://0002-add-bananapi-ai2n-dtb-overlay.patch \
"

do_compile:append() {
	if [ -n "${KERNEL_DTC_FLAGS}" ]; then
		export DTC_FLAGS="${KERNEL_DTC_FLAGS}"
	fi

	for dtbf in ${KERNEL_DEVICETREE_OVERLAYS}; do
		dtb=`normalize_dtb "$dtbf"`
		oe_runmake $dtb CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" OBJCOPY="${KERNEL_OBJCOPY}" STRIP="${KERNEL_STRIP}" ${KERNEL_EXTRA_ARGS}
	done
}

do_install:append() {
	install -d ${D}/${KERNEL_DTBDEST}/overlay
	install -m 0644 ${B}/arch/arm64/boot/dts/renesas/overlay/*.dtbo ${D}/${KERNEL_DTBDEST}/overlay/
}

do_deploy:append() {
	install -d $deployDir/overlay
	install -m 0644 ${D}/${KERNEL_DTBDEST}/overlay/*dtbo $deployDir/overlay/
}
