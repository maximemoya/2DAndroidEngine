package fr.mm.evolutiongamemax.bibliotheques

import android.content.Context
import android.graphics.Bitmap

class MyTileSetManager(
    context: Context,
    rDrawablePathImage: Int,
    val x_cell_count: Int,
    val y_cell_count: Int,
    var width: Int = 0,
    var height: Int = 0
) {

    val bitmap = MaximeToolsLayoutV1.setImageBitmap(context, rDrawablePathImage)
    val bufferLinesList = mutableListOf<MutableList<Bitmap>>()
    val bufferFullIdList = mutableListOf<Bitmap>()
    val listIdBlock = mutableListOf<Int>()

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

                if(width == 0 && height == 0){
                    width = _width
                    height = _height
                }
                else if (width != 0 && height != 0) {
                    resizedBitmap = MaximeToolsLayoutV1.setResizeBitmap(bitmap2, width, height)
                }
                else if (height != 0) {
                    resizedBitmap = MaximeToolsLayoutV1.setResizeBitmap(bitmap2, _width, height
                    )
                    width = _width
                }
                else{
                    resizedBitmap = MaximeToolsLayoutV1.setResizeBitmap(bitmap2, width, _height
                    )
                    height = _height
                }
                bitmapsByFrameList.add(resizedBitmap)
                bufferFullIdList.add(resizedBitmap)

            }
            bufferLinesList.add(bitmapsByFrameList)
        }

        listIdBlock.add(513)

    }

}