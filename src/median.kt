import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

object Median {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val `is` = FileInputStream(File("/Users/pedropaixao/Desktop/input01.txt"))
        System.setIn(`is`)
        runMedian(args)
    }
}

fun runMedian(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val minheap = Heap()
    val maxheap = Heap(true)

    for (i in 0 until n) {
        val aItem = scan.nextLine().trim().toInt()
        //a[i] = aItem
        addValue(aItem, minheap, maxheap)
        println(getMedian(minheap, maxheap))
    }
}

class Heap(val max: Boolean = false) {
    private val arr: ArrayList<Int> = ArrayList()

    fun getParentIndex(index: Int): Int = index / 2

    fun getLeftChildIndex(index: Int): Int = index * 2

    fun getRightChildIndex(index: Int): Int = index * 2 + 1

    fun add(value: Int) {
        arr.add(value)
        heapifyUp()
    }

    fun printArr() = println("${if(max) "MAX" else "MIN"} - ${arr.joinToString()}")

    fun swap(sourceIndex: Int, targetIndex: Int) {
        val sourceVal = arr[sourceIndex]
        val targetVal = arr[targetIndex]
        arr[sourceIndex] = targetVal
        arr[targetIndex] = sourceVal
    }

    fun heapifyUp(index: Int = arr.lastIndex) {
        val parentIndex = getParentIndex(index)
        val lastValue = arr[index]
        if((!max && lastValue < arr[parentIndex]) || (max && lastValue > arr[parentIndex])){
            swap(index, parentIndex)
            heapifyUp(parentIndex)
        }
    }

    fun heapifyDown(index: Int = 0) {
        val leftIndex = getLeftChildIndex(index)
        val rightIndex = getRightChildIndex(index)
        val leftValue = if(arr.size > leftIndex) arr[leftIndex] else null
        val rightValue = if(arr.size > rightIndex) arr[rightIndex] else null

        val myValueIndex =
        if(leftValue != null && rightValue != null && ((!max && leftValue > rightValue) || (max && leftValue < rightValue))) {
            rightIndex
        } else if(leftValue != null){
            leftIndex
        } else null

        val value = arr[index]

        if(myValueIndex != null && ((!max && value > arr[myValueIndex]) || (max && value < arr[myValueIndex]) )){
            swap(index, myValueIndex)
            heapifyDown(myValueIndex)
        }
    }

    fun poll(): Int {
        val output = arr[0]
        arr[0]= arr[arr.lastIndex]
        arr.removeAt(arr.lastIndex)
        heapifyDown()
        return output
    }

    fun peek(): Int = arr[0]
    fun size(): Int = arr.size
    fun getRoot(): Int = arr[0]
}

fun addValue(value: Int, minHeap: Heap, maxHeap: Heap) {
    if( maxHeap.size() == 0 || value < maxHeap.peek()) {
        maxHeap.add(value)
    } else {
        minHeap.add(value)
    }
    balanceHeaps(minHeap, maxHeap)
}

fun balanceHeaps(minHeap: Heap, maxHeap: Heap) {
    if(minHeap.size() - maxHeap.size() >= 2) {

        maxHeap.add(minHeap.poll())
    } else if(maxHeap.size() - minHeap.size() >= 2) {
        minHeap.add(maxHeap.poll())
    }
}

fun getMedian(minHeap: Heap, maxHeap: Heap): Double {
    return when {
        minHeap.size() == maxHeap.size() -> (minHeap.getRoot().toDouble() + maxHeap.getRoot().toDouble())/2
        minHeap.size() > maxHeap.size() -> minHeap.getRoot().toDouble()
        else -> maxHeap.getRoot().toDouble()
    }
}