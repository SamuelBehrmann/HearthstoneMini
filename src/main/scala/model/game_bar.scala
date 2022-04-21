package model

class GameBar {
    val hp: Healthpoints = new Healthpoints(100)
    val mana: Mana = new Mana(100)
    var hand: List[String] = List()
    val deck: List[Card] = null
    val eol = sys.props("line.separator")

    def hpSlot(): String = hp.toString
    def manaSlot(): String = mana.toString
    def handSlot(): String = "\u001b[39m Hand: " + hand.mkString("|", "|", "|")
    def deckSlot(): String = "\u001b[39m deck"

    override def toString() = hpSlot() + " " + manaSlot() + " "+ handSlot() + " " + deckSlot()
}
