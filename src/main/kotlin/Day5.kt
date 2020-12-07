import java.lang.Math.round
import kotlin.math.roundToInt

fun main() {

    val lines = Utils.readFileAsLinesUsingUseLines("input-5")

    val seats = mutableListOf<Seat>()
    for(line in lines){
        val encodedRow = line.subSequence(0, 7).toString()
        val encodedColumn = line.subSequence(7, line.length).toString()

        val seat = Seat(getRow(encodedRow), getColumn(encodedColumn))
        seats.add(seat)
    }

    println(seats.maxByOrNull { it.seatId() }!!.seatId())


    // Part 2

    val seatIdsAndSeats = seats.map { Pair(it.seatId(), it) }
    val result = seatIdsAndSeats.sortedBy { it.first }
    result.forEach { println(it) }

    //  0 40
    println(result.filterIndexed { index, pair -> pair.first != index + 40 })

}

fun getColumn(encodedColumn: String)  = get(encodedColumn, 7, 0, "R")
fun getRow(encodedRow: String)  = get(encodedRow, 127, 0, "B")

fun get(encodedColumn : String, max : Int, min : Int, topVal : String) : Int{

//    println("####")
    var maxColumn = max
    var minColumn = min

    var column = 0
    for(c in encodedColumn){
        var range = ((maxColumn - minColumn).toDouble() / 2).roundToInt()
//        println("$minColumn $maxColumn $range $column")
        if(c.toString() == topVal){
            column += range
            minColumn += range
        }
        else{
            maxColumn -= range
        }
    }

    return column
}

data class Seat(val row: Int,val column: Int){
   fun seatId() = row * 8 + column
}