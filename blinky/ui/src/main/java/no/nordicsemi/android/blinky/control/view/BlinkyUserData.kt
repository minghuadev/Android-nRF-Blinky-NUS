package no.nordicsemi.android.blinky.control.view


class BlinkyUserData(
    var sendFirst: Long = 0,
    var sendLast: Long = 0,
    var recvLast: Long = 0
) {
    fun do_send(): Unit {
        val nowMillis = System.currentTimeMillis()
        if ( sendFirst == 0L ) {
            sendFirst = nowMillis
        }
        sendLast = nowMillis
    }
    fun do_recv(): Unit {
        val nowMillis = System.currentTimeMillis()
        recvLast = nowMillis
    }
    fun get_trip(): Long {
        if ( sendFirst == 0L ) {
            return -1L
        }
        if ( recvLast == 0L ) {
            return -2L
        }
        if ( recvLast < sendLast ) {
            return -3L
        }
        return recvLast - sendLast
    }
    fun clear_data(): Unit {
        sendFirst = 0
        sendLast = 0
        recvLast = 0
    }
}

