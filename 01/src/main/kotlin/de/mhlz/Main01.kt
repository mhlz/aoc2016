package de.mhlz

import java.io.File

/**
 * @author Mischa Holz
 */

fun Pair<Int, Int>.turn(left: Boolean): Pair<Int, Int> {
    val (x, y) = this
    val fact = if(left) 1 else -1
    if(x == 0) {
        return Pair(-y * fact, 0)
    } else {
        return Pair(0, x * fact)
    }
}

fun main(args: Array<String>) {
    var (x, y) = Pair(0, 0)
    var dir = Pair(0, 1)

    val locations = mutableListOf(Pair(x, y))

    File("input.txt").readText().split(", ").forEach {
        val first = it[0]
        dir = dir.turn(first == 'L')

        val xDist = Math.abs(dir.first) * it.substring(1).toInt()
        val yDist = Math.abs(dir.second) * it.substring(1).toInt()
        for(xi in (0..xDist))
            for(yi in (0..yDist)) {
                if(xi == 0 && yi == 0) continue
                val loc = Pair(x + xi * dir.first, y + yi * dir.second)
                if(loc in locations)
                    println("Task 2: ${loc.first}, ${loc.second} -> ${Math.abs(loc.first) + Math.abs(loc.second)}")
                locations.add(loc)
            }

        x += xDist * dir.first
        y += yDist * dir.second

        locations.add(Pair(x, y))
    }

    println()
    println("Task 1: $x, $y -> ${Math.abs(x) + Math.abs(y)}")

}
