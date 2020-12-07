import java.io.File

class Utils {

    companion object{
        internal fun readFileAsLinesUsingUseLines(resourceName: String): List<String>
                = File(ClassLoader.getSystemResource(resourceName).file).useLines { it.toList() }
    }
}