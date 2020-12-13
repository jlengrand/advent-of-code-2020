package final

fun main() {
    val lines = Utils.readFileAsLinesUsingUseLines("input-5")
    val rowsAndCols = lines.map {
        Seat(
            toBinary(it.take(7)),
            toBinary(it.drop(7)),
        )
    }

    println(rowsAndCols.maxByOrNull { it.seatId }!!.seatId)

    // Part 2
    val sortedRowsAndCols = rowsAndCols.sortedBy { it.seatId }
    println(sortedRowsAndCols
        .zipWithNext().first { it.first.seatId + 1 != it.second.seatId  }.first.seatId + 1)
}

fun toBinary(seatVal : String) = seatVal
    .replace("F|L".toRegex(), "0")
    .replace("B|R".toRegex(), "1")
    .toInt(2)

data class Seat(val row: Int,val column: Int){
    val seatId : Int = row * 8 + column
}