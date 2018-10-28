import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

object Swaps {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val `is` = FileInputStream(File("/Users/pedropaixao/Desktop/input09.txt"))
        System.setIn(`is`)
        runSwaps(args)
    }
}


// Complete the minimumSwaps function below.
fun minimumSwaps(arr: Array<Int>): Int = quicksort(arr, 0, arr.size - 1)

fun quicksort(arr: Array<Int>, left: Int, right: Int): Int  {
    if (left >= right) {
        return 0
    }
    val pivot = arr[(left+right)/2]
    val (index, count) = partition(arr, left, right, pivot)
    val leftCount = quicksort(arr, left, index - 1)
    val rightCount = quicksort(arr, index, right)
    return leftCount + rightCount + count
}

fun partition(arr: Array<Int>, inputLeft: Int, inputRight: Int, pivot: Int): Pair<Int, Int> {
    var count = 0
    var left = inputLeft
    var right = inputRight
    while(left <= right) {
        while(arr[left] < pivot){
            left++
        }

        while(arr[right] > pivot) {
            right--
        }

        if(left <= right) {
            if(left != right) {
                swap(arr, left, right)
                count++
            }
            left++
            right--
        }
    }
    return left to count
}

fun swap(arr: Array<Int>, left: Int, right: Int) {
    val leftVal = arr[left]
    val rightVal = arr[right]
    arr[left] = rightVal
    arr[right] = leftVal
    //println("Swapped $leftVal with $rightVal")
}

fun runSwaps(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val res = minimumSwaps(arr)

    println(res)
}