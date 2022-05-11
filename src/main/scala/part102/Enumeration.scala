package part102

import part102.Enumeration.Weekday.Monday
import part102.Enumeration.Weekday.Sunday

object Enumeration extends App {

  object Weekday extends Enumeration{
    val  Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday = Value
  }

//serialised
  println(Weekday.Monday)
  println(Weekday.Monday.toString)

  //deserialised
  println(Weekday.withName("Monday"))
 // println(Weekday.withName("Mondai")) // this will throw an exception for no value found as there is no mondai in the object weekday

  println(Weekday.values)
  println(Weekday.Monday < Weekday.Tuesday)
  println(Weekday.Monday > Weekday.Friday)
  println(Weekday.Sunday > Weekday.Wednesday)



  println(UniqueWeekdays.Thursday)
  println(UniqueWeekdays.Tuesday.toString)



  println(OrderModifiedWeekdays.values.toList.sorted)

  //pattern matching is nonExhaustive

  def nonExhaustive(weekday: Weekday.Value) {
    weekday match {
      case Monday => println("I hate Mondays")// you need to import to make this work to specify which enum you are talking about as after erasure all enums are expected to have the same type and the compiler does not know which one you are talking about as it thinks they are all of the same type
      case Sunday => println("The weekend is already over? :( ")
    }
  }

}



object UniqueWeekdays extends Enumeration{
  val Monday = Value("Mon")
  val Tuesday = Value("Tue")
  val Wednesday = Value("Wed")
  val Thursday = Value("Thu")
  val Friday = Value("Fri")
  val Saturday = Value("Sat")
  val Sunday = Value("Sun")
}

object OrderModifiedWeekdays extends Enumeration {

  val Monday = Value(6)
  val Tuesday = Value(5)
  val Wednesday = Value(4)
  val Thursday = Value(3)
  val Friday = Value(2)
  val Saturday = Value(1)
  val Sunday = Value(0)

}

