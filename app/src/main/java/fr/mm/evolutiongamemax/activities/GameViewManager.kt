package fr.mm.evolutiongamemax.activities

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import fr.mm.evolutiongamemax.activities.menu.MenuView
import fr.mm.evolutiongamemax.interfaces.MaximeViewInterface

class GameViewManager(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object STATIC {

        abstract class ViewIDEnum {
            companion object {
                val MENU: Int = 0
            }
        }

        var actualViewID: Int = ViewIDEnum.MENU
        val viewList: MutableList<MaximeViewInterface> = mutableListOf()

        val screenX : Int = Resources.getSystem().displayMetrics.widthPixels
        val screenY : Int = Resources.getSystem().displayMetrics.heightPixels
        val screenRect : RectF = RectF(0f,0f, screenX.toFloat(), screenY.toFloat())
        val radiusCorner = screenY * 0.03f

    }

    init {

        // ADD ALL VIEWS TO VIEW LIST :
        viewList.add(MenuView(context))

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        viewList[actualViewID].onDraw(canvas)

        postInvalidateDelayed(25)


    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        viewList[actualViewID].onTouchEvent(event)

        return true
    }

}