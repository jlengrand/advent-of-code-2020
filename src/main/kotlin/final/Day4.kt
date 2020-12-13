package final

import Utils.Companion.readFileAsLinesUsingUseLines

private val regex = "byr:(\\d{4}) (cid:[0-9]+ )?ecl:(amb|blu|brn|gry|grn|hzl|oth) eyr:(\\d{4}) hcl:#([a-f\\d]{6}) hgt:([0-9]{3}cm|[0-9]{2}in) iyr:(\\d{4}) pid:(\\d{9})".toRegex()
private val mandatoryFields = listOf("byr", "ecl", "eyr", "hcl", "hgt", "iyr", "pid")

class Day4 {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val passportLines = toPassportLines(readFileAsLinesUsingUseLines("input-4")).map { toSortedPassportData(it) }

            println(passportLines.filter {  containsAll(it, mandatoryFields) }.size)
            // Part 2
            println(passportLines.filter { try {toPassportData(it); true} catch (ex: Exception) { false } }.size)
        }
    }
}

fun containsAll(line: String, values : List<String>) = values.all { line.contains(it) }

fun toSortedPassportData(line : String) = line.split(" ").sortedBy { d -> d.substring(0, 2) }.joinToString(" " )

fun toPassportLines(lines: List<String>) =
    lines
        .flatMapIndexed { index, x ->
            when {
                index == 0 || index == lines.lastIndex -> listOf(index)
                x.isEmpty() -> listOf(index - 1, index + 1)
                else -> emptyList()
            }
        }
        .windowed(size = 2, step = 2) { (from, to) -> lines.slice(from..to) }
        .map { it.joinToString(" ") }

fun toPassportData(line:String) =
    regex.matchEntire(line)
        ?.destructured
        ?.let { (byr, cid, ecl, eyr, hcl, hgt, iyr, pid) ->
            PassportData(byr.toInt(), iyr.toInt(), eyr.toInt(), hgt, hcl, ecl, pid)
        }
        ?: throw IllegalAccessException("Bad input $line")

data class PassportData(val byr: Int, val iyr: Int, val eyr: Int, val hgt: String, val hcl: String, val ecl: String, val pid: String){
    init {
        require(byr in 1920..2002)
        require(iyr in 2010..2020)
        require(eyr in 2020..2030)
    }
}