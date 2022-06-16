package model.field_component

import model.{Matrix, Player, field_component}

trait FieldInterface {
  val players: Seq[Player]
  val matrix: Matrix[String]
  val slotNum: Int
  val turns: Int


  def placeCard(handSlot: Int, fieldSlot: Int): fieldImpl.FieldInterface

  def drawCard(): fieldImpl.FieldInterface

  def destroyCard(player: Int, slot: Int): fieldImpl.FieldInterface

  def reduceHp(player: Int, amount: Int): fieldImpl.FieldInterface

  def increaseHp(amount: Int): fieldImpl.FieldInterface

  def reduceMana(amount: Int): fieldImpl.FieldInterface

  def increaseMana(amount: Int): fieldImpl.FieldInterface

  def resetAndIncreaseMana(): fieldImpl.FieldInterface

  def setPlayerNames(p1: String, p2: String): fieldImpl.FieldInterface

  def setHpValues(amount: Int): fieldImpl.FieldInterface

  def setManaValues(amount: Int): fieldImpl.FieldInterface

  def switchPlayer(): fieldImpl.FieldInterface

  def getPlayerById(id: Int): Player

  def getActivePlayer(): Player

  def toMatrix(): Matrix[String]

}
