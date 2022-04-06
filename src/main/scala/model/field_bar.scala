package model

import scala.collection.mutable.Stack
import model.Card

case class FieldBar():
    val cardArea = new CardArea(Vector[String]())
    val graveYard = Stack[String]()

    def placeCard(slot: Int, card: String) = cardArea.replaceSlot(slot, card)

    def removeCard(slot: Int) = {
        graveYard.push(cardArea.slot(slot)) // add Card to graveyard
        cardArea.replaceSlot(slot, "") // clear the slot
    }

    def attack() = {}

    def useEffect() = {}


