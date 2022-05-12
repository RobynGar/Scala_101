package part102

object SealedCaseObjects extends App{

  //for enumeration using sealed traits pattern matching is exhaustive
  def test(weekdays: WeekdayTrait) = {
    weekdays match {
      case Monday => println("I hate Mondays")
      case Sunday => println("The weekend is already over? :( ")

    }
  }

  println(test(Monday))

}
//another way to do enumeration is sealed case objects
//cons: no easy way to get all values, no default serialise/deserialise method, no default ordering of values
//although can be added
sealed trait WeekdayTrait
case object Monday extends WeekdayTrait
case object Tuesday extends WeekdayTrait
case object Wednesday extends WeekdayTrait
case object Thursday extends WeekdayTrait
case object Friday extends WeekdayTrait
case object Saturday extends WeekdayTrait
case object Sunday extends WeekdayTrait

abstract class error(val status: String, val message: String, val order: Int){
  //adding the cons part manually
  def compare(that: Error) = this.order
}

