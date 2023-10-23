package hearthstoneMini
package model.cardareaComponent
import model.cardComponent.cardImpl.Card
import play.api.libs.json.JsValue

import scala.xml.Node

trait CardAreaInterface {
  val row: Vector[Option[Card]]
  val size: Int
  def slot(slotNum: Int): Option[Card]
  def replaceSlot(slotNum: Int, slot: Option[Card]): CardAreaInterface
  def reduceDefVal(slotNum: Int, amount: Int): CardAreaInterface
  def reduceAttackCount(slotNum: Int): CardAreaInterface
  def resetAttackCount(): CardAreaInterface
  def toJson: JsValue
  def toXML: Node
}

