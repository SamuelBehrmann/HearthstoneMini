package model

case class Player(name: String, fieldbar: FieldBar, gamebar: GameBar /* menubar: MenuBar */) {
    override def toString() = name
}