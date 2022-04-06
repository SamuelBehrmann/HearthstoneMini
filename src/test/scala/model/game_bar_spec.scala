package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameBarSpec extends AnyWordSpec with Matchers {
   "A Gamebar" when {
    "empty" should {
      "be created using nothing" in {
          val gameBar = new GameBar()
          print(gameBar.hpSlot() + " " + gameBar.manaSlot() + " " + gameBar.handSlot() + " " + gameBar.deckSlot())
          gameBar.hp.value should be(100)
      }
    }
  }
}
