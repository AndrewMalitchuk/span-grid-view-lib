package com.example.playground

import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

class GridItemDecorator(
    val offsetTop: Int,
    val offsetRight: Int,
    val offsetBottom: Int,
    val offsetLeft: Int,
    val spacingHorizontal: Int,
    val spacingVertical: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        val item = (parent.adapter as? RecyclerViewAdapter)?.list?.get(position)

        val col = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 0
        val width = (parent.width)

        val realSize = width - (offsetLeft + offsetRight)

        val oneSpanSize = width / col
        val realOneSpanSize = realSize / col

        val oneColumnSize = oneSpanSize
        val realOneColumnSize = realOneSpanSize

        // movement for 1 column
        val delta = abs(oneColumnSize - realOneColumnSize)


        item?.let {

            // 1-2 == 45 dp == 120 px
            // 3-4 == 22 dp == 60 px


            val foo = col - (col - 2)

//            // фрактальное единство
//            outRect.apply {
//                left = delta * item.spanSize
//                right = (delta * item.spanSize * 2)
//            }


            // масштаб рабочий
            outRect.apply {
                left=delta*item.spanSize
                right=delta*item.spanSize
            }

            // screen width 411
            // 4 span width 365


            outRect.apply {
                //region масштаб
//                left = delta * item.spanSize
//                right = delta * item.spanSize
                //endregion

                //region сдвиг
//                left = (delta * item.spanSize) - (delta * item.spanSize)
//                right = delta * item.spanSize
                //endregion

                //region сдвиг + сдвиг с сумматором
//                val movement = (delta * item.spanSize) * it.order
//
//                Log.i("foo", "${item.text} ${(delta * item.spanSize).dp} | ${movement.dp}")
//                left = (delta * item.spanSize) - (delta * item.spanSize) - movement
//                right = delta * item.spanSize + movement
                //endregion

                //region сдвиг + сдвиг с сумматором + v2
//                var movement = (delta) * it.order
//                if (!it.right || (it.spanSize == col) || (it.order == 0)) {
//                    Log.i(
//                        "foo",
//                        "${item.text} s = ${it.spanSize} | o = ${it.order} | m = ${movement.dp}"
//                    )
//                    left = (delta * item.spanSize) - (delta * item.spanSize) - movement
//                    right = delta * item.spanSize + movement
//                } else {
//                    if ((it.right && it.locked) && (it.spanSize<col/2)) {
//
//
//                        movement =delta*(it.prevSum-(col-it.prevSum))
//
//                        left = (delta * item.spanSize) - (delta * item.spanSize) - movement
//                        right = delta * item.spanSize + movement
//
//                    } else {
//
//                        Log.i(
//                            "bar",
//                            "${item.text} s = ${it.spanSize} | o = ${it.order} | m = ${movement.dp}"
//                        )
//                        // 1 dp == 2 px
//                        right =
//                            (delta * item.spanSize) - (delta * item.spanSize) + (offsetLeft + offsetRight)
//                        left = delta * item.spanSize - (offsetLeft + offsetRight)
//                    }
//                }
                //endregion

                //region сдвиг відно правго краю
                var movement = (delta) * it.order

                if (!it.right || (it.spanSize == col) || (it.order == 0)) {
                    Log.i(
                        "foo",
                        "${item.text} s = ${it.spanSize} | o = ${it.order} | m = ${movement.dp}"
                    )
                    left = (delta * item.spanSize) - (delta * item.spanSize) - movement + offsetLeft
                    right = delta * item.spanSize + movement - offsetLeft+spacingHorizontal
                } else {
                    if ((it.right && it.locked) && (it.spanSize < col / 2)) {


                        movement = delta * (it.prevSum - (col - it.prevSum))

                        left =
                            (delta * item.spanSize) - (delta * item.spanSize) - movement + offsetLeft
                        right = delta * item.spanSize + movement - offsetLeft+spacingHorizontal

                    } else {

                        Log.i(
                            "bar",
                            "${item.text} s = ${it.spanSize} | o = ${it.order} | m = ${movement.dp}"
                        )
                        // 1 dp == 2 px
                        right =
                            (delta * item.spanSize) - (delta * item.spanSize) + offsetRight+ spacingHorizontal
                        left = delta * item.spanSize - offsetRight
                    }
                }
                //endregion

                //region
                if (it.vertical) {
                    outRect.top = spacingVertical
                    outRect.bottom = spacingVertical
                }
                if (it.top) {
                    outRect.top = offsetTop
                }
                if (it.bottom) {
                    outRect.bottom = offsetBottom
                }
                //endregion


            }


//            // масштаб
//            outRect.apply {
////                left=delta*item.spanSize
//                if (it.text == "4") {
//                    left = -(offsetLeft + offsetRight) / 2
//                    right = (delta * item.spanSize * 2) + (offsetLeft + offsetRight) / 2
//                } else {
//                    if (it.text == "5") {
//                        left = -(offsetLeft + offsetRight)
//                        right = (delta * item.spanSize * 2) + (offsetLeft + offsetRight)
//                    } else {
////                        if (it.text=="6"){
////                            left = -(offsetLeft + offsetRight)
////                            right = (delta * item.spanSize * 2)
////
////                        }else {
//                            right = delta * item.spanSize * 2
////                        }
//                    }
//                }
//
//
//            }


//            // One item on the row
//            if (it.left && it.right) {
//                outRect.left = offsetLeft
//                outRect.right = offsetRight
//            }
//            if (it.left && !it.right) {
//
////                outRect.left = offsetLeft
////                outRect.right = (-abs(offsetLeft - offsetRight) )
//
////                outRect.left = offsetLeft
////                outRect.right = -abs(offsetLeft - offsetRight) / (col / item.spanSize)
//            } else {
//                if (it.right && !it.left) {
////                    outRect.left = abs(offsetLeft - offsetRight)
////                    outRect.right = offsetRight
//
//
////                    outRect.left = abs(offsetLeft - offsetRight) / (col / item.spanSize)
////                    outRect.right = offsetRight
//                } else {
////                    if (it.horizontal) {
////                        it.text
////                        outRect.left = 30
////                        outRect.right = 0
//////                        outRect.right = 30
////                    }
//                }
//            }


        }


        // Works fine
//        item?.let {
//            if (it.left) {
//                outRect.left = offsetLeft
//            }
//            if (it.right) {
//                outRect.right = offsetRight
//            }
//            if (it.vertical) {
//                outRect.top = spacingVertical
//                outRect.bottom = spacingVertical
//            }
//            if (it.top) {
//                outRect.top = offsetTop
//            }
//
//
//            if (it.horizontal) {
//                if (it.right) {
//                    outRect.left = spacingHorizontal
//                } else {
//                    if (it.left) {
//                        outRect.right = spacingHorizontal
//                    } else {
//                        outRect.left = spacingHorizontal
//                        outRect.right = spacingHorizontal
//                    }
//                }
//            }
//            if (it.bottom) {
//                outRect.bottom = offsetBottom
//            }
//        }
    }

    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}