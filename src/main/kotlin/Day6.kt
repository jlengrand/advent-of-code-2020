fun main() {

    val lines = Utils.readFileAsLinesUsingUseLines("input-6")

    val groups = mutableListOf<List<String>>()

    var group = mutableListOf<String>()
    for(line in lines){
        if(line.isNotEmpty()){
            group.add(line)
        }
        else{
            groups.add(group)
            group = mutableListOf()
        }
    }
    groups.add(group)

    val setsSizes = groups.map { x -> x.map { it.toList()}.flatten().toSet().size }
    println(setsSizes.sumBy { it })

    // Part 2
    val groupsOfAnswers = groups.map { x ->  Pair(x.size, x.map { it.toList()}.flatten()) }
    val yolo = groupsOfAnswers.map { x -> Pair(x.first, x.second.groupingBy { it }.eachCount()) }

    val yoloCounted = yolo.map { x -> Pair(x.first, x.second.filter { it.value == x.first}) }
    val result = yoloCounted.sumBy { it.second.size }

    println(result)

}