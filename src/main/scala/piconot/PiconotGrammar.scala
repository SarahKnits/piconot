//"If you are on", space, street, [north option], [east option], [west option], [south option], ",", space, go, space, street, ".", new line;
package piconot

import com.sun.xml.internal.xsom.impl.scd.Iterators.Array

object PiconotGrammar extends App {
  implicit def funcToStr(func:Unit):String = func.toString()

  def If:You= {
    new You
  }
  ((((((((If you) are) on)("Second")("St.")) and) you) can) go) north
}
class You {
  def you:Are = {
    new Are
  }
}

class Are {
  def are:On = {
    new On
  }
}

class On {
  def on(name:String)(modifier:String):And = {
    new And(name, modifier)
  }
}

class And(name:String, modifier:String) {
  def and:You2 = {
    new You2(name, modifier)
  }
  def then
}

class You2(name:String, modifier:String) {
  def you:Can = {
    new Can(name, modifier)
  }
}

class Can(name:String, modifier:String) {
  def can:Go = {
    new Go(name, modifier, true)
  }
  def cannot:Go = {
    new Go(name, modifier, false)
  }
}

class Go(name:String, modifier:String, can:Boolean) {
  def go:Direction = {
    println("name: " + name)
    println("modifier: " + modifier)
    println("can: " + can.toString())
    new Direction(name, modifier, can)
  }
}

class Direction(name:String, modifier:String, can:Boolean) {
  def north:Unit = {
    println("north")
  }
  def east:Unit = {
    println("east")
  }
  def west:Unit = {
    println("west")
  }
  def south:Unit = {
    println("south")
  }

}
