package part102

object Options extends App{

  trait Chocolate{
    val colour: String
    val filling: Option[String]
  }

  case class Bounty() extends Chocolate {
    override val colour: String = "brown"
    override val filling: Option[String] = Some("coconut")
  }

  case class Caramel() extends Chocolate{
    override val colour: String = "brown"
    override val filling: Option[String] = Some("caramel")
  }
  case class DairyMilk(colour: String, filling: Option[String])

  val whiteDairy = DairyMilk("white", None)
  val darkDairy = DairyMilk("dark brown", None)
  val fruitAndNut = DairyMilk("brown", Some("fruit and nut"))

  def whatIsTheFilling(choc: DairyMilk)= {
    choc.filling.getOrElse("no filling")
  }
  println(whatIsTheFilling(whiteDairy))
  println(whatIsTheFilling(fruitAndNut))

  val bounty = Bounty()
  val caramel = Caramel()

  def whatIsInTheChocolate(chocolate: Chocolate): String = {
    chocolate.filling match {
      case Some("coconut") => "It is coconut!"
      case Some(other) => s"No coconut but there is $other"
      case None => "Just plain chocolate"
    }
  }
  println(whatIsInTheChocolate(bounty))
  println(whatIsInTheChocolate(caramel))


  case class Dog(name: String, spotsColours: Option[String], howlVolume: Option[Int])


  def colourSpots(dog: Dog)= {
    dog.spotsColours match {
      case Some(colour) => s"${dog.name} has $colour spots"
      case None => s"${dog.name} does not have spots"
    }
  }

  def spotsOrNot(dog: Dog) = {
    dog.spotsColours.getOrElse(s"${dog.name} does not have any spots.")
  }

  val spottyDog= Dog("Loki", Some("brown"), None)
  val notSpottyDog = Dog("Maple", None, Some(300))

  println(colourSpots(spottyDog))
  println(colourSpots(notSpottyDog))
  //////GET OR ELSE/////
  println(spotsOrNot(notSpottyDog))
  println(spotsOrNot(spottyDog))

  def isThereAnInteger(num: Option[Int]) = {
    num match {
      case Some(num) => num * 2
      case None => 0
    }
  }
 println(isThereAnInteger(spottyDog.howlVolume))
 println(isThereAnInteger(notSpottyDog.howlVolume))


}
