//"If you are on", space, street, [north option], [east option], [west option], [south option], ",", space, go, space, street, ".", new line;
package piconot


object PiconotGrammar extends App {
  implicit def funcToStr(func:Unit):String = func.toString()

  def If = {
    You
  }

  If.you.are.on("Second")("St.").and.you.can.go.uptown.and.you.cannot.go.outta_town.then.teleport.to("First")("St.")
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
  def and:You2 = {
    new You2(name, modifier, open)
  }
  def then:GoTo = {
    new GoTo(name, modifier, open)
  }
}

class GoTo(name:String, modifier:String, open:Array[Int]) {
  def go:FutureDirection = {
    new FutureDirection(name, modifier, open)
  }
  def teleport = {
    new On2(name, modifier, open, -1)
  }
}

class You2(name:String, modifier:String, open:Array[Int]) {
  def you:Can = {
    new Can(name, modifier, open)
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

class FutureDirection(name:String, modifier:String, open:Array[Int]) {
  def uptown:On2 = {
    new On2(name, modifier, open,0)
  }
  def outta_town:On2 = {
    new On2(name, modifier, open, 1)
  }
  def into_town:On2 = {
    new On2(name, modifier, open, 2)
  }
  def downtown:On2 = {
    new On2(name, modifier, open, 3)
  }
}

class On2(name:String, modifier:String, open:Array[Int], finalDirection:Int) {
  def on(name2:String)(modifier2:String) = {
    println("name: " + name + " modifier: " + modifier +" finalDirection: " + finalDirection )
    for (x <- Range(0,4)) {
      println(open(x))
    }
  }
  def to(name2:String)(modifier2:String) = {
    println("name: " + name + " modifier: " + modifier +" finalDirection: " + finalDirection )
    for (x <- Range(0,4)) {
      println(open(x))
    }
  }
}