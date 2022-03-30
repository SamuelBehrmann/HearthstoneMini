package model

import scala.collection.mutable.Stack

case class FieldBar():
    val cardArea = new CardArea
    val graveYard = Stack[Card]()

    def placeCard(field: Int, card: Card) = copy(cardArea.replaceSlot(field, card))

    def removeCard(field : Int) = { 
        graveYard.push(cardArea(field)) // add Card to graveyard
        copy(cardArea.replaceSlot(field, NULL)) // clear the slot
    }

    def attack()

    def useEffect()


    