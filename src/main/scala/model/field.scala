package model


object Field{
    val offset: Int = 1
    val standartSlotNum: Int = 5
    val standartCardWidth: Int = 15
    val standartCardHeight: Int = 5
    val standartSlotWidth: Int = standartCardWidth + 2 // 2 for Margin
    val standartFieldBarHeight: Int = standartCardHeight + 1 // + 2 for border
    val standartGameBarHeight: Int = 7
    val standartMenueBarHeight: Int = 2
    val standartFieldWidth: Int = standartSlotNum * standartSlotWidth
    val standartFieldHeight: Int = (standartFieldBarHeight + standartGameBarHeight + standartMenueBarHeight) * 2 + Field.offset
}

case class Field(matrix: Matrix[String] = new Matrix[String](Field.standartFieldHeight, Field.standartFieldWidth, " "), slotNum: Int = Field.standartSlotNum, player1: Player = new Player(id = 1), player2: Player = new Player(id = 2)) {
    def this(size: Int, player1: Player, player2: Player) = this(new Matrix[String](Field.standartFieldHeight, Field.standartSlotWidth * size, " "), size, player1, player2)

    def placeCardPlayer1(slot: Int, card: Card): Field = copy(matrix = matrix.updateMatrixWithMatrix(Field.standartGameBarHeight + Field.standartMenueBarHeight, Field.standartSlotWidth * slot + 1, card.toMatrix()), player1 = player1.placeCard(slot, card))
    def placeCardPlayer2(slot: Int, card: Card): Field = copy(matrix = matrix.updateMatrixWithMatrix(Field.standartGameBarHeight + Field.standartMenueBarHeight + Field.standartFieldBarHeight, Field.standartSlotWidth * slot + 1, card.toMatrix()), player2 = player2.placeCard(slot, card))
    def drawCardPlayer1(): Field = copy(player1 = player1.drawCard())
    def reduceHp(amount: Int): Field = copy(player1 = player1.reduceHp(amount))

    def toMatrix(): Matrix[String] = matrix
    .updateMatrix(0, 0, List[String]("-" * Field.standartFieldWidth))
    .updateMatrixWithMatrix(Field.offset, 0, player1.toMatrix())
    .updateMatrixWithMatrix(Field.offset + Field.standartMenueBarHeight + Field.standartGameBarHeight + Field.standartFieldBarHeight, 0, player2.toMatrix())

    override def toString() = toMatrix().rows.map(_.mkString("|","","|\n")).mkString
}
