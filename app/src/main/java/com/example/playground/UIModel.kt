package com.example.playground

abstract class UIModel(
    val spanSize: Int,
    var top:Boolean=false,
    var bottom:Boolean=false,
    var left:Boolean=false,
    var right:Boolean=false,
    var horizontal:Boolean=false,
    var vertical:Boolean=false,

    var order:Int,
    var locked:Boolean=false,
    var prevSum:Int=0,

)

class ListItem(val text: String, spanSize: Int,order: Int=0) :
    UIModel(spanSize, order=order)