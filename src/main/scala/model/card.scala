package model

import scala.compiletime.ops.boolean




class Card(name: String, manaCost: Int, attValue: Int, defenseValue: Int, effect: String, rarity: String) {
    val width: Int = 10
    val height:Int = 5
    override def toString():String = name + " (" + manaCost + ")" + "#" + "atk: " + attValue + " def: " + defenseValue + "#" + effect + "#" + rarity
}





