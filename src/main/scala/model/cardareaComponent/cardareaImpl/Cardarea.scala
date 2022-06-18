package model.cardareaComponent.cardareaImpl

import model.cardareaComponent.CardAreaInterface
import model.cardComponent.cardImpl.Card

case class Cardarea[A](row: Vector[Option[Card]]) extends CardAreaInterface:
    def this(size: Int, filling: Option[Card]) = this(Vector.tabulate(size) { (row) => filling })
    override val size: Int = row.size
    override def slot(slotNum: Int): Option[Card] = row(slotNum)
    override def replaceSlot(slotNum: Int, card: Option[Card]): CardAreaInterface = copy(row.updated(slotNum, card))
    override def reduceDefVal(slotNum: Int, amount: Int): CardAreaInterface = copy(row.updated(slotNum, Some(row(slotNum).get.reduceHP(amount))))
    override def reduceAttackCount(slotNum: Int): CardAreaInterface = copy(row.updated(slotNum, Some(row(slotNum).get.reduceAttackCount())))

