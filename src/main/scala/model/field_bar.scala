package model

import scala.collection.mutable.Stack
import model.Card

case class FieldBar(cardArea: CardArea[Any]):
    def this(size: Int, filling: Any) = this(new CardArea(size, filling))
    val graveYard = Stack[Any]()
    val size = cardArea.size
    val eol = sys.props("line.separator")

    def bar(slotWidth: Int = 5, slotNum: Int = 5): String = (("+" + "-" * slotWidth) * slotNum) + "+" + eol
    def slots(slotWidth: Int = 5): String = cardArea.row.map((string) => " " * ((slotWidth-string.asInstanceOf[String].length)/2) + string + " " * ((slotWidth-string.asInstanceOf[String].length)/2)).mkString("|", "|", "|") + eol
    def completeField(slotWidth: Int = 5): String = bar(slotWidth, size) + slots(slotWidth) + bar(slotWidth,size)
    override def toString = completeField()

    def placeCard(slot: Int, card: Any): FieldBar = copy(cardArea.replaceSlot(slot, card))
    def removeCard(slot: Int): FieldBar = copy({
        graveYard.push(cardArea.slot(slot)) // add Card to graveyard
        cardArea.replaceSlot(slot, " ") // clear the slot
    })

    //def attack() = {}
    //def useEffect() = {}


