package model
import model.Card

case class CardArea[Any](row: Vector[Any]):
    def this(size: Int, filling: Any) = this(Vector.tabulate(size) { (row) => filling })
    val size: Int = row.size
    def slot(slotNum: Int) = row(slotNum)
    def replaceSlot(slotNum: Int, slot: Any): CardArea[Any] = copy(row.updated(slotNum, slot))