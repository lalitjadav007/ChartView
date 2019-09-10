package `in`.thejadav.chartview

import kotlin.random.Random

object ChartHelper {
    fun generateColor(): String {
        val newColor = Random.nextInt(0x1000000)
        return String.format("#%06X", newColor)
    }
}