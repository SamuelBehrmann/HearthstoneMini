package model

case class CardArea[Card](row: Vector[Card]):
    def this(size: Int, filling: Card) = this(Vector.tabulate(size) { (row) => filling })
    val size: Int = row.size
    def slot(slotNum: Int): Card = row(slotNum)
    def replaceSlot(slotNum: Int, slot: Card): CardArea[Card] = copy(row.updated(slotNum, slot))
