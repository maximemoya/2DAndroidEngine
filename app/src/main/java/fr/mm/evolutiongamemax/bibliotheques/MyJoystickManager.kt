package fr.mm.evolutiongamemax.bibliotheques

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import fr.mm.evolutiongamemax.activities.GameViewManager
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos

abstract class MyJoystickManager {

    companion object {

        val NONE = -1

        val UP = 0
        val UP_RIGHT = 1
        val RIGHT = 2
        val DOWN_RIGHT = 3
        val DOWN = 4
        val DOWN_LEFT = 5
        val LEFT = 6
        val UP_LEFT = 7

        val ATK_UP = 8
        val ATK_RIGHT = 9
        val ATK_DOWN = 10
        val ATK_LEFT = 11

        var cosinusPhiMove = 0f
        var sinusPhiMove = 0f
        var deltaXCenterToTouchXMove = 0f
        var deltaYCenterToTouchYMove = 0f

        private val screenProportion = GameViewManager.screenY * 0.04f
        private var centerX = 0f
        private var centerY = 0f
        private var touchX = 0f
        private var touchY = 0f
        private var deltaX = 0f
        private var deltaY = 0f
        private var isJoystickMoveActivate = false
        private val paint: Paint = Paint()

        private var centerX_ATK = 0f
        private var centerY_ATK = 0f
        private var touchX_ATK = 0f
        private var touchY_ATK = 0f
        private var deltaX_ATK = 0f
        private var deltaY_ATK = 0f
        private var isJoystickATKActivate = false
        private val paint_ATK: Paint = Paint()

        private var returnVal = NONE

        init {
            paint.color = Color.DKGRAY
            paint_ATK.color = Color.RED
        }

        fun onDraw(canvas: Canvas) {

            if (isJoystickMoveActivate) {

                canvas.drawCircle(touchX, touchY, screenProportion * 2, paint)
            }

            if (isJoystickATKActivate) {

                canvas.drawCircle(touchX_ATK, touchY_ATK, screenProportion * 2, paint_ATK)
            }

        }

        fun onTouchEvent(event: MotionEvent): Int {

            if (event.action == MotionEvent.ACTION_DOWN) {

                // Joystick separateScreen : MOVEMENT || ATK :
                if(event.x < GameViewManager.screenX/2){
                    centerX = event.x
                    centerY = event.y
                    touchX = centerX
                    touchY = centerY
                    isJoystickMoveActivate = true
                }
                else{
                    centerX_ATK = event.x
                    centerY_ATK = event.y
                    touchX_ATK = centerX_ATK
                    touchY_ATK = centerY_ATK
                    isJoystickATKActivate = true
                }

            }

            if(isJoystickMoveActivate){
                if(event.x < GameViewManager.screenX/2){
                    if (event.action == MotionEvent.ACTION_MOVE) {
                        deltaX = event.x - touchX
                        deltaY = event.y - touchY

                        if (abs(deltaX) > screenProportion || abs(deltaY) > screenProportion) {

                            deltaXCenterToTouchXMove = event.x - centerX
                            deltaYCenterToTouchYMove = event.y - centerY

                            val hypothenuse =
                                (Math.sqrt(((deltaX * deltaX) + (deltaY * deltaY)).toDouble()))
                            cosinusPhiMove = (deltaX) / hypothenuse.toFloat()
                            sinusPhiMove = (deltaY) / hypothenuse.toFloat()
                            touchX = centerX + cosinusPhiMove.toFloat() * screenProportion
                            touchY = centerY + sinusPhiMove.toFloat() * screenProportion
                            val angleRadian = acos(cosinusPhiMove)

                            if (sinusPhiMove < 0) {

                                if (angleRadian < (PI / 6)) {
                                    return RIGHT
                                } else if (angleRadian < (PI * 2 / 6)) {
                                    return UP_RIGHT
                                } else if (angleRadian < (PI * 4 / 6)) {
                                    return UP
                                } else if (angleRadian < (PI * 5 / 6)) {
                                    return UP_LEFT
                                } else {
                                    return LEFT
                                }

                            } else {

                                if (angleRadian < (PI / 6)) {
                                    return RIGHT
                                } else if (angleRadian < (PI * 2 / 6)) {
                                    return DOWN_RIGHT
                                } else if (angleRadian < (PI * 4 / 6)) {
                                    return DOWN
                                } else if (angleRadian < (PI * 5 / 6)) {
                                    return DOWN_LEFT
                                } else {
                                    return LEFT
                                }

                            }

                        }
                        else {
                            deltaXCenterToTouchXMove = 0f
                            deltaYCenterToTouchYMove = 0f
                        }

                    }
                }

            }

            if(isJoystickATKActivate){
                if(event.x >= GameViewManager.screenX/2){
                    if (event.action == MotionEvent.ACTION_MOVE) {
                        deltaX_ATK = event.x - touchX_ATK
                        deltaY_ATK = event.y - touchY_ATK

                        if (abs(deltaX_ATK) > screenProportion || abs(deltaY_ATK) > screenProportion) {

                            val hypothenuse =
                                (Math.sqrt(((deltaX_ATK * deltaX_ATK) + (deltaY_ATK * deltaY_ATK)).toDouble()))
                            val cosinusPhi = (deltaX_ATK) / hypothenuse
                            val sinusPhi = (deltaY_ATK) / hypothenuse
                            touchX_ATK = centerX_ATK + cosinusPhi.toFloat() * screenProportion
                            touchY_ATK = centerY_ATK + sinusPhi.toFloat() * screenProportion
                            val angleRadian = acos(cosinusPhi)

                            if (sinusPhi < 0) {

                                if (angleRadian < (PI / 4)) {
                                    return ATK_RIGHT
                                } else if (angleRadian < (PI * 3 / 4)) {
                                    return ATK_UP
                                } else {
                                    return ATK_LEFT
                                }

                            } else {

                                if (angleRadian < (PI / 4)) {
                                    return ATK_RIGHT
                                } else if (angleRadian < (PI * 3 / 4)) {
                                    return ATK_DOWN
                                } else {
                                    return ATK_LEFT
                                }

                            }

                        }

                    }
                }

            }

            if (event.action == MotionEvent.ACTION_UP) {
                isJoystickMoveActivate = false
                isJoystickATKActivate = false
                cosinusPhiMove = 0f
                sinusPhiMove = 0f
                deltaXCenterToTouchXMove = 0f
                deltaYCenterToTouchYMove = 0f

            }
            return returnVal
        }

    }


}