package model.fieldbarComponent

import model.cardComponent.cardImpl.Card
import model.fieldComponent.fieldImpl.FieldObject
import model.matrixComponent.matrixImpl.Matrix
import play.api.libs.json.JsValue

import scala.xml.Node

trait FieldbarInterface {
  val size: Int
  def placeCard(slot: Int, card: Card): FieldbarInterface
  def removeCard(slot: Int): FieldbarInterface
  def toMatrix: Matrix[String]
  def reduceDefVal(slotNum: Int, amount: Int): FieldbarInterface
  def reduceAttackCount(slotNum: Int): FieldbarInterface
  def resetAttackCount(): FieldbarInterface
  def toJson: JsValue
  def toXML: Node
}
