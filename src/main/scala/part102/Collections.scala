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

  val flatmapIf = Some(3).flatMap(x => if(x == 3) Some(9) else None)
  println(flatmapIf)

  val flatmapEmptyIf = Option.empty[Int].flatMap(x => if(x == 3) Some(9) else None)





}
