package final

import Utils.Companion.readFileAsLinesUsingUseLines

val passwordRegexp = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".toRegex()

fun main(){
    val lines = readFileAsLinesUsingUseLines("input-2")
    val constraints = lines.map { ConstraintAndPassword.fromLine(it) }

    println(constraints.filter { it.satisfy1() }.size)
    println(constraints.filter { it.satisfy2() }.size)
}

data class ConstraintAndPassword(val min: Int, val max: Int, val letter: String, val password: String){
    fun satisfy1() = password.fold(0) {acc, p -> if (p.toString() == letter) acc +1 else acc } in min..max

    fun satisfy2() = (password[min - 1] == letter.single()).xor(password[max - 1] == letter.single())

    companion object{
        fun fromLine(line:String) : ConstraintAndPassword {
            return passwordRegexp.matchEntire(line)
                ?.destructured
                ?.let { (min, max, letter, password) ->
                    ConstraintAndPassword(min.toInt(), max.toInt(), letter, password)
                }
                ?: throw IllegalAccessException("Bad password input $line")
        }
    }
}