package model

case class Field(matrix: Matrix[String], slotNum: Int = 5) {
    def this(size: Int) = this(new Matrix[String](60,""),5)
    
    val menuBarP1: Any = null
    val gameBarP1: GameBar = new GameBar()
    val fieldBarP1: FieldBar = new FieldBar(slotNum)
    val fieldBarP2: FieldBar = new FieldBar(slotNum)
    val gameBarP2: GameBar = new GameBar()
    val menuBarP2: Any = null
    

    def placeCard(slot: Int, card: Card) = copy(matrix.updateMatrix(2, 5*slot, card.toString().split("#").toList))

    def print1() = matrix.printMatrix()

    def printField(): String = 
        fieldBarP1.bar(slotNum = slotNum) + "#" +
        //menuBarP1.toString() + "#" +
        gameBarP1.toString() + "#" +
        fieldBarP1.toString() + "#" +
        fieldBarP2.toString() + "#" +
        gameBarP2.toString() + "#" +
        //menuBarP2.toString() + "#" +
        fieldBarP1.bar(slotNum = slotNum)
        
    override def toString() = printField()
}
