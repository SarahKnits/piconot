package piconot

import java.io.File

import PiconotGrammar._
import picolib.maze.Maze
import picolib.semantics.{GUIDisplay, TextDisplay, Picobot}
import piconot.PiconotGrammar.rules

import scalafx.application.JFXApp

object Empty extends JFXApp {
  val emptyMaze = Maze("resources" + File.separator + "empty.txt")

  /*
    0 ***x -> S 0
    0 *x*S -> E 0
    0 *E*S -> X 1

    1 x*** -> N 1
    1 N*x* -> W 2

    2 ***x -> S 2
    2 **xS -> W 1
   */

  If. you. are. on("Main")("St."). and. can. go. downtown. then. go. downtown. on("Main")("St.")
  If. you. are. on("Main")("St."). and. cannot. go. downtown. and. can. go. outta_town. then. go. outta_town. on("Main")("St.")
  If. you. are. on("Main")("St."). and. cannot. go. downtown. and. cannot. go. outta_town. then. teleport. to("First")("Blvd.")

  If. you. are. on("First")("Blvd."). and. can. go. uptown. then. go. uptown. on("First")("Blvd.")
  If. you. are. on("First")("Blvd."). and. cannot. go. uptown. and. can. go. into_town. then. go. into_town. on("Second")("St.")

  If. you. are. on("Second")("St."). and. can. go. downtown. then. go. downtown. on("Second")("St.")
  If. you. are. on("Second")("St."). and. cannot. go. downtown. and. can. go. into_town. then. go. into_town. on("First")("Blvd.")

  object EmptyBot extends Picobot(emptyMaze, rules)
    with TextDisplay with GUIDisplay

  stage = EmptyBot.mainStage
}
