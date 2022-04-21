package model

import model.FieldBar

class Field(slotNum: Int = 5) {
    var menuBarP1: Any = null
    val gameBarP1: GameBar = new GameBar()
    val fieldBarP1: FieldBar = new FieldBar(slotNum, "      ")
    val fieldBarP2: FieldBar = new FieldBar(slotNum, "      ")
    val gameBarP2: GameBar = new GameBar()
    var menuBarP2: Any = null
    val eol = sys.props("line.separator")

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
