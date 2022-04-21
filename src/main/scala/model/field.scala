package model

case class Field(matrix: Matrix[String], slotNum: Int = 5) {
    def this(size: Int) = this(new Matrix[String](10,60,""),5)

    def placeCard(player: Player, slot: Int, card: Card) = player.fieldbar.placeCard(slot, card)

    def print() = matrix.printMatrix()

    def printField(): String = "hello"
        // fieldBarP1.bar(slotNum = slotNum) + "#" +
        // //menuBarP1.toString() + "#" +
        // gameBarP1.toString() + "#" +
        // fieldBarP1.toString() + "#" +
        // fieldBarP2.toString() + "#" +
        // gameBarP2.toString() + "#" +
        // //menuBarP2.toString() + "#" +
        // fieldBarP1.bar(slotNum = slotNum)

    override def toString() = printField()

}
