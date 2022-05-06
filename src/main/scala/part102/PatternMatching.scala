package part102

object PatternMatching extends App {

  sealed trait Temperature

  case object Cold extends Temperature

  case object Hot extends Temperature

  case object Rain extends Temperature

  val weather: Temperature = Cold

  weather match {
    case Rain => "take a coat with a hood"
    case Cold => "wear a warm coat"
    case Hot => "wear something brezzey"
    case _ => "look out the window"
  }

  abstract class Notification

  case class Email(sender: String, title: String, body: String) extends Notification

  case class SMS(caller: String, message: String) extends Notification

  val notification: Notification = SMS("95344689", "Hello is it me your looking for?")

  //  notification match {
  //    case Email(email, title, _) => "Email from " +email+ "title"
  //    case SMS(number, message) => "SMS from " +number+ "! Message: "+message
  //  }
  notification match {
    case Email(email, title, _) if title.contains("Jira") => "Probably a pull request"
    //    the if statement is called a guard (guards) it works as a block
    case Email(email, title, _) => "Email from " + email + "title"
    case SMS(number, message) => "SMS from " + number + "! Message: " + message
  }

  val iceCreamFlavour: Flavor = Strawberry

  trait Flavor

  case object Chocolate extends Flavor

  case object Strawberry extends Flavor

  case object Caramel extends Flavor

  case object Cookie extends Flavor


  val iceCreamCreator = iceCreamFlavour match {
    case Strawberry => "strawberry shortcake"
    case Caramel => "caramel chew chew"
    case Chocolate => "chocolate fudge brownie"
    case Cookie => "cookie dough"
    case _ => iceCreamFlavour
  }

  println(iceCreamCreator)

  val myPizza: Int = 12

  val pizzaSize = myPizza match {
    case 7 => "Personal"
    case 9 => "Small"
    case 11 => "Medium"
    case 14 => "Large"
    case _ => "Medium"
  }

  println(pizzaSize)

  case class Pizza(size: Int, crust: String)

  val pizzaChoice = Pizza(11, "classic")

  val pizzaPrice = pizzaChoice match {
    case Pizza(size, crust) if size == 7 => 5.99
    case Pizza(size, crust) if size == 9 => 10.99
    case Pizza(size, crust) if size == 11 && crust.contains("stuffed") => 12.99 + 2.99
    case Pizza(size, crust) if size == 11 => 12.99
    case Pizza(size, crust) if size == 14 && crust.contains("stuffed") => 14.99 + 2.99
    case Pizza(size, crust) if size == 14 => 14.99
    case Pizza(_, _) => 12.99

  }

  println(pizzaPrice)

  def capitaliseCity(city: String): String = {
    city match {
      case "london" | "belfast" | "cardiff" | "edinburgh" => city.toUpperCase
      case _ => "Than is not a capital city of the UK"
    }
  }

  println(capitaliseCity("hillperton"))
  println(capitaliseCity("edinburgh"))

  abstract class Animal {
    val name: String
    val age: Int

  }

  case class Dog() extends Animal {
    override val name: String = "puppy"
    override val age: Int = 1
  }

  case class Cat() extends Animal {
    override val name: String = "Sam"
    override val age: Int = 11
  }

  val ani: Animal = Dog()
  val samAnimal: Animal = Cat()

  val animalChecker = ani match {
    case Dog() => "dog"
    case Cat() => "kitty"
    case _ => "other"
  }
  println(animalChecker)

  def nameCheck(animal: Animal): String = {
    animal match {
      case animal if animal.name == "Sam" => s"Sam's age is: ${animal.age}"
      case _ => s"${animal.name} is not Sam"
    }
  }

  println(nameCheck(ani))
  println(nameCheck(samAnimal))

  def olderThanTen(animal: Animal): String = {
    animal match {
      case animal if animal.age > 10 => s"${animal.name} is ${animal.age} years old"
      case _ => s"${animal.name} is young"
    }
  }

  println(olderThanTen(samAnimal))
  println(olderThanTen(ani))



}
