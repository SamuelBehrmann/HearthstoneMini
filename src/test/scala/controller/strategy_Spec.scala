package controller

package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import _root_.model.Field
import _root_.model.Move
import _root_.model.Card
import _root_.model.EmptyCard
import _root_.model.Player
import util.Observer


class StrategySpec extends AnyWordSpec with Matchers {
  "A strategy" when {
    "was chosen normal" in {
      Strategy.normalStrategy().players(0).gamebar.hp.value should be (30)
    }
    "when chosen hardcore" in {
      Strategy.hardcoreStrategy().players(0).gamebar.hp.value should be (10)
      Strategy.hardcoreStrategy().players(0).gamebar.mana.value should be (5)
    }
    "when chosen admin" in {
      Strategy.adminStrategy().players(1).gamebar.hp.value should be (100)
      Strategy.adminStrategy().players(1).gamebar.mana.value should be (100)
    }
  }
}

