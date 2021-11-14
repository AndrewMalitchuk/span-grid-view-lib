package com.example.playground

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var gridColumn = 0

    fun genDummyData(): ArrayList<ListItem> {
        gridColumn = 4
        return ArrayList<ListItem>().apply {

            add(ListItem("3", 1))
            add(ListItem("4", 1))
            add(ListItem("5", 1))
            add(ListItem("6", 1))

            add(ListItem("20", 1))
            add(ListItem("21", 1))
            add(ListItem("22", 1))

            add(ListItem("25", 2))
            add(ListItem("26", 1))


            add(ListItem("a", 3))
            add(ListItem("b", 1))

            add(ListItem("c", 1))
            add(ListItem("d", 3))

            add(ListItem("0", 4))

            add(ListItem("1", 2))
            add(ListItem("2", 2))

            add(ListItem("3", 1))
            add(ListItem("4", 1))
            add(ListItem("5", 1))
            add(ListItem("6", 1))

            add(ListItem("7", 1))
            add(ListItem("8", 3))

            add(ListItem("9", 3))
            add(ListItem("10", 1))

            add(ListItem("11", 1))
            add(ListItem("12", 2))
            add(ListItem("13", 1))

            add(ListItem("14", 1))
            add(ListItem("15", 4))

            add(ListItem("16", 2))
            add(ListItem("17", 4))

            add(ListItem("18", 3))
            add(ListItem("19", 4))


            add(ListItem("23", 2))

            add(ListItem("24", 3))

            add(ListItem("27", 2))

            add(ListItem("28", 3))
            add(ListItem("29", 4))

            add(ListItem("30", 3))
            add(ListItem("31", 1))

            add(ListItem("32", 1))
            add(ListItem("33", 1))
            add(ListItem("34", 1))
            add(ListItem("35", 1))
            add(ListItem("36", 1))
            add(ListItem("37", 1))
            add(ListItem("38", 1))
            add(ListItem("39", 1))
            add(ListItem("40", 3))
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val result = genDummyData()
        foo(result, gridColumn)
        with(binding) {
            val recyclerViewAdapter =
                RecyclerViewAdapter(
                    result
                )
            rvList.apply {
                layoutManager = GridLayoutManager(this@MainActivity, gridColumn).apply {
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return result[position].spanSize
                        }
                    }
                }
                adapter = recyclerViewAdapter
                addItemDecoration(
                    GridItemDecorator(
                        offsetTop = 100,
                        offsetRight = 20,
                        offsetBottom = 250,
                        offsetLeft = 100,
                        spacingHorizontal = 20,
                        spacingVertical = 10
                    )
                )
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun foo(list: List<UIModel>, column: Int) {

        val result = ArrayList<Pair<Int, UIModel>>()

        var j = 0
        var i = 0
        var spanSizeSum = 0
        var previousSum = 0
        var previoudItem:UIModel?=null
        while (i < list.size) {
            val currentItem = list[i]


            var pair = Pair(j, currentItem)

            if ((column - spanSizeSum) >= 0) {

                if ((previousSum + currentItem.spanSize) > column) {
                    j++
                    pair = Pair(j, currentItem)
                    previoudItem?.prevSum=spanSizeSum
                    previoudItem?.locked=true
                    spanSizeSum = 0
                } else {
                    if ((spanSizeSum + currentItem.spanSize) > column) {
                        j++
                        pair = Pair(j, currentItem)
                        previoudItem?.prevSum=spanSizeSum
                        previoudItem?.locked=true
                        spanSizeSum = 0

                    }

                }

                result.add(pair)
                spanSizeSum += currentItem.spanSize
                previousSum = currentItem.spanSize
                previoudItem=currentItem
            }
            if (spanSizeSum == column) {
                spanSizeSum = 0
                j++
            }
            i++
        }

        val grouped = result.groupBy { it.first }
        grouped.forEach { (i, list) ->
            if (list.size == 1) {
                list[0].second.apply {
                    left = true
                    right = true

                    order=0
                }
            } else {
                list.first().second.left = true
                list.last().second.right = true

//                list.forEach {
                list.forEachIndexed { index, pair ->
//                    with(it.second) {
                    with(pair.second) {
                        order=index
                        if (!right) {
                            horizontal = true
                        }
                        if (!left) {
                            horizontal = true
                        }
                    }

                }

            }
        }

        // Top
        grouped[0]?.forEach {
            it.second.top = true
        }
        // Botom
        grouped[grouped.keys.last()]?.forEach {
            it.second.bottom = true
        }
        grouped.forEach { (_, list) ->

            list.forEach {
                it.second.vertical = true
            }

        }
        grouped


        result
    }

//    @RequiresApi(Build.VERSION_CODES.N)
//    fun bar(list: List<UIModel>, column: Int) {
//
//
//        // Num, Model, Weight
//        val result = ArrayList<Pair<Int, UIModel>>()
//        val resultFoo = ArrayList<Triple<Int, UIModel, Int>>()
//
//        var j = 0
//        var i = 0
//        var spanSizeSum = 0
//        var previousSum = 0
//        while (i < list.size) {
//            val currentItem = list[i]
//            var pair = Pair(j, currentItem)
//
//            if ((column - spanSizeSum) >= 0) {
//
//                if ((previousSum + currentItem.spanSize) > column) {
//                    j++
//                    pair = Pair(j, currentItem)
//                    spanSizeSum = 0
//                } else {
//                    if ((spanSizeSum + currentItem.spanSize) > column) {
//                        j++
//                        pair = Pair(j, currentItem)
//                        spanSizeSum = 0
//
//                    }
//
//                }
//
//                result.add(pair)
//                spanSizeSum += currentItem.spanSize
//                previousSum = currentItem.spanSize
//            }
//            if (spanSizeSum == column) {
//                spanSizeSum = 0
//                j++
//            }
//            i++
//        }
//
//        val grouped = result.groupBy { it.first }
//        grouped.forEach { (_, list) ->
//            if (list.size == 1) {
//                list[0].second.apply {
//                    left = true
//                    right = true
//                }
//            } else {
//                list.first().second.left = true
//                list.last().second.right = true
//
//                list.forEach {
//                    with(it.second) {
//                        if (!right) {
//                            horizontal = true
//                        }
//                        if (!left) {
//                            horizontal = true
//                        }
//                    }
//
//                }
//
//            }
//        }
//
//        // Top
//        grouped[0]?.forEach {
//            it.second.top = true
//        }
//        // Botom
//        grouped[grouped.keys.last()]?.forEach {
//            it.second.bottom = true
//        }
//        grouped.forEach { (_, list) ->
//
//            list.forEach {
//                it.second.vertical = true
//            }
//
//        }
//
//
//        result
//    }

}