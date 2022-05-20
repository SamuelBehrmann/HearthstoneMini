package controller

object GameState extends Enumeration {
    type GameState = Value
    val ERROR, PREGAME, MAINGAME, EXIT, WIN = Value
}