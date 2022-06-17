package model.cardareaComponent.cardareaImpl

import model.cardareaComponent.CardAreaInterface
import model.cardComponent.cardImpl.Card

case class Cardarea[A](row: Vector[Option[Card]]) extends CardAreaInterface:
    def this(size: Int, filling: Option[Card]) = this(Vector.tabulate(size) { (row) => filling })
    override val size: Int = row.size
    override def slot(slotNum: Int): Option[Card] = row(slotNum)
    override def replaceSlot(slotNum: Int, slot: Option[Card]): CardAreaInterface = copy(row.updated(slotNum, slot))

