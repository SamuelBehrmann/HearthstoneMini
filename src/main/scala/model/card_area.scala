package model
import model.Card

case class CardArea[Card](row: Vector[model.Card]):
    val size = row.size
    def slot(slotNum: Int) = row(slotNum)
    def replaceSlot(slotNum: Int, slot: model.Card): CardArea[model.Card] = copy(row.updated(slotNum, slot)) 