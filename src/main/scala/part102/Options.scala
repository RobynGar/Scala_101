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
  val fruiteAndNut = DairyMilk("brown", Some("fruit and nut"))

  def whatIsTheFilling(choc: DairyMilk)= {
    choc match {
      case choc => choc.filling.getOrElse("no filling")
      case _ => "not a chocolate bar in stock"
    }
  }
  println(whatIsTheFilling(whiteDairy))
  println(whatIsTheFilling(fruiteAndNut))

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

}
