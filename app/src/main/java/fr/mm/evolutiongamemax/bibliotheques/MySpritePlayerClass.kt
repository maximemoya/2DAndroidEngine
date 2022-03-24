package fr.mm.evolutiongamemax.bibliotheques

import android.graphics.Canvas
import android.graphics.RectF
import android.util.Log
import android.view.MotionEvent
import fr.mm.evolutiongamemax.activities.GameViewManager
import fr.mm.evolutiongamemax.activities.menu.MenuView
import fr.mm.evolutiongamemax.interfaces.MaximeSpriteSheetInterface
import fr.mm.evolutiongamemax.interfaces.MaximeViewInterface
import kotlin.math.sqrt

class MySpritePlayerClass(
    val bitmap: MyAnimBitmapSquareManager,
    var configSpriteSheet: MaximeSpriteSheetInterface,
    var posX: Float = ((GameViewManager.screenX / 2) - (bitmap.width / 2)).toFloat(),
    var posY: Float = ((GameViewManager.screenY / 2) - bitmap.height / 2).toFloat()
) : MaximeViewInterface {

    companion object {
        val rectSprite: RectF = RectF()
    }

    var animationLine = configSpriteSheet.DOWN()
    var diagonalDirection = MyJoystickManager.NONE
    var isMoving = false // moving or idle

    var speed = GameViewManager.screenY * 0.015f
    var acceleration = GameViewManager.screenY * 0.02f
    var touchX = 0f
    var touchY = 0f

    init {
        rectSprite.set(posX, posY, bitmap.width.toFloat(), bitmap.height.toFloat())
        val th1 = Thread(Runnable { threadPlayer() })
        th1.start()

    }

    var changeX = 0f
    var changeY = 0f

    fun threadPlayer() {

        while (true) {

            if (isMoving) {
                val testAccByFinger = sqrt(
                    (MyJoystickManager.deltaXCenterToTouchXMove * MyJoystickManager.deltaXCenterToTouchXMove +
                            MyJoystickManager.deltaYCenterToTouchYMove * MyJoystickManager.deltaYCenterToTouchYMove).toDouble()
                )

                if (testAccByFinger > GameViewManager.screenY * 0.15f) {
                    acceleration = GameViewManager.screenY * 0.02f
                } else {
                    acceleration = 0.0f
                }
                changeX =
                    MenuView.posViewX - MyJoystickManager.cosinusPhiMove * (speed + acceleration)
                changeY =
                    MenuView.posViewY - MyJoystickManager.sinusPhiMove * (speed + acceleration)
            }

            if (changeX != MyMapManager.posViewLastX && changeY != MyMapManager.posViewLastY) {

                val deltaX = changeX - MyMapManager.posViewLastX
                val deltaY = changeY - MyMapManager.posViewLastY

                var isCollide = false

                for (i in 0 until MyMapManager.mapSlotList.size) {

                    if (MyMapManager.tileset.listIdBlock.contains(MyMapManager.mapSlotList[i].idTileSet)) {

                        if (MySpritePlayerClass.rectSprite.contains(MyMapManager.mapSlotList[i].rect)) {
                            isCollide = true
                            Log.i("MAXIME_INFO MyMapManager", "player touch blockID")
                        }
                        if (
                            MyMapManager.mapSlotList[i].rect.contains(
                                MySpritePlayerClass.rectSprite.centerX(),
                                MySpritePlayerClass.rectSprite.centerY()
                            )
                        ) {
                            isCollide = true
                            Log.i("MAXIME_INFO MyMapManager", "player Center touch blockID")
                        }
                        if (isCollide) {
                            MenuView.posViewX = MyMapManager.posViewLastX
                            MenuView.posViewY = MyMapManager.posViewLastY
                        } else {
                            MenuView.posViewX = changeX
                            MenuView.posViewY = changeY
                        }
                    }
                }
                MyMapManager.posViewLastX = MenuView.posViewX
                MyMapManager.posViewLastY = MenuView.posViewY


                if (!isCollide) {
                    MyMapManager.setRectByPos(MenuView.posViewX, MenuView.posViewY)
                }


            }

            Thread.sleep(25)
        }

    }

    override fun onDraw(canvas: Canvas) {

        if (isMoving) {
            bitmap.drawLineAnimBitmaps(
                canvas = canvas,
                lineOfAnimation = animationLine,
                posX,
                posY
            )

        } else {
            bitmap.drawBitmap(
                canvas = canvas,
                lineOfAnimation = animationLine,
                _frame = 0,
                posX,
                posY
            )
        }

        MyJoystickManager.onDraw(canvas)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        touchX = event.x
        touchY = event.y

        when (MyJoystickManager.onTouchEvent(event)) {

            // - - - MOVE - - - - - - - - - - - - - - - - - - -

            MyJoystickManager.RIGHT -> {
                isMoving = true
                animationLine = configSpriteSheet.RIGHT()
                diagonalDirection = MyJoystickManager.NONE
            }

            MyJoystickManager.LEFT -> {
                isMoving = true
                animationLine = configSpriteSheet.LEFT()
                diagonalDirection = MyJoystickManager.NONE
            }

            MyJoystickManager.UP -> {
                isMoving = true
                animationLine = configSpriteSheet.UP()
                diagonalDirection = MyJoystickManager.NONE
            }

            MyJoystickManager.DOWN -> {
                isMoving = true
                animationLine = configSpriteSheet.DOWN()
                diagonalDirection = MyJoystickManager.NONE
            }

            MyJoystickManager.UP_LEFT -> {
                isMoving = true
                animationLine = configSpriteSheet.UP()
                diagonalDirection = MyJoystickManager.UP_LEFT
            }

            MyJoystickManager.UP_RIGHT -> {
                isMoving = true
                animationLine = configSpriteSheet.UP()
                diagonalDirection = MyJoystickManager.UP_RIGHT
            }

            MyJoystickManager.DOWN_LEFT -> {
                isMoving = true
                animationLine = configSpriteSheet.DOWN()
                diagonalDirection = MyJoystickManager.DOWN_LEFT
            }

            MyJoystickManager.DOWN_RIGHT -> {
                isMoving = true
                animationLine = configSpriteSheet.DOWN()
                diagonalDirection = MyJoystickManager.DOWN_RIGHT
            }

            // - - - ATTACK - - - - - - - - - - - - - - - - - - -

            MyJoystickManager.ATK_RIGHT -> {
                isMoving = true
                animationLine = configSpriteSheet.ATK_RIGHT()
            }

            MyJoystickManager.ATK_LEFT -> {
                isMoving = true
                animationLine = configSpriteSheet.ATK_LEFT()
            }

            MyJoystickManager.ATK_UP -> {
                isMoving = true
                animationLine = configSpriteSheet.ATK_UP()
            }

            MyJoystickManager.ATK_DOWN -> {
                isMoving = true
                animationLine = configSpriteSheet.ATK_DOWN()
            }

            else -> {
                isMoving = false
                diagonalDirection = MyJoystickManager.NONE
            }

        }

        if (event.action == MotionEvent.ACTION_UP) {
            isMoving = false
            acceleration = 0.0f
        }

        return true
    }

}