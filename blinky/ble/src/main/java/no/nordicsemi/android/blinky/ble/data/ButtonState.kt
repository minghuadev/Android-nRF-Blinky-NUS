package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice

class ButtonState: ButtonCallback() {
    var state: String = "<unknown_false_button_state>"

    override fun onButtonStateChanged(device: BluetoothDevice, state: String) {
        this.state = state
    }
}