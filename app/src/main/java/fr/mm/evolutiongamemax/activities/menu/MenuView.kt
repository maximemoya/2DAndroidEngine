package fr.mm.evolutiongamemax.activities.menu

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import fr.mm.evolutiongamemax.R
import fr.mm.evolutiongamemax.activities.GameViewManager
import fr.mm.evolutiongamemax.bibliotheques.*
import fr.mm.evolutiongamemax.interfaces.MaximeViewInterface

class MenuView(val context: Context) : MaximeViewInterface {

    companion object{
        var posViewX = 0f
        var posViewY = 0f
    }

    /*
    VARIABLES :
     */

    val paint = Paint(1)
    var color1 = Color.argb(255, 100, 100, 150)
    var color2 = Color.argb(255, 200, 100, 50)
    var width = 0
    var height = 0
    var rectFullScreen = Rect(0, 0, 0, 0)
    var rectSky = Rect(0, 0, 0, 0)
    var rectGround = Rect(0, 0, 0, 0)

    val playerBitmap = MyAnimBitmapSquareManager(
        context = context,
        rDrawablePathImage = R.drawable.linkformatted,
        x_cell_count = 8,
        y_cell_count = 9,
        width = (GameViewManager.screenY * 0.2f).toInt(),
        height = (GameViewManager.screenY * 0.3f).toInt(),
        speedAnimation = 2
    )

    val player = MySpritePlayerClass(
        bitmap = playerBitmap,
        configSpriteSheet = MySpriteSheetManager.LINKCONFIG
    )

    val map = MyMapManager(
        MyTileSetManager(
            context = context,
            R.drawable.terrain_atlas,
            32,
            32
        )
    )

    init {
        paint.color = Color.argb(255, 50, 50, 50)
        map.createLand(20,12,12,6,0f,0f)
        val thread = Thread(Runnable { onThreadMethod() })
        thread.start()

    }

    override fun onDraw(canvas: Canvas) {
        if (width == 0) {
            width = canvas.width
            height = canvas.height
            rectFullScreen.set(0, 0, width, height)
            rectSky.set(0, 0, width, height * 3 / 4)
            rectGround.set(0, height * 3 / 4, width, height)
        }

        canvas.drawColor(color1)
        map.onDrawAllMapSlotList(canvas = canvas)
        player.onDraw(canvas = canvas)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        player.onTouchEvent(event)
        return true
    }

    fun onThreadMethod(){

        val i = 0
//        while(true){
//            Thread.sleep(100)
//        }

    }

}