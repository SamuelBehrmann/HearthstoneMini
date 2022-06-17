package model.field_component

import model.{Matrix, Player, field_component}

trait FieldInterface {
  val players: Seq[Player]
  val matrix: Matrix[String]
  val slotNum: Int
  val turns: Int


  def placeCard(handSlot: Int, fieldSlot: Int): FieldInterface

  def drawCard(): FieldInterface

  def destroyCard(player: Int, slot: Int): FieldInterface

  def reduceHp(player: Int, amount: Int): FieldInterface

  def increaseHp(amount: Int): FieldInterface

  def reduceMana(amount: Int): FieldInterface

  def increaseMana(amount: Int):FieldInterface

  def resetAndIncreaseMana(): FieldInterface

  def setPlayerNames(p1: String, p2: String): FieldInterface

  def setHpValues(amount: Int): FieldInterface

  def setManaValues(amount: Int): FieldInterface

  def switchPlayer(): FieldInterface

  def getPlayerById(id: Int): Player

  def getActivePlayer(): Player

  def toMatrix(): Matrix[String]

}
