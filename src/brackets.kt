import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InvalidObjectException
import java.util.*
import kotlin.collections.ArrayList

object Brackets {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val `is` = FileInputStream(File("/Users/pedropaixao/Desktop/input01.txt"))
        System.setIn(`is`)
        runBrackets(args)
    }
}

fun runBrackets(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val t = scan.nextLine().trim().toInt()
    for (tItr in 1..t) {
        val expression = scan.nextLine()
        val isVal = isValid(expression)
        if(isVal) println("YES") else println("NO")
    }
}

data class Stack(val chars: ArrayList<String> = ArrayList()) {
    fun push(char: String) = chars.add(char)
    fun pop(): String? = if(chars.isNotEmpty()) chars.removeAt(chars.size-1) else null
    fun isEmpty(): Boolean = chars.isEmpty()
}




fun isValid(expression: String): Boolean {
    val myStack = Stack()
    for(chara in expression.toCharArray()) {
        val char = chara.toString()
        if(char == "(" || char == "[" || char == "{") {
            myStack.push(char)
        } else {
            if(myStack.pop() != inverse(char)){
                return false
            }
        }
    }
    return myStack.isEmpty()
}

fun inverse(char: String): String {
    return when(char) {
        "[" -> "]"
        "(" -> ")"
        "{" -> "}"
        "}" -> "{"
        ")" -> "("
        "]" -> "["
        else -> throw InvalidObjectException("Invalid Char: $char")
    }
}

