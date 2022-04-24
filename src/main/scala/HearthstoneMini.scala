package scala

import model._

    @main
    def run(): Unit = {
        val player1 = new Player("heinrich", 1, new FieldBar(), new GameBar())
        val player2 = new Player("peter", 2, new FieldBar(), new GameBar())

        val field = new Field(size = 5, player1 = player1, player2 = player2)
        val karte = new Card("Der Brecher", 2, 4, 3, "Zerstoerung", "rare")

        //print(field.toString)
        val newField = field.placeCardPlayer1(0, karte)
        val newField1 = newField.placeCardPlayer2(3, karte)
        //print(newField1.toString)
        val newField2 = newField1.reduceHp(17)
        print(newField2.toString)
}
