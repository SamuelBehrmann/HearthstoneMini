package model.card_area_component
import model.card_component.cardImpl.Card

trait CardAreaInterface {
  val row: Vector[Option[Card]]
  val size: Int
  def slot(slotNum: Int): Option[Card]
  def replaceSlot(slotNum: Int, slot: Option[Card]): CardAreaInterface
}

