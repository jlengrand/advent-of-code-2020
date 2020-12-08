val regexp = "([a-z]{3}) ([+-][0-9]+)"

fun main() {
    val lines = Utils.readFileAsLinesUsingUseLines("input-8")
    val instructions = lines.mapIndexed { idx, line -> Instruction.fromString(line, idx ) }

//    val runInstructions = mutableListOf<Instruction>()
//    var accumulator = 0
//    var nextInstruction = instructions[0]
//    runInstructions.add(nextInstruction)

//    while (runInstructions.filter { it == nextInstruction }.size < 2){
//        when (nextInstruction.instructionType) {
//            INSTRUCTION_TYPE.nop -> {
//                nextInstruction = instructions[nextInstruction.index + 1]
//                runInstructions.add(nextInstruction)
//            }
//            INSTRUCTION_TYPE.jmp -> {
//                nextInstruction = instructions[nextInstruction.index + nextInstruction.value]
//                runInstructions.add(nextInstruction)
//            }
//            INSTRUCTION_TYPE.acc -> {
//                accumulator += nextInstruction.value
//                nextInstruction = instructions[nextInstruction.index + 1]
//                runInstructions.add(nextInstruction)
//            }
//        }
//    }

//    println(accumulator)

    // Part 2
    println(isInfinite(instructions))

    val indexes = instructions.filter { it.instructionType == INSTRUCTION_TYPE.jmp }.map { it.index }

    var startIndex = indexes.size - 1
    var mutableInstructions = mutableListOf<Instruction>()
    do{
        mutableInstructions = instructions.toMutableList()
        mutableInstructions[indexes[startIndex]] = Instruction(INSTRUCTION_TYPE.nop, mutableInstructions[indexes[startIndex]].value, mutableInstructions[indexes[startIndex]].index)
        startIndex -= 1

    }while(isInfinite(mutableInstructions) )

    println(mutableInstructions)

    var accumulator = 0
    var index = 0
    var nextInstruction = mutableInstructions[index]

    while (true){
        when (nextInstruction.instructionType) {
            INSTRUCTION_TYPE.nop -> {
                index += 1
                nextInstruction = mutableInstructions[index]
            }
            INSTRUCTION_TYPE.jmp -> {
                index += nextInstruction.value
                nextInstruction = mutableInstructions[index]
            }
            INSTRUCTION_TYPE.acc -> {
                accumulator += nextInstruction.value
                index += 1
                nextInstruction =  mutableInstructions[index]
            }
        }
        println(accumulator)
    }
}

fun isInfinite(instructions : List<Instruction>) : Boolean{

    try{
        val runInstructions = mutableListOf<Instruction>()
        var accumulator = 0
        var nextInstruction = instructions[0]
        runInstructions.add(nextInstruction)

        while (runInstructions.filter { it == nextInstruction }.size < 2){
            when (nextInstruction.instructionType) {
                INSTRUCTION_TYPE.nop -> {
                    nextInstruction = instructions[nextInstruction.index + 1]
                    runInstructions.add(nextInstruction)
                }
                INSTRUCTION_TYPE.jmp -> {
                    nextInstruction = instructions[nextInstruction.index + nextInstruction.value]
                    runInstructions.add(nextInstruction)
                }
                INSTRUCTION_TYPE.acc -> {
                    accumulator += nextInstruction.value
                    nextInstruction = instructions[nextInstruction.index + 1]
                    runInstructions.add(nextInstruction)
                }
            }
        }
        return true
    }
    catch(e : IndexOutOfBoundsException){
        return false
    }
}

enum class INSTRUCTION_TYPE {
    acc, jmp, nop
}

data class Instruction(val instructionType : INSTRUCTION_TYPE, val value: Int, val index: Int){
    companion object{
        fun fromString(input: String, index : Int) =
            regexp.toRegex().matchEntire(input)
                ?.destructured
                ?.let { (instruction, value) ->
                    Instruction(INSTRUCTION_TYPE.valueOf(instruction), value.toInt(), index)
                }
                ?: throw IllegalAccessException("Bad password input $input")

    }
}