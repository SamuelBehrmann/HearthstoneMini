package model

import model.FieldBar

case class Field(slotNum: Int = 5, fieldBarP1: FieldBar, fieldBarP2: FieldBar) {
    def this(size: Int) = this(size, new FieldBar(size, "      "), new FieldBar(size, "      "))
    var menuBarP1: Any = null
    val gameBarP1: GameBar = new GameBar()
    val gameBarP2: GameBar = new GameBar()
    var menuBarP2: Any = null
    val eol = sys.props("line.separator")



    def placeCardP1(slot: Int, card: Any) = copy(slotNum, fieldBarP1.placeCard(slot, card), fieldBarP2)
    def placeCardP2(slot: Int, card: Any) = copy(slotNum, fieldBarP1 ,fieldBarP2.placeCard(slot, card))


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
