package fr.mm.evolutiongamemax.bibliotheques

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import kotlin.math.floor

open class MyAnimBitmapSquareManager(
    val context: Context,
    rDrawablePathImage: Int,
    x_cell_count: Int,
    y_cell_count: Int,
    val width: Int = 0,
    val height: Int = 0,
    var speedAnimation: Int = 2
) {

    private val bitmap = MaximeToolsLayoutV1.setImageBitmap(context, rDrawablePathImage)
    private val bufferAnimationLinesList = mutableListOf<MutableList<Bitmap>>()
    private var frame = 0
    private var counterSpeedFrame = 0

    private val _width = bitmap.width / x_cell_count
    private val _height = bitmap.height / y_cell_count

    init {

        for (y in 0 until y_cell_count) {
            val bitmapsByFrameList = mutableListOf<Bitmap>()
            for (x in 0 until x_cell_count) {
                val bitmap2 = Bitmap.createBitmap(
                    bitmap, _width * x, _height * y,
                    _width, _height
                )
                var resizedBitmap = bitmap2
                if (width != 0 && height != 0) {
                    resizedBitmap = MaximeToolsLayoutV1.setResizeBitmap(bitmap2, width, height)
                } else if (width != 0) {
                    resizedBitmap = MaximeToolsLayoutV1.setResizeBitmap(bitmap2, width, _height
                    )
                } else if (height != 0) {
                    resizedBitmap = MaximeToolsLayoutV1.setResizeBitmap(bitmap2, _width, height
                    )
                }
                bitmapsByFrameList.add(resizedBitmap)
            }
            bufferAnimationLinesList.add(bitmapsByFrameList)
        }
    }

    fun drawLineAnimBitmaps(
        canvas: Canvas,
        lineOfAnimation: Int,
        posX: Float,
        posY: Float,
        paint: Paint? = null
    ) {

        if (lineOfAnimation == bufferAnimationLinesList.size) {
            Log.i(
                "MAXIME_INFO",
                "WARNING from class MAnimBitmapSquareManager{ -> drawLineAnimBitmaps() } => lineOfAnimation out of range /!\\"
            )
            return
        }

        if (frame == bufferAnimationLinesList[lineOfAnimation].size) {
            frame = 0
        }

        canvas.drawBitmap(bufferAnimationLinesList[lineOfAnimation][frame], posX, posY, paint)

        // regulate speed anim :
        counterSpeedFrame++
        if (counterSpeedFrame > speedAnimation) {
            frame++
            counterSpeedFrame = 0
        }

    }

    fun drawBitmap(
        canvas: Canvas,
        lineOfAnimation: Int,
        _frame: Int,
        posX: Float,
        posY: Float,
        paint: Paint? = null
    ) {

        if (lineOfAnimation == bufferAnimationLinesList.size || _frame == bufferAnimationLinesList[lineOfAnimation].size) {
            Log.i(
                "MAXIME_INFO",
                "WARNING from class MAnimBitmapSquareManager{ -> drawBitmap() } => frame out of range /!\\"
            )
            return
        }

        canvas.drawBitmap(bufferAnimationLinesList[lineOfAnimation][_frame], posX, posY, paint)

    }

    fun drawFullAnimBitmaps(canvas: Canvas, posX: Float, posY: Float, paint: Paint? = null) {

        if (frame == bufferAnimationLinesList.size * bufferAnimationLinesList[0].size) {
            frame = 0
        }

        val x = frame % bufferAnimationLinesList[0].size
        val y = floor(frame.toFloat() / bufferAnimationLinesList[0].size).toInt()

        canvas.drawBitmap(bufferAnimationLinesList[y][x], posX, posY, paint)

        // regulate speed anim :
        counterSpeedFrame++
        if (counterSpeedFrame > speedAnimation) {
            frame++
            counterSpeedFrame = 0
        }


    }

}