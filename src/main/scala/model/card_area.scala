package model
import model.Card

case class CardArea[Option[Card]](row: Vector[Option[Card]]):
    def this(size: Int, filling: Option[Card]) = this(Vector.tabulate(size) { (row) => filling })
    val size: Int = row.size
    def slot(slotNum: Int): Option[Card] = row(slotNum)
    def replaceSlot(slotNum: Int, slot: Option[Card]): CardArea[Option] = copy(row.updated(slotNum, slot))
