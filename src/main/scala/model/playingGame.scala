package model

class playingGame {
  println("Hearthstone Mini Spielbeginn: Bitte Namen von Spieler 1 und Spieler 2 eingeben.\n ")
  println("Spieler 1: ")
  val nameS1: String = scala.io.StdIn.readLine()
  println("Spieler 2: ")
  val nameS2: String = scala.io.StdIn.readLine()

  val field = new Field(5, nameS1, nameS2)


  print(field)

  println(nameS1 + " Karte legen. ( Karte + Kartenplatz angeben) \n" )
  val card: Int = scala.io.StdIn.readInt()
  val cardPlace: Int = scala.io.StdIn.readInt()
  val placeCard: Field = field.placeCardP1(cardPlace, field.gameBarP1.hand(card - 1))
  print(placeCard)

  println(nameS2 + " Karte legen. ( Karte + Kartenplatz angeben) \n" )
  val cardS2: Int = scala.io.StdIn.readInt()
  val cardPlaceS2: Int = scala.io.StdIn.readInt()
  val placeCardS2: Field = placeCard.placeCardP2(cardPlaceS2, placeCard.gameBarP2.hand(cardS2 - 1))
  print(placeCardS2)

  println("\u001B[30mSchönes Spiel, Danke!!")

}
