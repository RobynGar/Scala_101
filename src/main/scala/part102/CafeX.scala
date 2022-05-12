package part102

import java.awt.MenuItem
import scala.math.BigDecimal.RoundingMode

object CafeX extends App{
 trait MenuItem{
   val cost: BigDecimal
   val temp: Temperature
   val name: String
   val vegetarian: Boolean
   val vegan: Boolean
 }

  sealed trait Temperature
  case object Hot extends Temperature
  case object Cold extends Temperature


  case class Coffee() extends MenuItem {
    override val cost: BigDecimal = 1.0
    override val temp: Temperature = Hot
    override val name: String = "Coffee"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = false
  }
  case class Cola() extends MenuItem {
    override val cost: BigDecimal = 0.50
    override val temp: Temperature = Cold
    override val name: String = "Cola"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = true
  }
  case class CheeseSandwich() extends MenuItem {
    override val cost: BigDecimal = 2.0
    override val temp: Temperature = Cold
    override val name: String = "Cheese Sandwich"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = false
  }
  case class SteakSandwich() extends MenuItem {
    override val cost: BigDecimal = 2.0
    override val temp: Temperature = Cold
    override val name: String = "Steak Sandwich"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
  }

  def bill(order: List[MenuItem]): String = {
    def total(items: List[MenuItem]): BigDecimal = {
      items.map(x => x.cost).sum
    }
    val billTotal = total(order)
    def serviceCharge(whatToTip: BigDecimal) = {
      (whatToTip * 0.125).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }
    val tip = serviceCharge(billTotal)
    s"Your bill is £$billTotal the recommended tip it £$tip"
  }

  println(bill(List(Coffee(), CheeseSandwich())))
}
