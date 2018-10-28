import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import java.util.HashSet
import java.util.ArrayList



object Coins {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val `is` = FileInputStream(File("/Users/pedropaixao/Desktop/input01.txt"))
        System.setIn(`is`)
        runCoins(args)
    }
}


fun runCoins(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nm = scan.nextLine().split(" ")

    val n = nm[0].trim().toInt()

    val m = nm[1].trim().toInt()

    val coins = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val res = ways(n, coins.toCollection(ArrayList()))

    println(res)
}

// Complete the ways function below.
fun ways(n: Int, coins: ArrayList<Int>): Long {
    val DP = LongArray(n + 1) // O(N) space.
    DP[0] = 1.toLong()  // n == 0 case.
    for (coin in coins) {
        for (j in coin until DP.size) {
            // The only tricky step.
            DP[j] += DP[j - coin]
        }
    }
    return DP[n]
}