package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class game_Bar_Spec extends AnyWordSpec with Matchers {
  "A Gamebar" when {
    val gameBar = GameBar()
    "created" should {
      "be created in initial state" in {
        gameBar.toMatrix().toAString() should be("\u001b[32mHP: 100 \u001b[0;34mMana: 10\u001b[0;37m                                                 \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n" +
          "-------------------------------------------------------------------------------------\n")
      }
    }
    "when you draw a card" in {
      gameBar.drawCard().hand.length should be (5)
    }
    "a card is removed from hand" in {
      gameBar.removeCardFromHand(2).hand.length should be(3)
    }
    "a card is added to hand" in {
      gameBar.addCardToHand(new EmptyCard()).hand.length should be(5)
    }
    "when hp reduced by 20" in {
      gameBar.reduceHp(20).hp.value should be (80)
    }
    "when hp increased by 40" in {
      gameBar.increaseHp(40).hp.value should be (140)
    }
    "when mana reduced by 10" in {
      gameBar.reduceMana(10).mana.value should be (0)
    }
    "when mana increased by 50" in {
      gameBar.increaseMana(50).mana.value should be (60)
    }
    "when card added to Friedhof" in {
      gameBar.addCardToFriedhof(new EmptyCard()).friedhof.length should be (1)
    }
  }
}

