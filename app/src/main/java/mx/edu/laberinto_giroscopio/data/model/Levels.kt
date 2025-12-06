package mx.edu.laberinto_giroscopio.data.model

object Levels {
    val level1 = arrayOf(
        intArrayOf(1,1,1,1,1,1,1,1),
        intArrayOf(1,2,0,0,1,0,3,1),
        intArrayOf(1,0,1,0,1,0,0,1),
        intArrayOf(1,0,1,0,0,0,1,1),
        intArrayOf(1,0,0,1,1,0,0,1),
        intArrayOf(1,1,0,0,0,0,1,1),
        intArrayOf(1,1,1,1,1,1,1,1)
    )
    val level2 = arrayOf(
        intArrayOf(1,1,1,1,1,1,1,1),
        intArrayOf(1,2,0,0,0,1,3,1),
        intArrayOf(1,0,1,1,0,1,0,1),
        intArrayOf(1,0,0,1,0,0,0,1),
        intArrayOf(1,1,0,0,1,1,0,1),
        intArrayOf(1,0,0,1,0,0,0,1),
        intArrayOf(1,1,1,1,1,1,1,1)
    )
    val level3 = arrayOf(
        intArrayOf(1,1,1,1,1,1,1,1),
        intArrayOf(1,2,0,0,0,1,3,1),
        intArrayOf(1,0,1,1,0,1,0,1),
        intArrayOf(1,0,0,1,0,0,0,1),
        intArrayOf(1,1,0,0,1,1,0,1),
        intArrayOf(1,0,0,1,0,0,0,1),
        intArrayOf(1,1,1,1,1,1,1,1)
    )
    val allLevels = listOf(level1, level2, level3)
}
