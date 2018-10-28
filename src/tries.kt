import java.util.*
import kotlin.collections.HashMap
import java.io.File
import java.io.FileInputStream
import java.io.IOException

object Tries {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val `is` = FileInputStream(File("/Users/pedropaixao/Desktop/input02.txt"))
        System.setIn(`is`)
        runTries(args)
    }
}

data class Node(val chars: HashMap<Char, Node> = HashMap(), var isWord: Boolean = false, var nrWords: Int  = 0) {
    fun contains(char: Char): Boolean = chars.contains(char)
    fun get(char: Char): Node? = chars[char]
}

val rootNode = Node()

fun runTries(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val n = scan.nextLine().trim().toInt()
    for (nItr in 1..n) {
        val opContact = scan.nextLine().split(" ")

        val op = opContact[0]
        val contact = opContact[1]

        if(op == "add") {
            addWord(contact)
        } else if( op == "find") {
            println(calculateTotalRemainingWords(contact))
        }
    }
}

fun addWord(word: String) {
    var pivotNode: Node = rootNode
    val charArr = word.toCharArray()
    for(i in 0..(charArr.size - 1)) {
        val char = charArr[i]
        pivotNode = pivotNode.get(char) ?: addNode(char, pivotNode)
        pivotNode.nrWords += 1
    }
    pivotNode.isWord = true
}

fun addNode(char: Char, parentNode: Node): Node {
    val newNode = Node()
    parentNode.chars[char] = newNode
    return newNode
}

fun calculateTotalRemainingWords(word: String): Int {
    var pivotNode: Node? = rootNode
    val charArr = word.toCharArray()
    for(i in 0..(charArr.size - 1)) {
        val char = charArr[i]
        pivotNode = pivotNode!!.get(char)
        if(pivotNode == null) return 0
    }
    return pivotNode!!.nrWords
}