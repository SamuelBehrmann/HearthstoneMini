package controller

object GameState extends Enumeration {
    type GameState = Value
    val ERROR, PREGAME, MAINGAME, EXIT = Value

    val map = Map[GameState, String] (
        ERROR -> "Ungültiger Befehl oder fehlerhafte Eingabe!",
        PREGAME -> "",
        MAINGAME -> "",
        EXIT -> "Danke fürs spielen!"
    )
    def message(gameState: GameState) = map(gameState)
}