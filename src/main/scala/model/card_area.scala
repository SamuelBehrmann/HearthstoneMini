package model

case class CardArea[Card](row: Vector[Card]):
    val size = row.size
    def slot(slotNum: Int): T = row(slotNum)
    def replaceSlot(slotNum: Int, slot: T): CardArea[Card] = copy(row.updated(slotNum, slot)) 