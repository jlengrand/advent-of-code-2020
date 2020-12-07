import Utils.Companion.readFileAsLinesUsingUseLines

class Day2 {

    companion object{
        private fun toConstraintAndPassword(candp: String) : Pair<Constraint, String>{
            val res = candp.split(":")

            val constraintinfo = res[0].split(" ")
            val constraint = Constraint(constraintinfo[0].split("-")[0].toInt(), constraintinfo[0].split("-")[1].toInt(), constraintinfo[1])

            return Pair(constraint, res[1].trim())
        }

        private fun satisfyConstraint(constraint: Constraint, password: String): Boolean{
            val numberOfLetters = password.fold(0) {acc, p -> if (p.toString() == constraint.letter) acc +1 else acc }

            return numberOfLetters <= constraint.max && numberOfLetters >= constraint.min
        }


        private fun satisfyConstraint2(constraint: Constraint, password: String): Boolean{
            val isOne = password[constraint.min - 1] == constraint.letter.single()
            val isTwo = password[constraint.max - 1] == constraint.letter.single()

            return (isOne && !isTwo) || (isTwo && !isOne)
        }


        @JvmStatic
        fun main(args: Array<String>) {

            val lines = readFileAsLinesUsingUseLines("input-2")
            val candps = lines.map { x -> toConstraintAndPassword(x) }

            println(candps.filter { x -> satisfyConstraint(x.first, x.second) }.size)
            println(candps.filter { x -> satisfyConstraint2(x.first, x.second) }.size)
        }
    }
}
data class Constraint(val min: Int, val max: Int, val letter: String)