package no.nordicsemi.android.blinky.spec

import java.util.UUID

class BlinkySpec {

    companion object {
        /*
        val BLINKY_SERVICE_UUID: UUID = UUID.fromString("00001523-1212-efde-1523-785feabcd123")
        val BLINKY_BUTTON_CHARACTERISTIC_UUID: UUID = UUID.fromString("00001524-1212-efde-1523-785feabcd123")
        val BLINKY_LED_CHARACTERISTIC_UUID: UUID = UUID.fromString("00001525-1212-efde-1523-785feabcd123")
         */

        val BLINKY_UUID_SVC: UUID =UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e") /* nordic nus service */
        val BLINKY_UUID_RD: UUID = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e") /* rd out of nus peripheral */
        val BLINKY_UUID_WR: UUID = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e") /* wr into nus peripheral */

        val BLINKY_SERVICE_UUID: UUID = BLINKY_UUID_SVC
        val BLINKY_BUTTON_CHARACTERISTIC_UUID: UUID = BLINKY_UUID_RD
        val BLINKY_LED_CHARACTERISTIC_UUID: UUID = BLINKY_UUID_WR
    }

}