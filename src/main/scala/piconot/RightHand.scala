package piconot

import picolib.semantics.Rule
import PiconotGrammar._


object RightHand extends App {

  If. you. are. on("Main")("St."). and. can. go. outta_town. then. go. outta_town. on("Second")("St.")
  If. you. are. on("Main")("St."). and. cannot. go. outta_town. and. can. go. uptown. then. teleport. to("Third")("St.")
  If. you. are. on("Main")("St."). and. cannot. go. outta_town. and. can. go. uptown. and. can. go. into_town. then. go. into_town. on("Fourth")("St.")
  println(rules)
}
