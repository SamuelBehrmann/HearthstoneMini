package model

case class CardArea[CardType](row: Vector[CardType]):
    def this(size: Int, filling: CardType) = this(Vector.tabulate(size) { (row) => filling })
    val size: Int = row.size
    def slot(slotNum: Int): CardType = row(slotNum)
    def replaceSlot(slotNum: Int, slot: CardType): CardArea[CardType] = copy(row.updated(slotNum, slot))
