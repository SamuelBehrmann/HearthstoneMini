package model

import scala.collection.mutable.Stack
import model.Card

case class FieldBar():
    val cardArea = new CardArea(Vector[model.Card]())
    val graveYard = Stack[model.Card]()

    def placeCard(field: Int, card: model.Card) = cardArea.replaceSlot(field, card)

    def removeCard(field : Int) = { 
        graveYard.push(cardArea.slot(field)) // add Card to graveyard
        cardArea.replaceSlot(field, null) // clear the slot
    }

    def attack() = {}

    def useEffect() = {}


    