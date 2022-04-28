package model

case class FieldBar(cardArea: CardArea[CardType] = new CardArea[CardType](Field.standartSlotNum, new EmptyCard()), matrix: Matrix[String] = new Matrix[String](Field.standartFieldBarHeight, Field.standartFieldWidth, " ")):
    def this(size: Int, filling: CardType) = this(cardArea = new CardArea(size, filling), new Matrix[String](Field.standartFieldBarHeight, Field.standartSlotWidth * size, " "))
    val size = cardArea.size

    val eol = sys.props("line.separator")

    def placeCard(slot: Int, card: CardType): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, card), matrix = matrix.updateMatrixWithMatrix(0, slot * Field.standartSlotWidth + 1, card.toMatrix()))
    def removeCard(slot: Int): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, EmptyCard()), matrix = matrix.updateMatrixWithMatrix(0, slot * Field.standartSlotWidth + 1, EmptyCard().toMatrix()))

    def toMatrix(): Matrix[String] = matrix
    .updateMatrix(5,0, List[String]("-" * Field.standartFieldWidth))

    //def attack() = {}
    //def useEffect() = {}


