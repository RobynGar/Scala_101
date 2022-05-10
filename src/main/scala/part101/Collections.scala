package part101

object Collections extends App {
  val everybodyNames = Seq("Connie", "Cristian", "Jacob", "Robyn", "Sarina", "Yonis")
  val randomMap = Map(1 -> "red", 2 -> "yellow", 3 -> "blue", 4 -> "refrigerator")
  val numSeq = Seq(6, 8, 3, 9, 2, 7)
  val addOne = numSeq.map(x => x + 1)
  val removeAllEvenNums = numSeq.filter(x => x % 2 != 0)
  //  val characterSeq = Seq("t", "g", "j", "d")
  //  val containLetterT = characterSeq.contains("t")
  val containLetterT = everybodyNames.flatten.contains('t')
  println(addOne)
  println(removeAllEvenNums)
  println(containLetterT)
  println(everybodyNames.flatten)

  //////102 addition//////

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
