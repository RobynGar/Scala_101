package part102

import scala.collection.immutable.Queue

object Collections extends App{

  //stream
 // val aStream = Stream(0, 1, 2, 3, 4, 5)
  //streams seem to be deprecated

  //queue
  val queName = Queue(1, 2, 3, 4, 5, 6, 7, 8)
  println(queName.enqueue(10))

  //set
  val setOne = Set(1, 2, 2, 3, 4, 5, 5)
  println(setOne)

  //Map
    //using tuples
  val tupleMap = Map(("x", 24), ("y", 25), ("z", 26))
    //OR using the more common arrow notation
  val arrowMap = Map("x" -> 24, "y" -> 25, "z" -> 26)

  println(tupleMap)
  println(arrowMap)

  val wildcard = List(1,3,2).map(_ * 2) //_ can be used as a shorthand/ placeholder to represent the input value rather than .map(number => number * 2)
  println(wildcard)
  val mapPM = List(1,2,3).map{
    case 1 => 10
    case x => x * 2
  }
  //map with pattern matching
  println(mapPM)

  val mapOfMapWithSingleOutput = Map("myKey" -> "myValue").map{case (key, value) => s"key: $key, value: $value"}
  println(mapOfMapWithSingleOutput)

  val mapOfMapWithTupleOutput = Map("myKey" -> "myValue").map{case (key, value) => value -> key}
  println(mapOfMapWithTupleOutput)

  // nested collections
  val nestedList = List(2,6,4).map(x => List(x*2, x*4, x*6))
  println(nestedList)
  //OR
  val nestedFlattenList = List(2,6,4).map(x => List(x*2, x*4, x*6)).flatten
  println(nestedFlattenList)
  //OR
  val nestedFlatmapList = List(2,6,4).flatMap(x => List(x*2, x*4, x*6)) //does the same as above but uses one method (flatmap) rather than two (map and flatten)
  println(nestedFlatmapList)

  val flatmapCase = List(1,2,3).flatMap{
    case x@(1|3) => Some(x*2)
    //@ is used for variable binding, binding a variable to a full or partial match result
    //x is bound to 1 or 3 as the @ is between them
    //only include the @ when you want to also deal with the object
    //x at 1 or 3 do this
    case _       => None
  }
  println(flatmapCase)

  //mapping on an option
  val mapOnOption = Some(3).map(number => number * 3)
  println(mapOnOption)

  val mapOnOptionCase = Some(3).map{
    case 1 => 10
    case x => x * 2
  }
  println(mapOnOptionCase)

  val optionEmpty = Option.empty[Int].map(number => number * 3)
  // if option is empty then return none
  println(optionEmpty)

  //The flatMap function in options is similar to the collections flatMap and is used when the return value is itself an option
  val flatmapIf = Some(3).flatMap(x => if(x == 3) Some(9) else None)
  val flatmapElse = Some(2).flatMap(x => if(x == 3) Some(9) else None)
  println(flatmapIf)
  println(flatmapElse)

  val flatmapEmptyIf = Option.empty[Int].flatMap(x => if(x == 3) Some(9) else None)
  println(flatmapEmptyIf)

  //map with either

  val mapEither = Right[String, Int](3).map(number => number * 3)
  println(mapEither)

  val mapEitherCase = Right[String, Int](1).map{
    case 1 => 10
    case x => x * 2
  }
  println(mapEitherCase)

  val mapEitherLeft = Left[String, Int]("Hi").map(number => number * 3)
  println(mapEitherLeft)

  //FLATMAP EITHER
  println(Right[String, Int](3).flatMap(x => if(x == 3) Right[String, Int](9) else Left[String, Int]("false")))
  println(Right[String, Int](2).flatMap(x => if(x == 3) Right[String, Int](9) else Left[String, Int]("false")))
 // println(Right[String, Int]("Hey").flatMap(x => if(x == 3) Right[String, Int](9) else Left[String, Int]("false"))) //this will not compile as we have used Right at the beginning and tried to put a string in it when in the statement Right[String, Int] specifies the Right side will be an Int as the right side of the [] is what the Right statement is expecting
  println(Left[String, Int]("Hi").flatMap(x => if(x == 3) Right[String, Int](9) else Left[String, Int]("false"))) //this will print Left(Hi) overriding the false written in the left side in the else


  //////exercise
  def capitalMacbooks(macbooks: String): String = {
    macbooks.map(char => char.toUpper)
  }

  println(capitalMacbooks("macbooks are the best"))

  def multiplyBy2AndSum(stringList: List[String]): Int = {
    stringList.map(x => x.toInt).map(n => n * 2).sum

  }

  println(multiplyBy2AndSum(List("2","5","8","9","3")))

  def multiply12OrReturn12(possibleInt: Option[Int]): Int ={
    possibleInt match {
      case Some(v) => v * 12
      case _ => 12
    }
  }
  println(multiply12OrReturn12(Some(3)))
  println(multiply12OrReturn12(None))

  val scores = Seq(None, Some("A"), Some("B"), Some("C"), None, Some("F"))

  println(scores.filter(x => x != None))
  println(scores.filter(x => x != None).flatten)// same as above without the some in front of each grade

  println(scores.map(x => if(x == None) Some("F") else x))
  println(scores.flatMap(x => if(x == None) "F" else x)) //same as above but in the output you do not get all the grades with Some in front of them

}
