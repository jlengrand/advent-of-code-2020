fun processLine(line : String, acc :Pair<Int, Int>, step:Int) : Pair<Int, Int>{
    val foundSpot = line[acc.second % line.length].toString()
    val numberTrees= if (foundSpot == "#") acc.first + 1 else acc.first
    return Pair( numberTrees  , acc.second + step)
}

fun main() {
    val lines = Utils.readFileAsLinesUsingUseLines("input-3")

    val lessLines = lines.subList(1, lines.size)
    val result11 = lessLines.fold(Pair(0, 1)) {
            acc , line ->
        final.processLine(line, acc, 1)
    }

    val result31 = lessLines.fold(Pair(0, 3)) {
            acc , line ->
        final.processLine(line, acc, 3)
    }

    val result51 = lessLines.fold(Pair(0, 5)) {
            acc , line ->
        final.processLine(line, acc, 5)
    }

    val result71 = lessLines.fold(Pair(0, 7)) {
            acc , line ->
        final.processLine(line, acc, 7)
    }

    val evenLessLines = lines.filterIndexed { idx, _ -> idx % 2 == 0 }
    val theLeastLines = evenLessLines.subList(1, evenLessLines.size)

    val result12 = theLeastLines.fold(Pair(0, 1)) {
            acc , line ->
        final.processLine(line, acc, 1)
    }

    println(result31.first)
    println("======")
    println(result11.first * result12.first * result31.first * result51.first * result71.first)
}