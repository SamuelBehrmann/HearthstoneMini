package model

import model.FieldBar

case class Field(slotNum: Int = 5, fieldBarP1: FieldBar, fieldBarP2: FieldBar,
                 menuBarP1: String, menuBarP2: String,
                 gameBarP1: GameBar = new GameBar(List("1", "2", "3", "4", "5")),
                 gameBarP2: GameBar = new GameBar(List("6", "7", "8", "9", "10"))) {
    def this(size: Int, nameP1: String, nameP2: String) = this(size, new FieldBar(size, " "), new FieldBar(size, " "), nameP1, nameP2)
    //val gameBarP1: GameBar = new GameBar(List("1", "2", "3", "4", "5"))
    //val gameBarP2: GameBar = new GameBar(List("6", "7", "8", "9", "10"))
    val eol = sys.props("line.separator")



    def placeCardP1(slot: Int, card: Any) = copy(slotNum, fieldBarP1.placeCard(slot - 1, card), fieldBarP2, menuBarP1, menuBarP2, gameBarP1.removeCard(card.toString), gameBarP2)
    def placeCardP2(slot: Int, card: Any) = copy(slotNum, fieldBarP1 ,fieldBarP2.placeCard(slot - 1, card), menuBarP1, menuBarP2, gameBarP1, gameBarP2.removeCard(card.toString))


    def printField(): String = fieldBarP1.bar(slotNum = slotNum) +
        "\u001b[31m" + menuBarP1 + eol +
        gameBarP1 + eol +
        "\u001b[31m" + fieldBarP1 + eol +
        "\u001b[34m" + fieldBarP2 +
        gameBarP2 + eol +
        "\u001b[34m" + menuBarP2 + eol +
        "\u001b[37m" + fieldBarP1.bar(slotNum = slotNum)

    override def toString() = printField()
}
