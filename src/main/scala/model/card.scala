package model


abstract class Card(name: String, manaCost: Int, attValue: Int, defenseValue: Int, effect: String, rarity: String) {

  def setName(name: String): Unit
  def setManaCost(amount: Int): Mana
  def setAttack(amount: Int): Unit
  def setDefense(amount: Int): Unit
  def setEffect(beschreibung: String): Unit
  def setRarity(rarity: String): Unit



  override def toString: String = {
    name + "\n" + "mana cost: " + manaCost + "\n" + "attack: " + attValue + "\n" +
      "defense: " + defenseValue + "\n" + "rarity: " + rarity + "\n" + "effect: " + effect
  }



}

/*// enum für rarities
object rarities extends Enumeration {
  val gewoehnlich, selten, episch, legendaer = Value
}

*/




