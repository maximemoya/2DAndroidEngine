package fr.mm.evolutiongamemax.interfaces

import android.graphics.Canvas
import android.view.MotionEvent

interface MaximeViewInterface {

    fun onDraw(canvas: Canvas)
    fun onTouchEvent(event: MotionEvent) : Boolean

}