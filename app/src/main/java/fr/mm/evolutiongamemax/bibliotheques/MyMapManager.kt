package fr.mm.evolutiongamemax.bibliotheques

import android.graphics.Canvas
import android.graphics.RectF
import android.util.Log
import fr.mm.evolutiongamemax.activities.GameViewManager
import fr.mm.evolutiongamemax.activities.menu.MenuView.Companion.posViewX
import fr.mm.evolutiongamemax.activities.menu.MenuView.Companion.posViewY
import java.lang.Exception
import kotlin.math.abs

class MyMapManager(val tileset: MyTileSetManager) {

    companion object{
        var cellX: Int = 0
        var cellY: Int = 0
        var sizeX: Float = 0f
        var sizeY: Float = 0f
        val mapSlotList: MutableList<MyMapSlot> = mutableListOf()
        var posViewLastX = 0f
        var posViewLastY = 0f
        lateinit var tileset : MyTileSetManager

        fun setRectByPos(posX:Float, posY:Float){
            for (i in 0 until MyMapManager.mapSlotList.size) {
                    MyMapManager.mapSlotList[i].rect.left = posX + (i % cellX-1) * sizeX
                    MyMapManager.mapSlotList[i].rect.right = MyMapManager.mapSlotList[i].rect.left + sizeX
                    MyMapManager.mapSlotList[i].rect.top = posY + (i / cellY) * sizeY
                    MyMapManager.mapSlotList[i].rect.bottom = MyMapManager.mapSlotList[i].rect.top + sizeY
            }
        }

    }
    //    val tilesetList = tileset.bufferLinesList
    init {
        MyMapManager.tileset = tileset
    }

    fun createLand16x16On8x4ScreenSize() {

        val sizeX = GameViewManager.screenX.toFloat() / 8
        val sizeY = GameViewManager.screenY.toFloat() / 4
        val posStartX = -((16 - 8) / 2) * sizeX
        val posStartY = -((16 - 4) / 2) * sizeY

        for (y in 0 until 16) {
            for (x in 0 until 16) {
                val slot = MyMapSlot(
                    RectF(
                        posStartX + (x * sizeX),
                        posStartY + (y * sizeY),
                        posStartX + (x * sizeX) + sizeX,
                        posStartY + (y * sizeY) + sizeY
                    ), 1
                )
                mapSlotList.add(slot)
            }
        }

        for (i in 0 until mapSlotList.size) {
            Log.i(
                "MAXIME_INFO", " {${mapSlotList[i].idTileSet}} " +
                        "\tRectF{${mapSlotList[i].rect.left} \t| ${mapSlotList[i].rect.top}" +
                        " \t| ${mapSlotList[i].rect.right} \t| ${mapSlotList[i].rect.bottom}" +
                        " \t| ${mapSlotList[i].rect.width()} \t| ${mapSlotList[i].rect.height()}}"
            )
        }

    }

    fun createLand(
        cellX: Int,
        cellY: Int,
        cellByScreenWidth: Int,
        cellByScreenHeight: Int,
        posStartLandX: Float = -1f,
        posStartLandY: Float = -1f
    ) {

        MyMapManager.cellX = cellX
        MyMapManager.cellY = cellY

        val sizeX = GameViewManager.screenX.toFloat() / cellByScreenWidth
        val sizeY = GameViewManager.screenY.toFloat() / cellByScreenHeight

        MyMapManager.sizeX = sizeX
        MyMapManager.sizeY = sizeY

        var posStartX = posStartLandX
        var posStartY = posStartLandY

        if (posStartLandX == -1f || posStartLandY == -1f) {
            if (posStartLandX == -1f && posStartLandY == -1f) {
                posStartX = -((cellX - cellByScreenWidth) / 2) * sizeX
                posStartY = -((cellY - cellByScreenHeight) / 2) * sizeY
            } else if (posStartLandX != -1f) {
                posStartY = -((cellY - cellByScreenHeight) / 2) * sizeY
            } else if (posStartLandY != -1f) {
                posStartX = -((cellX - cellByScreenWidth) / 2) * sizeX
            }
        }


        for (y in 0 until cellY) {
            for (x in 0 until cellX) {

                var id = 385

                if ((y == cellY / 2 || y == (cellY / 2) + 1) && (x == cellX / 2 || x == (cellX / 2) + 1)) {
                    id = 513
                }

                val slot = MyMapSlot(
                    RectF(
                        posStartX + (x * sizeX),
                        posStartY + (y * sizeY),
                        posStartX + (x * sizeX) + sizeX,
                        posStartY + (y * sizeY) + sizeY
                    ), id
                )
                mapSlotList.add(slot)
            }
        }

//        for (i in 0 until mapSlotList.size) {
//            Log.i(
//                "MAXIME_INFO", " {${mapSlotList[i].idTileSet}} " +
//                        "\tRectF{${mapSlotList[i].rect.left} \t| ${mapSlotList[i].rect.top}" +
//                        " \t| ${mapSlotList[i].rect.right} \t| ${mapSlotList[i].rect.bottom}" +
//                        " \t| ${mapSlotList[i].rect.width()} \t| ${mapSlotList[i].rect.height()}}"
//            )
//        }

    }

    fun onDrawAllMapSlotList(canvas: Canvas) {

        for (i in 0 until mapSlotList.size) {

            if (
                mapSlotList[i].rect.left > -mapSlotList[i].rect.width() &&
                mapSlotList[i].rect.right < GameViewManager.screenX + mapSlotList[i].rect.width() &&
                mapSlotList[i].rect.top > -mapSlotList[i].rect.height() &&
                mapSlotList[i].rect.bottom < GameViewManager.screenY + mapSlotList[i].rect.height()

            ) {
                try {
                    canvas.drawBitmap(
                        tileset.bufferFullIdList[mapSlotList[i].idTileSet],
                        null,
                        mapSlotList[i].rect,
                        null
                    )
                } catch (e: Exception) {

                }
            }

        }

    }

}