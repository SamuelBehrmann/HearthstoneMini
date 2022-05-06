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

case class Field(matrix: Matrix[String] = new Matrix[String](Field.standartFieldHeight, Field.standartFieldWidth, " "), slotNum: Int = Field.standartSlotNum, players: List[Player] = List[Player](new Player(id = 1), new Player(id = 2))) {
    def this(size: Int, player1: String, player2: String) = this(new Matrix[String](Field.standartFieldHeight, Field.standartSlotWidth * size, " "), size, players =  List[Player](new Player(name = player1, id = 1), new Player(name = player2, id = 2)))
    def this(size: Int) = this(new Matrix[String](Field.standartFieldHeight, Field.standartSlotWidth * size, " "), size, players =  List[Player](new Player(id = 1), new Player(id = 2)))

    def placeCard(playerID: Int, handSlot: Int, fieldSlot: Int): Field = copy(players = players.updated(playerID, players(playerID).placeCard(handSlot ,fieldSlot)))
    def drawCard(playerID: Int): Field = copy(players = players.updated(playerID, players(playerID).drawCard()))
    def destroyCard(playerID: Int, slot: Int): Field = copy(players = players.updated(playerID, players(playerID).destroyCard(slot)))
    def reduceHp(playerID: Int, amount: Int): Field = copy(players = players.updated(playerID, players(playerID).reduceHp(amount)))
    def increaseHp(playerID: Int, amount: Int): Field = copy(players = players.updated(playerID, players(playerID).increaseHp(amount)))
    def reduceMana(playerID: Int, amount: Int): Field = copy(players = players.updated(playerID, players(playerID).reduceMana(amount)))
    def increaseMana(playerID: Int, amount: Int): Field = copy(players = players.updated(playerID, players(playerID).increaseMana(amount)))
    def setPlayerNames(p1: String, p2: String): Field = copy(players = players.updated(0, players(0).setName(p1)).updated(1, players(1).setName(p2)))
    def toMatrix(): Matrix[String] = matrix
    .updateMatrix(0, 0, List[String]("-" * Field.standartFieldWidth))
    .updateMatrixWithMatrix(Field.offset, 0, players(0).toMatrix())
    .updateMatrixWithMatrix(Field.offset + Field.standartMenueBarHeight + Field.standartGameBarHeight + Field.standartFieldBarHeight, 0, players(1).toMatrix())

    override def toString() = toMatrix().rows.map(_.mkString("|","","|\n")).mkString
}
