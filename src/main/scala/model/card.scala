package model

case class Card(name: String, manaCost: Int, attValue: Int, defenseValue: Int, rarity: String, effect: String) {

//  def emptyCard: Card = {
//
//  }

  override def toString: String = {
    "Name: " + name +" (" + manaCost + ")" + "\n" + "attack: " + attValue + "\n" +
      "defense: " + defenseValue + "\n" + "Seltenheit: " + rarity + "\n" +  "Effekt: " + effect
  }



}

/*// enum für rarities
object rarities extends Enumeration {
  val gewoehnlich, selten, episch, legendaer = Value
}

*/




