package com.example.playground

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

        val foo=realSize/2/2


        item?.let {
            // One item on the row
            if(it.left && it.right){
                outRect.left=offsetLeft
                outRect.right=offsetRight
            }
            if(it.left && !it.right){
                outRect.left=offsetLeft
                outRect.right=-abs(offsetLeft-offsetRight)/item.spanSize
            }else {
                if (it.right && !it.left) {
                    outRect.left = abs(offsetLeft - offsetRight) / item.spanSize
                    outRect.right = offsetRight
                }
            }


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
}