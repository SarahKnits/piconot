package piconot

/*
 * Authors: Hayden Blauzvern and Sarah Gilkinson
 */

import picolib.semantics._

/*
 * Defines the grammar outlined in grammar-actual.txt
 * Implemented with A LOT of objects
 */
object PiconotGrammar {

  // Set of publicly available picobot rules
  var rules: List[Rule] = List()

  // Mapping from user defined states to picobot states
  var streetsToStates: Map[String, String] = Map()

  object If {
    def you = {
      Are
    }
  }

  object Are {
    def are = {
      On
    }
  }

  object On {
    def on(name: String)(modifier: String): And = {
      val open = Array.fill[Int](4)(-1) // Array to hold surroundings information
      new And(name, modifier, open)
    }
  }

  class And(name: String, modifier: String, open: Array[Int]) {
    def and: Can = {
      new Can(name, modifier, open)
    }

    def then: GoNext = {
      new GoNext(name, modifier, open)
    }
  }

  class GoNext(name: String, modifier: String, open: Array[Int]) {
    def go: DirectionNext = {
      new DirectionNext(name, modifier, open)
    }

    def teleport: OnNext = {
      new OnNext(name, modifier, open, -1)
    }
  }

  class Can(name: String, modifier: String, open: Array[Int]) {
    def can: Go = {
      new Go(name, modifier, open, true)
    }

    def cannot: Go = {
      new Go(name, modifier, open, false)
    }
  }

  class Go(name: String, modifier: String, open: Array[Int], can: Boolean) {
    def go: Direction = {
      new Direction(name, modifier, open, can)
    }
  }

  class Direction(name: String, modifier: String, open: Array[Int], can: Boolean) {
    def uptown: And = {
      if (can) open(0) = 0 else open(0) = 1
      new And(name, modifier, open)
    }

    def outta_town: And = {
      if (can) open(1) = 0 else open(1) = 1
      new And(name, modifier, open)
    }

    def into_town: And = {
      if (can) open(2) = 0 else open(2) = 1
      new And(name, modifier, open)
    }

    def downtown: And = {
      if (can) open(3) = 0 else open(3) = 1
      new And(name, modifier, open)
    }
  }

  class DirectionNext(name: String, modifier: String, open: Array[Int]) {
    def uptown: OnNext = {
      new OnNext(name, modifier, open, 0)
    }

    def outta_town: OnNext = {
      new OnNext(name, modifier, open, 1)
    }

    def into_town: OnNext = {
      new OnNext(name, modifier, open, 2)
    }

    def downtown: OnNext = {
      new OnNext(name, modifier, open, 3)
    }
  }

  class OnNext(name: String, modifier: String, open: Array[Int], finalDirection: Int) {
    def on(nameNext: String)(modifierNext: String) = {
      rules = rules :+ Rule(getState(name + " " + modifier),
        getSurroundings(open),
        getDirection(finalDirection),
        getState(nameNext + " " + modifierNext))
    }

    def to(nameNext: String)(modifierNext: String) = {
      rules = rules :+ Rule(getState(name + " " + modifier),
        getSurroundings(open),
        getDirection(finalDirection),
        getState(nameNext + " " + modifierNext))
    }
  }

  //----------------------------------------

  def getState(street: String): State = {
    streetsToStates.get(street) match {
      case Some(stateNum:String) => State(stateNum)
      case None => {
        streetsToStates = streetsToStates + (street -> streetsToStates.size.toString) // not very scala-y
        getState(street)
      }
    }
  }

  def getRelDes(action: Int): RelativeDescription = {
    action match {
      case 0 => Open // can
      case 1 => Blocked // can't
      case -1 => Anything // default
    }
  }

  def getSurroundings(surroundings: Array[Int]): Surroundings = {
    Surroundings(getRelDes(surroundings(0)), getRelDes(surroundings(1)),
      getRelDes(surroundings(2)), getRelDes(surroundings(3)))
  }

  def getDirection(direction: Int): MoveDirection = {
    direction match {
      case 0 => North
      case 1 => East
      case 2 => West
      case 3 => South
      case _ => StayHere
    }
  }
}