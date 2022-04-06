package scala

import model.FieldBar

@main
def run(): Unit = {
    val field = new FieldBar(5, "      ")
    print(field)

}