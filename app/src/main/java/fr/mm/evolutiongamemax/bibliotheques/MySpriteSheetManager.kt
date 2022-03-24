package fr.mm.evolutiongamemax.bibliotheques

import fr.mm.evolutiongamemax.interfaces.MaximeSpriteSheetInterface

abstract class MySpriteSheetManager {

    companion object {

        val LINKCONFIG = LinkAnimation()

    }

    class LinkAnimation() : MaximeSpriteSheetInterface {
        override fun RIGHT(): Int {
            return 0
        }

        override fun LEFT(): Int {
            return 1
        }

        override fun UP(): Int {
            return 3
        }

        override fun DOWN(): Int {
            return 2
        }

        override fun DEAD(): Int {
            return 4
        }

        override fun ATK_RIGHT(): Int {
            return 5
        }

        override fun ATK_LEFT(): Int {
            return 6
        }

        override fun ATK_UP(): Int {
            return 8
        }

        override fun ATK_DOWN(): Int {
            return 7
        }

    }


}