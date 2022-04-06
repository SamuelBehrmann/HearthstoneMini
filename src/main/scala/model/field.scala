package model

import model.FieldBar

class Field(slotNum: Int = 5) {
    val menuBarP1: Any = null
    val gameBarP1: Any = null
    val fieldBarP1: FieldBar = new FieldBar(slotNum, "      ")
    val fieldBarP2: FieldBar = new FieldBar(slotNum, "      ")
    val gameBarP2: Any= null
    val menuBarP2: Any = null
    val eol = sys.props("line.separator")

    def printField(): String = fieldBarP1.bar(slotNum = slotNum) +
        "\u001b[31m" + menuBarP1 + eol +
        gameBarP1 + eol +
        fieldBarP1 + eol +
        "\u001b[32m" + fieldBarP2 + eol +
        gameBarP2 + eol +
        menuBarP2 + eol +
        "\u001b[37m" + fieldBarP1.bar(slotNum = slotNum)
    override def toString() = printField()
}
