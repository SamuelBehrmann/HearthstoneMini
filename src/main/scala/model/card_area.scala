package model
import model.Card

case class CardArea[String](row: Vector[String]):
    def this(size: Int, filling: String) = this(Vector.tabulate(size) { (row) => filling })
    val size: Int = row.size
    def slot(slotNum: Int) = row(slotNum)
    def replaceSlot(slotNum: Int, slot: String): CardArea[String] = copy(row.updated(slotNum, slot))