package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameBarSpec extends AnyWordSpec with Matchers {
  "A Gamebar" when {
    val gameBar = GameBar()
    "created" should {
      "be created in initial state" in {
        gameBar.toMatrix().toAString() should be("\u001b[32mHP: 30 \u001b[0;34mMana: 1\u001b[0;37m                                                   \u001b[0;31mDeck: 4  Friedhof: 0\u001b[0;37m\n " +
          "Brecher (2)      Brecher (2)      Brecher (2)      Brecher (2)                      \n " +
          "atk: 3           atk: 3           atk: 3           atk: 3                           \n " +
          "def: 4           def: 4           def: 4           def: 4                           \n " +
          "Truemmer         Truemmer         Truemmer         Truemmer                         \n " +
          "Legende          Legende          Legende          Legende                          \n" +
          "-------------------------------------------------------------------------------------\n")
      }
    }
    "you draw a card" in {
      val gameBar1 = GameBar()
      gameBar1.drawCard().hand.length should be (5)
    }
    "a card is removed from hand" in {
      gameBar.removeCardFromHand(2).hand.length should be(3)
    }
    "a card is added to hand" in {
      gameBar.addCardToHand(new EmptyCard()).hand.length should be(5)
    }
    "hp reduced by 20" in {
      gameBar.reduceHp(20).hp.value should be (10)
    }
    "hp increased by 40" in {
      gameBar.increaseHp(40).hp.value should be (70)
    }
    "mana reduced by 10" in {
      gameBar.reduceMana(10).mana.value should be (0)
    }
    "mana increased" in {
      gameBar.increaseMana(10).mana.value should be (1)
    }
    "card added to Friedhof" in {
      gameBar.addCardToFriedhof(new EmptyCard()).friedhof.length should be (1)
    }
    "reset and increased mana" in {
      val afterAlter = gameBar.resetAndIncreaseMana()
      afterAlter.mana.value should be(gameBar.mana.value + 1)
      afterAlter.mana.max should be(gameBar.mana.max + 1)
    }
  }
}

