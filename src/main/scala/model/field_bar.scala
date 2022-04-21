package model

case class FieldBar(cardArea: CardArea[Any], matrix: Matrix[String]):
    def this(size: Int, filling: Any) = this(new CardArea(size, filling), new Matrix[String](Card.height, Card.width * (size + 2), "s"))
    val size = cardArea.size
    

    val eol = sys.props("line.separator")
    def bar(slotWidth: Int = Card.width, slotNum: Int = 5): String = (("+" + "-" * slotWidth) * slotNum) + "+#" 

    def completeField(slotWidth: Int = Card.width + 2): String = matrix.rows.map((row) => row.mkString).mkString("", eol, "")

    //override def toString = completeField()
    def placeCard(slot: Int, card: Any): FieldBar = copy(cardArea.replaceSlot(slot - 1, card),matrix.updateMatrixWithMatrix(0,(slot-1) * (Card.width + 2), card.asInstanceOf[Card].toMatrix()))
    def removeCard(slot: Int): FieldBar = copy({
        cardArea.replaceSlot(slot, "")
    })

    //def attack() = {}
    //def useEffect() = {}


