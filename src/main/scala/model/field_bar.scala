package model

case class FieldBar(cardArea: CardArea[Any]):
    def this(size: Int, filling: Any) = this(new CardArea(size, filling))
    val size = cardArea.size
    var matrix = new Matrix[String](Card.height, Card.width * size, "0")

    def bar(slotWidth: Int = Card.width, slotNum: Int = 5): String = (("+" + "-" * slotWidth) * slotNum) + "+#"
    def slots() = cardArea.row.map((card) => matrix = matrix.updateMatrixWithMatrix(0,cardArea.row.toList.indexOf(card) * Card.width,card.asInstanceOf[Card].toMatrix()))

    //def completeField(slotWidth: Int = Card.width): String = bar(slotWidth, size) + slots(slotWidth) + bar(slotWidth,size)

    //override def toString = completeField()
    def placeCard(slot: Int, card: Any): FieldBar = copy(cardArea.replaceSlot(slot, card))
    def removeCard(slot: Int): FieldBar = copy({
        cardArea.replaceSlot(slot, "")
    })

    //def attack() = {}
    //def useEffect() = {}


