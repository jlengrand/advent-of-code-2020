package final

import Utils

class Day1 {

    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            val lines = Utils.readFileAsLinesUsingUseLines("input-1").map { it.toInt() }

            fun part1(){
                for(line1 in lines)
                    for (line2 in lines)
                        if (line1 + line2 == 2020)
                        {
                            println(line1 * line2 )
                            return
                        }
            }
            part1()

            fun part2() {
                for (line1 in lines)
                    for (line2 in lines)
                        for (line3 in lines)
                            if (line1 + line2 + line3 == 2020) {
                                println(line1 * line2 * line3)
                                return
                            }
            }
            part2()
        }
    }
}