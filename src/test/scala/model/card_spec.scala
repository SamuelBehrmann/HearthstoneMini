package model
import model.Matrix
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardSpec extends AnyWordSpec with Matchers {
   "A Card" when {
    "filled" should {
      val card = new Card("test", 2, 2, 2, "Schmettern", "rare")
      "have a String repesentation" in {
        card.toString() should be("test (2)#atk: 2#def: 2#Schmettern#rare")
      }
      "have a Matrix repesentation" in {
        card.toMatrix().toString should be("Matrix(Vector(Vector(t, e, s, t,  , (, 2, ),  ,  ,  ,  ,  ,  ,  ), Vector(a, t, k, :,  , 2,  ,  ,  ,  ,  ,  ,  ,  ,  ), Vector(d, e, f, :,  , 2,  ,  ,  ,  ,  ,  ,  ,  ,  ), Vector(S, c, h, m, e, t, t, e, r, n,  ,  ,  ,  ,  ), Vector(r, a, r, e,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  )))")

      }
      "have a name" in {
        card.name should be("test")
      }
      "have Manacost" in {
        card.manaCost should be(2)
      }
      "have atk Value" in {

        card.attValue should be(2)
      }
      "have def Value" in {
        card.defenseValue should be(2)
      }
      "have an effekt" in {
        card.effect should be("Schmettern")
      }
      "have a Rarity" in {
        card.rarity should be("rare")
      }
    }
  }
}