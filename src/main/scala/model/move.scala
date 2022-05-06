package model

import scala.annotation.meta.field

case class Move(handSlot: Int = 0, fieldSlot: Int = 0, amount: Int = 0, p1: String = "player1", p2: String = "player2")
