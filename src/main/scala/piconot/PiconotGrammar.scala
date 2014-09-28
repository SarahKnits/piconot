package piconot


object PiconotGrammar extends App {

  def If = {
    You
  }

  If. you. are. on("Second")("St."). and. can. go. uptown. and. cannot. go. outta_town. then. teleport. to("First")("St.")
  If. you. are. on("Main")("St."). and. cannot. go. into_town. and. can. go. downtown. then. go. downtown. on("Second")("Blvd.")
}

object You {
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
  def on(name:String)(modifier:String):And = {
    val open = Array.fill[Int](4)(-1)
    new And(name, modifier, open)
  }
}

class And(name:String, modifier:String, open:Array[Int]) {
  def and:Can = {
    new Can(name, modifier, open)
  }
  def then:GoNext = {
    new GoNext(name, modifier, open)
  }
}

class GoNext(name:String, modifier:String, open:Array[Int]) {
  def go:DirectionNext = {
    new DirectionNext(name, modifier, open)
  }
  def teleport:OnNext = {
    new OnNext(name, modifier, open, -1)
  }
}

class Can(name:String, modifier:String, open:Array[Int]) {
  def can:Go = {
    new Go(name, modifier, open, true)
  }
  def cannot:Go = {
    new Go(name, modifier, open, false)
  }
}

class Go(name:String, modifier:String, open:Array[Int], can:Boolean) {
  def go:Direction = {
    new Direction(name, modifier, open, can)
  }
}

class Direction(name:String, modifier:String, open:Array[Int], can:Boolean) {
  def uptown:And = {
    if (can) open(0) = 0 else open(0) = 1
    new And(name, modifier, open)
  }
  def outta_town:And = {
    if (can) open(1) = 0 else open(1) = 1
    new And(name, modifier, open)
  }
  def into_town:And = {
    if (can) open(2) = 0 else open(2) = 1
    new And(name, modifier, open)
  }
  def downtown:And = {
    if (can) open(3) = 0 else open(3) = 1
    new And(name, modifier, open)
  }

}

class DirectionNext(name:String, modifier:String, open:Array[Int]) {
  def uptown:OnNext = {
    new OnNext(name, modifier, open,0)
  }
  def outta_town:OnNext = {
    new OnNext(name, modifier, open, 1)
  }
  def into_town:OnNext = {
    new OnNext(name, modifier, open, 2)
  }
  def downtown:OnNext = {
    new OnNext(name, modifier, open, 3)
  }
}

class OnNext(name:String, modifier:String, open:Array[Int], finalDirection:Int) {
  def on(name2:String)(modifier2:String) = {
    for (x <- Range(0,4)) {
      println(open(x))
    }
  }
  def to(name2:String)(modifier2:String) = {
    for (x <- Range(0,4)) {
      println(open(x))
    }
  }
}