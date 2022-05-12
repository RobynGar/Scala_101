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
    override val temp: Temperature = Hot
    override val name: String = "Steak Sandwich"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
  }
  object LobsterAndCaviar extends MenuItem {
    override val cost: BigDecimal = 80.0
    override val temp: Temperature = Hot
    override val name: String = "lobster and caviar"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
  }

  def bill(order: List[MenuItem]): String = {

    def totalWithNoFood(items: List[MenuItem]): BigDecimal = {
//      if (items.exists(x => x.foodType == FoodBeverage.Food)){
//        (items.map(x => x.cost).sum) + ((items.map(x => x.cost).sum) * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
//      } else items.map(x => x.cost).sum
      items.map(x => x.cost).sum
    }

    def total(items: List[MenuItem]): BigDecimal = {
      (items.map(x => x.cost).sum) + ((items.map(x => x.cost).sum) * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }

    def totalWithHotFood(items: List[MenuItem]): BigDecimal = {
      val hotFood =(items.map(x => x.cost).sum)
      val serviceCharge = ((items.map(x => x.cost).sum) * 0.2).setScale(2, BigDecimal.RoundingMode.HALF_UP)

      if(serviceCharge >= 20.0) 20 + hotFood
      else hotFood + serviceCharge
    }

    val orderTotal = order.map(items => items match {
      case items if (items.temp == Hot && items.foodType == FoodBeverage.Food) => totalWithHotFood(order)
      case items if items.foodType != FoodBeverage.Food => totalWithNoFood(order)
      //case items if (order.exists(x => x.foodType == FoodBeverage.Food)) => total(order)
      case _ => total(order)
    } )

//    def serviceCharge(whatToTip: BigDecimal) = {
//      (whatToTip * 0.125).setScale(2, BigDecimal.RoundingMode.HALF_UP)
//    }
//    val tip = serviceCharge(billTotal)
    if (order.exists(x => x.foodType == FoodBeverage.Food)){

      s"Your bill including service charge is £${orderTotal.last}"
    } else {
      s"Your bill is £${orderTotal.last}"
    }
  }

  println(bill(List(Coffee, CheeseSandwich)))//no hot food so service charge of 10%
  println(bill(List(Coffee, Coffee, Cola, Coffee)))//only drinks so no service charge should be applied
  println(bill(List(Coffee, SteakSandwich)))//contains hot food so should add 20% to bill for service charge
  println(bill(List(LobsterAndCaviar, LobsterAndCaviar))) //expensive meal to activate £20 service charge limit 160 meal that is hot so should be a 20% service charge of £32 but the max will make this £20 so 160 + 20 = £180
}
