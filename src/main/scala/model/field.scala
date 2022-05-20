package model

object Field {
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

case class Field(matrix: Matrix[String] = new Matrix[String](Field.standartFieldHeight, Field.standartFieldWidth, " "),
                 slotNum: Int = Field.standartSlotNum,
                 players: List[Player] = List[Player](new Player(id = 1), new Player(id = 2)),
                 turns: Int = 0) {
  // The active player is the Player in position 0 of the list
  def this(size: Int, player1: String, player2: String) = this(new Matrix[String](Field.standartFieldHeight, Field.standartSlotWidth * size, " "),
    size,
    players = List[Player](new Player(name = player1, id = 1), new Player(name = player2, id = 2)))

  def this(size: Int) = this(new Matrix[String](Field.standartFieldHeight, Field.standartSlotWidth * size, " "),
    size,
    players = List[Player](new Player(id = 1), new Player(id = 2)))

  def placeCard(handSlot: Int, fieldSlot: Int): Field = if (  players(0).fieldbar.cardArea.slot(fieldSlot).isEmpty && handSlot < players(0).gamebar.hand.length)
  then if (players(0).gamebar.mana.value >= players(0).gamebar.hand(handSlot).manaCost)
    then copy(players = players.updated(0, players(0).placeCard(handSlot, fieldSlot).reduceMana(players(0).gamebar.hand(handSlot).manaCost)))
    else this
  else this

  def drawCard(): Field = copy(players = players.updated(0, players(0).drawCard()))

  def destroyCard(player: Int, slot: Int): Field = copy(players = players.updated(player, players(player).destroyCard(slot)))

  def reduceHp(player: Int, amount: Int): Field = copy(players = players.updated(player, players(player).reduceHp(amount)))

  def increaseHp(amount: Int): Field = copy(players = players.updated(0, players(0).increaseHp(amount)))

  def reduceMana(amount: Int): Field = copy(players = players.updated(0, players(0).reduceMana(amount)))

  def increaseMana(amount: Int): Field = copy(players = players.updated(0, players(0).increaseMana(amount)))

  def resetAndIncreaseMana(): Field = copy(players = players.updated(0, players(0).resetAndIncreaseMana()).updated(1, players(1).resetAndIncreaseMana()))

  def setPlayerNames(p1: String, p2: String): Field = copy(players = players.updated(0, players(0).setName(p1)).updated(1, players(1).setName(p2)))

  def setHpValues(amount: Int): Field = copy(players = players.updated(0, players(0).setHpValue(amount)).updated(1, players(1).setHpValue(amount)))

  def setManaValues(amount: Int): Field = copy(players = players.updated(0, players(0).setManaValue(amount)).updated(1, players(1).setManaValue(amount)))

  def switchPlayer() = if (turns != 0 && turns % 2 == 1)
  then copy(players = players.updated(0, players(0).resetAndIncreaseMana()).updated(1, players(1).resetAndIncreaseMana()).reverse, turns = turns + 1)
  else copy(players = players.reverse, turns = turns + 1)

  def getPlayerById(id: Int) = players.filter(_.id == id)(0)

  def getActivePlayer() = players(0)

  def toMatrix(): Matrix[String] = matrix
    .updateMatrix(0, 0, List[String]("-" * Field.standartFieldWidth))
    .updateMatrixWithMatrix(Field.offset, 0, getPlayerById(1).toMatrix())
    .updateMatrixWithMatrix(Field.offset + Field.standartMenueBarHeight + Field.standartGameBarHeight + Field.standartFieldBarHeight, 0, getPlayerById(2).toMatrix())

  override def toString() = toMatrix().rows.map(_.mkString("|", "", "|\n")).mkString
}
