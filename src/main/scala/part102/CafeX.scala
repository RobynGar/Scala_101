package part102

import part102.CafeX.FoodBeverage

import java.awt.MenuItem
import scala.math.BigDecimal.RoundingMode

object CafeX extends App{
 trait MenuItem{
   val cost: BigDecimal
   val temp: Temperature
   val name: String
   val vegetarian: Boolean
   val vegan: Boolean
   val foodType: FoodBeverage.Value
 }

  sealed trait Temperature
  case object Hot extends Temperature
  case object Cold extends Temperature

  object FoodBeverage extends Enumeration{
    val Food, Beverage = Value
  }

  object Coffee extends MenuItem {
    override val cost: BigDecimal = 1.0
    override val temp: Temperature = Hot
    override val name: String = "Coffee"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Beverage
  }
  object Cola extends MenuItem {
    override val cost: BigDecimal = 0.50
    override val temp: Temperature = Cold
    override val name: String = "Cola"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = true
    override val foodType: FoodBeverage.Value = FoodBeverage.Beverage
  }
  object CheeseSandwich extends MenuItem {
    override val cost: BigDecimal = 2.0
    override val temp: Temperature = Cold
    override val name: String = "Cheese Sandwich"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
  }
  object SteakSandwich extends MenuItem {
    override val cost: BigDecimal = 2.0
    override val temp: Temperature = Cold
    override val name: String = "Steak Sandwich"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
  }

  def bill(order: List[MenuItem]): String = {
    def total(items: List[MenuItem]): BigDecimal = {
      if (items.exists(x => x.foodType == FoodBeverage.Food)){
        (items.map(x => x.cost).sum) + ((items.map(x => x.cost).sum) * 0.125).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      } else  items.map(x => x.cost).sum
    }
    val billTotal = total(order)
//    def serviceCharge(whatToTip: BigDecimal) = {
//      (whatToTip * 0.125).setScale(2, BigDecimal.RoundingMode.HALF_UP)
//    }
//    val tip = serviceCharge(billTotal)
    if (order.exists(x => x.foodType == FoodBeverage.Food)){
    s"Your bill including service charge is £$billTotal"
    } else {
      s"Your bill is £$billTotal"
    }
  }

  println(bill(List(Coffee, CheeseSandwich)))
  println(bill(List(Coffee, Coffee, Cola, Coffee)))
}
