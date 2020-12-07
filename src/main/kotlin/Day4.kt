import java.io.File

class Day4 {

    companion object{
        enum class ECL{
            amb, blu, brn, gry, grn, hzl, oth
        }

        private fun readFileAsLinesUsingUseLines(resourceName: String): List<String>
                = File(ClassLoader.getSystemResource(resourceName).file).useLines { it.toList() }

        private fun isValid(line: String) =
             line.contains("byr:")
                     && line.contains("iyr:")
                     && line.contains("eyr:")
                     && line.contains("hgt:")
                     && line.contains("hcl:")
                     && line.contains("ecl:")
                     && line.contains("pid:")

        private fun getValueOrNull(keyList :List<String>, key:String) : String? =
            if(keyList.none { it.contains(key) }) null
            else keyList.filter { it.contains(key) }[0].replace(key,"")


        private fun toPassportData(line:String): PassportData {
            val kandv = line.split(" ").map { it.trim() }

            val byr = getValueOrNull(kandv, "byr:")
            val iyr = getValueOrNull(kandv, "iyr:")
            val eyr = getValueOrNull(kandv, "eyr:")
            val hgt = getValueOrNull(kandv, "hgt:")
            val hcl = getValueOrNull(kandv, "hcl:")
            val ecl = getValueOrNull(kandv, "ecl:")
            val pid = getValueOrNull(kandv, "pid:")
            return PassportData(
                byr,
                iyr,
                eyr,
                hgt,
                hcl,
                ecl,
                pid
            )
        }

        private fun isValid(passportData: PassportData) : Boolean {
            return try{
                ((passportData.byr!=null && passportData.byr.toInt() >= 1920 && passportData.byr.toInt() <= 2002)
                        && (passportData.iyr!=null && passportData.iyr.toInt() >= 2010 && passportData.iyr.toInt() <= 2020)
                        && (passportData.eyr!=null && passportData.eyr.toInt() >= 2020 && passportData.eyr.toInt() <= 2030)
                        && (passportData.hgt!=null && validHeight(passportData.hgt))
                        && (passportData.hcl!=null && validHairColor(passportData.hcl))
                        && (passportData.ecl!=null && validEyeColor(passportData.ecl))
                        && (passportData.pid!=null && validPid(passportData.pid)))
            } catch (e: Exception ){
                false
            }
        }

        private fun validPid(pid: String): Boolean {
            val number = pid.toInt()
            return pid.length == 9
        }

        private fun validEyeColor(ecl: String): Boolean {
            val theColor =  ECL.valueOf(ecl)
            return true
        }

        private fun validHairColor(hcl: String): Boolean {
            return (hcl.length == 7
                    && hcl[0].toString() == "#"
                    && hcl.subSequence(1, hcl.length).all {
                    x -> x.isDigit() || x.toString() == "a" || x.toString() == "b" || x.toString() == "c" || x.toString() == "d" || x.toString() == "e" || x.toString() == "f"
            }
                )
        }

        private fun validHeight(hgt: String) : Boolean{
            val cmOrIn = hgt.subSequence(hgt.length - 2, hgt.length)

            if(cmOrIn == "cm"){
                val value = hgt.subSequence(0, 3).toString().toInt()
                return value in 150..193
            }
            else if(cmOrIn == "in"){
                val value = hgt.subSequence(0, 2).toString().toInt()
                return value in 59..76
            }
            else{
                return false

            }
        }

        @JvmStatic
        fun main(args: Array<String>) {

            val lines = readFileAsLinesUsingUseLines("input-4")

            val data = mutableListOf<String>()
            var currentData = ""
            for(line in lines){
                if(line.isEmpty()) {
                    data.add(currentData)
                    currentData = ""
                }
                else{
                    currentData = "$currentData $line"
                }
            }
            data.add(currentData)

            println(data.filter { isValid(it) }.size)

            // Part 2
            println(data.map { toPassportData(it) }.filter { isValid(it) }.size)

        }
    }
}

data class PassportData(val byr: String?, val iyr: String?, val eyr: String?, val hgt: String?, val hcl: String?, val ecl: String?, val pid: String?)