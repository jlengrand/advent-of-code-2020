package final

import Utils

fun hasTree(line:String, column:Int) : Boolean {
    return line[column % line.length].toString() == "#"
}

fun processForest(step: Int, forest: List<String>) : Int{
    return forest.drop(1).fold(Pair(0, step)) {
        acc, line -> Pair(acc.first + (if (hasTree(line, acc.second)) 1 else 0), acc.second + step)
    }.first
}

fun main() {
    val lines = Utils.readFileAsLinesUsingUseLines("input-3")
    println(processForest(3, lines))

    println(processForest(1, lines)
        * processForest(3, lines)
        * processForest(5, lines)
        * processForest(7, lines)
        * processForest(1, lines.filterIndexed { idx, _ -> idx % 2 == 0 })
    )
}