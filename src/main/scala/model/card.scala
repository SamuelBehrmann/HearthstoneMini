package model

object Card{
    val width: Int = 10
    val height: Int = 4
}

case class Card(name: String, manaCost: Int, attValue: Int, defenseValue: Int, effect: String, rarity: String) {
    override def toString():String = name + " (" + manaCost + ")" + "#" + "atk: " + attValue + " def: " + defenseValue + "#" + effect + "#" + rarity
    def toMatrix(): Matrix[String] = new Matrix[String](Card.height, Card.width, "").updateMatrix(0,0, toString().split("#").toList)
}





