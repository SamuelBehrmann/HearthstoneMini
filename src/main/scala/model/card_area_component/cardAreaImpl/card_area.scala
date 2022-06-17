package model.card_area_component.cardAreaImpl

import model.card_area_component.CardAreaInterface
import model.card_component.cardImpl.Card

case class CardArea[A](row: Vector[Option[Card]]) extends CardAreaInterface:
    def this(size: Int, filling: Option[Card]) = this(Vector.tabulate(size) { (row) => filling })
    override val size: Int = row.size
    override def slot(slotNum: Int): Option[Card] = row(slotNum)
    override def replaceSlot(slotNum: Int, slot: Option[Card]): CardAreaInterface = copy(row.updated(slotNum, slot))

