package part102

import scala.util.Random

object HigherOrderFunctions extends App{

  def calculate(a: Int, b: Int, fun: (Int, Int) => Int): Int = fun(a, b)

  val add = (val1: Int, val2: Int) => val1 + val2

//  def add(val1: Int, val2: Int) = {
//    val1 + val2
//  }

  //println(calculate(add, 1, 2))
  println(calculate(4, 6, add))

  def calculatesCircle(func: (Int, Double) => Double, val1: Int, val2: Double = 3.14): Double = func(val1, val2)
  def circle(func: (Int) => Double, val1: Int): Double = func(val1)

  val area: (Int) => Double = (r) =>  3.14 * (r * r) //you cannot enter default parameters defining functions with a val
 ////OR/////
  def areaOfCircle(r: Int, pi: Double = 3.14): Double = {
    pi * (r * r)
  }

  println(calculatesCircle(areaOfCircle, 5))
  println(circle(area, 5))
  println(areaOfCircle(5))
  println(areaOfCircle(3))

  ///////all below circumference function are doing the same thing but written in different ways
  //val circumference = (r: Int) =>  2 * 3.14 * r //implicit return type
  //val circumference: Int => Double = (r) =>  2 * 3.14 * r //explicit return type of a Double return type is declared after the first =>
  //val circumference: Int => Double = (r) =>  {2 * 3.14 * r} //same as above but the body is just inside {}
  val circumference: Int => Double = r =>  {2 * 3.14 * r} //same as above but the parameter name r is not inside () as there is only one parameter the () are not needed

  // method version of the circumference function where default parameters can be used for pi
  def circumferenceOfCircle(r: Int, pi: Double = 3.14): Double = {
   2 * pi * r
  }

  println(calculatesCircle(circumferenceOfCircle,9))
  println(circle(circumference, 9))


//Create a higher order function that takes a list of radii
  // and accepts either of the circle functions above.
  // Return a list of areas or circumferences for the passed in radii.

def circleList(func: (Int) => Double, r: List[Int]): List[Double] = r.map(x => func(x))
println(circleList(area, List(5, 3, 7)))


  //////////spongebob///////

  def spongeBobQuotes(character: String): String = {
    character match {
      case "Spongebob" => "Gary, I’m absorbing his blows like I’m made of some sort of spongy material"
      case "Patrick" => "Dumb people are always blissfully unaware of how dumb they really are…(drools)"
      case "Plankton" => "Holographic Meatloaf? My favorite!"
      case "Squidward" => "Too bad SpongeBob’s not here to enjoy Spongebob not being here."
      case _ => "Well, it’s no secret that the best thing about a secret is secretly telling someone your secret, thereby adding another secret to their secret collection of secrets, secretly."
    }
  }

  println(spongeBobQuotes("Patrick"))
  println(spongeBobQuotes("Crab"))

  ///////philosophy///////

  val philoQuotes = List("Happiness is not an ideal of reason but of imagination", "No man's knowledge here can go beyond his experience", "One cannot conceive anything so strange and so implausible that it has not already been said by one philosopher or another", "We are what we repeatedly do. Excellence, then, is not an act, but a habit", "All that is necessary for the triumph of evil is that good men do nothing", "I would never die for my beliefs because I might be wrong")


  def quotes(quote: Option[String]) = {
  quote match {
    case Some(quote) => quote
    case None => Random.shuffle(philoQuotes).head
  }
  }


  println(quotes(None))
  println(quotes(None))
  println(quotes(Some("To ignore a Loki is to get a headache")))



}
