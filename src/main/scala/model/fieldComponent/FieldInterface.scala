package model.fieldComponent

import model.cardComponent.cardImpl.Card
import model.playerComponent.playerImpl.Player
import model.matrixComponent.matrixImpl.Matrix
import model.fieldComponent
import play.api.libs.json.JsValue

import scala.xml.Node

trait FieldInterface {
  def players: List[Player]
  def matrix: Matrix[String]
  def slotNum: Int
  def turns: Int

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
  def reduceAttackCount(slotNum: Int): FieldInterface
  def resetAttackCount(): FieldInterface
  def reduceDefVal(slotNum: Int, amount: Int): FieldInterface
  def switchPlayer(): FieldInterface
  def getPlayerById(id: Int): Player
  def getActivePlayer: Player
  def toMatrix: Matrix[String]
  def toJson: JsValue
  def toXML: Node

}
