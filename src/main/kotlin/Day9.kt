import kotlin.system.exitProcess

fun main() {

    val lines = Utils.readFileAsLinesUsingUseLines("input-9")
    val intLines = lines.map { it.toLong() }

    val preamble = 5
    var index = 0
    do {
        val numbers = intLines.subList(index, index + preamble)
        index +=1
    }
        while (isSumOf(numbers, intLines[index + preamble - 1]))
    println(intLines[index + preamble - 1])

    // Part 2
    val answer1 = 393911906
//    val answer1 = 127

    var groupSize = 2
    while(true){
        println("##### $groupSize")
        for(i in 0..intLines.size-groupSize){
            val sublist = intLines.subList(i, i + groupSize)
            if(sublist.sum() == answer1.toLong()){
                println(sublist)
                println(sublist.minOrNull()!! + sublist.maxOrNull()!!)
                exitProcess(0)
            }
        }
        groupSize += 1
    }



}


fun isSumOf(numbers: List<Long>, theNumber: Long) : Boolean {

    val allCombinations = mutableListOf<Long>()
    for(number in numbers){
        for(number2 in numbers){
            if(number != number2){
                allCombinations.add(number + number2)
            }
        }
    }

    return theNumber in allCombinations
}