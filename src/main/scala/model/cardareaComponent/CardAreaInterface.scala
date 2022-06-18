package model.cardareaComponent
import model.cardComponent.cardImpl.Card

trait CardAreaInterface {
  val row: Vector[Option[Card]]
  val size: Int
  def slot(slotNum: Int): Option[Card]
  def replaceSlot(slotNum: Int, slot: Option[Card]): CardAreaInterface
  def reduceDefVal(slotNum: Int, amount: Int): CardAreaInterface
  def reduceAttackCount(slotNum: Int): CardAreaInterface
}

