package model

case class GameBar(hp: Healthpoints = new Healthpoints(100), mana: Mana = new Mana(100), hand: List[String], deck: List[Card]) {
    def this(newHand: List[String]) = this(new Healthpoints(100), new Mana(100), newHand, List[Card]())

    val eol = sys.props("line.separator")

    def hpSlot(): String = hp.toString
    def manaSlot(): String = mana.toString
    def handSlot(): String = "\u001b[30mHand: " + hand.mkString("|", "| |", "|")
    def deckSlot(): String = "\u001b[30mdeck"
    
    def removeCard(card: String) = copy(hp, mana, hand.filter(_ != card), deck)

    override def toString() = hpSlot() + " " + manaSlot() + " "+ handSlot() + "\n" + deckSlot()
}
