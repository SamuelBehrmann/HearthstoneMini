package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameBarSpec extends AnyWordSpec with Matchers {
   "A Gamebar" when {
     val gameBar = new GameBar(List("1", "2", "3", "4", "5"))
    "empty" should {
      "be created using nothing" in {
          val gameBar = new GameBar(List("1", "2", "3", "4", "5"))
          //print(gameBar.hpSlot() + " " + gameBar.manaSlot() + " " + gameBar.handSlot() + " " + gameBar.deckSlot())
          gameBar.hp.value should be(100)
      }
      "converted to String" when{
        "gameBar.toString" in {
          gameBar.hpSlot() should be ("\u001b[32m100")
          gameBar.manaSlot() should be ("\u001b[34m100")
          gameBar.handSlot() should be ("\u001b[30mHand: |1| |2| |3| |4| |5|")
          gameBar.deckSlot() should be ("\u001b[30mdeck")
          gameBar.toString() should be ("\u001b[32m100 " + "\u001b[34m100 " + "\u001b[30mHand: |1| |2| |3| |4| |5|" +
            "\n" + "\u001b[30mdeck")
        }

      }
    }
    "removed" should {
      "removing a Card" in {
        gameBar.removeCard("2").hand should be (List("1", "3", "4", "5"))
      }
    }
  }
}
