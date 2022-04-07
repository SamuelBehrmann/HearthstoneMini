package model




abstract class Card(name: String, manaCost: Int, attValue: Int, defenseValue: Int, effect: String, rarity: String) {

  def setName(name: String): Unit
  def setManaCost(amount: Int): Mana
  def setAttack(amount: Int): Unit
  def setDefense(amount: Int): Unit
  def setEffect(beschreibung: String): Unit
  def setRarity(rarity: String): Unit
  }





