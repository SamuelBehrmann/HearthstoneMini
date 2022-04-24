package model

case class FieldBar(cardArea: CardArea[CardType] = new CardArea[CardType](Field.standartSlotNum, new EmptyCard()), matrix: Matrix[String] = new Matrix[String](Field.standartFieldBarHeight, Field.standartFieldWidth, " ")):
    def this(size: Int, filling: CardType) = this(cardArea = new CardArea(size, filling), new Matrix[String](Field.standartFieldBarHeight, Field.standartSlotWidth * size, " "))
    val size = cardArea.size

    val eol = sys.props("line.separator")

    def bar(slotWidth: Int = Field.standartSlotWidth, slotNum: Int = Field.standartSlotNum): String = (("+" + "-" * slotWidth) * slotNum) + "+#"
    //def completeField(slotWidth: Int = Field.standartSlotWidth): String = matrix.rows.map((row) => row.mkString).mkString("", eol, "")

    //override def toString = completeField()
    def placeCard(slot: Int, card: CardType): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, card), matrix = matrix.updateMatrixWithMatrix(0, slot * Field.standartSlotWidth, card.toMatrix()))
    def removeCard(slot: Int): FieldBar = copy(cardArea = cardArea.replaceSlot(slot, EmptyCard()), matrix = matrix.updateMatrixWithMatrix(0, slot * Field.standartSlotWidth, EmptyCard().toMatrix()))

    def toMatrix(): Matrix[String] = matrix
    //.updateMatrix(0,0, List[String]("-" * Field.standartFieldWidth))
    .updateMatrix(5,0, List[String]("-" * Field.standartFieldWidth))

    //def attack() = {}
    //def useEffect() = {}


