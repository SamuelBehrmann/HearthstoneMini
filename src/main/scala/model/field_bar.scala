package model

import scala.collection.mutable.Stack
import model.Card

case class FieldBar():
    val cardArea = new CardArea(Vector[Card]())
    val graveYard = Stack[Card]()

    def placeCard(slot: Int, card: Card) = cardArea.replaceSlot(slot, card)

    def removeCard(slot: Int) = { 
        graveYard.push(cardArea.slot(slot)) // add Card to graveyard
        cardArea.replaceSlot(field, null) // clear the slot
    }

    def attack() = {}

    def useEffect() = {}


    