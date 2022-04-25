package scala

import model._

@main
def run(): Unit = {
    val player1 = new Player("Heinrich", 1, new FieldBar(), new GameBar())
    val player2 = new Player("Peter", 2, new FieldBar(), new GameBar())

    val field = new Field(size = 5, player1 = player1, player2 = player2)
    val karte = new Card("Der Brecher", 2, 4, 3, "Zerstoerung", "rare")

    //print(field.toString)
    val newField = field.placeCardPlayer1(0, karte)
    val newField1 = newField.placeCardPlayer2(3, karte)
    val newField2 = newField1.reduceHp(17)
    print(newField2.toString)
    val newField3 = newField2.drawCardPlayer1()
    print(newField3)
    //TODO: Hand Dynamisch machen
    //TODO: Place card, ohne card parameter. sollte eine von der hand nehmen
    //TODO: player mit auto index
    //TODO: methoden umstellen sodass eine spieler-ID übergebenwerden kann
    //TODO: Friedhof und Deck müssen noch mit aufs Spielfeld
    //TODO: deck mischen implementieren
    //TODO: Tests schreiben
    //TODO:
}
