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

}
