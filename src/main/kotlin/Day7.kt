fun main() {

    val lines = Utils.readFileAsLinesUsingUseLines("input-7-test-2")

    val bagMaps = lines.fold(mutableMapOf<String, List<String>>()) { acc, line -> acc[getStartingBag(line)] = getEnclosedBags(line.split("contain")[1]); acc }

    println(bagMaps)
    println("#########")
    var bagFilter = listOf<String>("shiny gold")
    var results = mutableListOf<String>()
    var currentResults: List<String>
    do{
        currentResults = bagMaps.filter { containsOneOf(it.value, bagFilter) }.keys.toList()
        bagFilter = currentResults
        results.addAll(currentResults)
    }
        while (bagFilter.isNotEmpty())

    println(results.toSet().size)

    // Part 2
    val bagMapsWithColors = lines.fold(mutableMapOf<String, List<Bag>>()) { acc, line -> acc[getStartingBag(line)] = getEnclosedBagsWithcolor(line.split("contain")[1]); acc }
}



fun containsOneOf(values: List<String>, matches: List<String>) : Boolean{
    val intResult = values.map { Pair(it, matches) }
    return intResult.any { it.second.contains(it.first) }
}

fun getStartingBag(line:String): String {
    return line.split("bags")[0].trim()
}

fun getEnclosedBags(bagsList : String) : List<String> {
    if(bagsList == "no other bags") return listOf()
    return bagsList.split(",").map{ getBagColor(it)}
}

fun getEnclosedBagsWithcolor(bagsList : String) : List<Bag> {
    if(bagsList.contains("no other bags")) return listOf()
    return bagsList.split(",").map{ Bag(getBagColor(it), getBagNumber(it))}
}

fun getBagNumber(bagDescription: String): Int {
    return bagDescription.split("bag")[0].substring(0, 2).trim().toInt()
}

fun getBagColor(bagDescription : String) : String {
    println(bagDescription)
    return bagDescription.split("bag")[0].substring(3).trim()
}

data class Bag(val color: String, val number: Int)