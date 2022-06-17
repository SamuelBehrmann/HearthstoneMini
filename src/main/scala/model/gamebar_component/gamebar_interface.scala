package model.gamebar_component

import model.{Card, Matrix}
import model.field_component.fieldImpl.FieldObject
import model.gamebar_component.GameBarImpl.GameBar

trait GamebarInterface {
  def removeCardFromHand(slot: Int): GameBar

  def addCardToHand(card: Option[Card]): GameBar

  def addCardToFriedhof(card: Option[Card]): GameBar

  def reduceHp(amount: Int): GameBar

  def increaseHp(amount: Int): GameBar

  def reduceMana(amount: Int): GameBar

  def increaseMana(amount: Int): GameBar

  def resetAndIncreaseMana(): GameBar

  def drawCard(): GameBar

  def setManaValue(amount: Int): GameBar

  def setHpValue(amount: Int): GameBar

  def handAsMatrix(): Matrix[String]

  def toMatrix(): Matrix[String]
}
