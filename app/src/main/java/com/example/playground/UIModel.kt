package com.example.playground

abstract class UIModel(
    val spanSize: Int,
    var top:Boolean=false,
    var bottom:Boolean=false,
    var left:Boolean=false,
    var right:Boolean=false,
    var horizontal:Boolean=false,
    var vertical:Boolean=false

)

class ListItem(val text: String, spanSize: Int) :
    UIModel(spanSize, )