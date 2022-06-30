package model.gamebarComponent

import model.cardComponent.cardImpl.Card
import model.matrixComponent.matrixImpl.Matrix
import model.fieldComponent.fieldImpl.FieldObject
import model.gamebarComponent.gamebarImpl.Gamebar
import play.api.libs.json.JsValue

import scala.xml.Node

trait GamebarInterface {
  def removeCardFromHand(slot: Int): Gamebar
  def addCardToHand(card: Option[Card]): Gamebar
  def addCardToFriedhof(card: Option[Card]): Gamebar
  def reduceHp(amount: Int): Gamebar
  def increaseHp(amount: Int): Gamebar
  def reduceMana(amount: Int): Gamebar
  def increaseMana(amount: Int): Gamebar
  def resetAndIncreaseMana(): Gamebar
  def drawCard(): Gamebar
  def setManaValue(amount: Int): Gamebar
  def setHpValue(amount: Int): Gamebar
  def handAsMatrix(): Matrix[String]
  def toMatrix: Matrix[String]
  def toJson: JsValue
  def toXML: Node
}
