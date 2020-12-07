import java.io.File

class Day1 {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val lines = readFileAsLinesUsingUseLines(ClassLoader.getSystemResource("input-1").file)

            val sums = lines.map { line -> lines.map { l -> Triple(line.toInt() + l.toInt(), line.toInt(), l.toInt()) } }.flatten()

            val sums2020 = sums.filter { x -> x.first == 2020 }

            println(sums2020[0].second * sums2020[0].third)

            // Part 2
            val sums2 = lines.map {
                line -> lines.map {
                    l ->
                        lines.map { k -> Pair(line.toInt() + l.toInt() + k.toInt(), Triple(line, l, k))}
                }
            }.flatten().flatten()

            var res = sums2.filter { x -> x.first == 2020 }
            println(res[0].second)

        }

        private fun readFileAsLinesUsingUseLines(fileName: String): List<String>
                = File(fileName).useLines { it.toList() }
    }
}