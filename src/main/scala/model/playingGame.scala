package model

class playingGame {
  println("Hearthstone Mini Spielbeginn: Bitte Namen von Spieler 1 und Spieler 2 eingeben.\n ")
  println("Spieler 1: ")
  val nameS1: String = scala.io.StdIn.readLine()
  println("Spieler 2: ")
  val nameS2: String = scala.io.StdIn.readLine()

  val field = new Field(5)
  field.menuBarP1 = nameS1
  field.menuBarP2 = nameS2

  field.gameBarP1.hand = List("1", "2", "3", "4", "5")
  field.gameBarP2.hand = List("6", "7", "8", "9", "10")

  print(field)

  println(nameS1 + " Karte legen. ( Karte + Kartenplatz angeben) \n" )
  //ints auslesen -> int1 = karte -> int2 = platz
  val card: Int = scala.io.StdIn.readInt()
  val cardPlace: Int = scala.io.StdIn.readInt()


  val placeCard: FieldBar = new FieldBar(2, " ").placeCard(card, cardPlace)
  


  // Karte von Hand auf Spielfeld legen
  // Karte von Hand entfernen

}
