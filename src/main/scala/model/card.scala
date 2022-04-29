package model

trait CardType {
    val name: String
    val manaCost: Int
    val attValue: Int
    val defenseValue: Int
    val effect: String
    val rarity: String
    def toMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartCardWidth, " ")
}

class Card(val name: String, val manaCost: Int, val attValue: Int, val defenseValue: Int, val effect: String, val rarity: String) extends CardType {
    override def toString(): String = name + " (" + manaCost + ")" + "#" + "atk: " + attValue + "#def: " + defenseValue + "#" + effect + "#" + rarity
    override def toMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartCardWidth, " ").updateMatrix(0, 0, toString().split("#").toList)
}

class EmptyCard(val name: String = "yolo", val manaCost: Int = 0, val attValue: Int = 0, val defenseValue: Int = 0, val effect: String = "", val rarity: String = "") extends CardType {
    override def toMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartCardWidth, " ")
}

