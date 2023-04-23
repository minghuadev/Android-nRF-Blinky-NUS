package no.nordicsemi.android.blinky.ble.data

import no.nordicsemi.android.ble.data.Data

class LedData private constructor() {

    companion object {
        fun from(value: Boolean): Data {
            /*
            return Data.opCode(if (value) 0x01 else 0x00)
             */
            var v_str = "/NUSSET 0\n"
            if (value) {
                v_str = "/NUSSET 1\n"
            }
            val v_byte = v_str.toByteArray()
            return Data(v_byte)
        }
    }

}