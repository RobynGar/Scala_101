package part102

//import scala.math.BigDecimal.RoundingMode //this does not seem to be needed

object CafeX extends App{
 trait MenuItem{
   val cost: BigDecimal
   val temp: Temperature
   val name: String
   val vegetarian: Boolean
   val vegan: Boolean
   val foodType: FoodBeverage.Value
   val premiumItem: Boolean
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
    override val premiumItem: Boolean = false
  }
  object Cola extends MenuItem {
    override val cost: BigDecimal = 0.50
    override val temp: Temperature = Cold
    override val name: String = "Cola"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = true
    override val foodType: FoodBeverage.Value = FoodBeverage.Beverage
    override val premiumItem: Boolean = false
  }
  object CheeseSandwich extends MenuItem {
    override val cost: BigDecimal = 2.0
    override val temp: Temperature = Cold
    override val name: String = "Cheese Sandwich"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
    override val premiumItem: Boolean = false
  }
  object SteakSandwich extends MenuItem {
    override val cost: BigDecimal = 2.0
    override val temp: Temperature = Hot
    override val name: String = "Steak Sandwich"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
    override val premiumItem: Boolean = false
  }
  object Lobster extends MenuItem {
    override val cost: BigDecimal = 25.0
    override val temp: Temperature = Hot
    override val name: String = "lobster"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
    override val premiumItem: Boolean = true
  }

  object Steak extends MenuItem {
    override val cost: BigDecimal = 25.0
    override val temp: Temperature = Hot
    override val name: String = "Steak and chips"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
    override val premiumItem: Boolean = false
  }

  def bill(order: List[MenuItem]): String = {

    def whichServiceCharge(items: List[MenuItem]): BigDecimal = {
      if (items.exists(x => x.foodType == FoodBeverage.Food && x.premiumItem == true)){
        totalWithPremium(items)
      } else if (items.exists(x => x.temp == Hot && x.foodType == FoodBeverage.Food)) {
        totalWithHotFood(items)
      } else if (items.exists(x => x.foodType == FoodBeverage.Food)){
        total(items)
      } else totalOnlyDrinks(items)
    }

    def total(items: List[MenuItem]): BigDecimal = {
      items.map(x => x.cost).sum + (items.map(x => x.cost).sum * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }
    def totalOnlyDrinks(items: List[MenuItem]): BigDecimal = {
      items.map(x => x.cost).sum
    }

    def totalWithHotFood(items: List[MenuItem]): BigDecimal = {
      val hotFood =items.map(x => x.cost).sum
      val serviceCharge = (items.map(x => x.cost).sum * 0.2).setScale(2, BigDecimal.RoundingMode.HALF_UP)

      if(serviceCharge >= 20.0) 20 + hotFood
      else hotFood + serviceCharge
    }

    def totalWithPremium(items: List[MenuItem]): BigDecimal = {
      val hotFood =items.map(x => x.cost).sum
      val serviceCharge = (items.map(x => x.cost).sum * 0.25).setScale(2, BigDecimal.RoundingMode.HALF_UP)

      if(serviceCharge >= 40.0) 40 + hotFood
      else hotFood + serviceCharge
    }
//
//    val orderTotal = order.map(items => items match {
//      case items if (items.temp == Hot && items.foodType == FoodBeverage.Food) => totalWithHotFood(order)
//      case items if items.foodType != FoodBeverage.Food => totalWithNoFood(order)
//      //case items if (order.exists(x => x.foodType == FoodBeverage.Food)) => total(order)
//      case _ => total(order)
//    } )
// with above needed to add .last as gives list of bigDouble as output
    val orderTotal = whichServiceCharge(order)
    if (order.exists(x => x.foodType == FoodBeverage.Food)){

      s"Your bill including service charge is £$orderTotal"
    } else {
      s"Your bill is £$orderTotal"
    }
  }

  println(bill(List(Coffee, CheeseSandwich)))//3.30 no hot food so service charge of 10%
  println(bill(List(Coffee, Coffee, Cola, Coffee)))//3.5 only drinks so no service charge should be applied
  println(bill(List(Coffee, SteakSandwich)))//3.60 contains hot food so should add 20% to bill for service charge
  println(bill(List(SteakSandwich, Coffee)))//3.60 this should be the same as above as the order the food and drinks are inputted should not make a difference
  println(bill(List(Steak, Steak, Steak, Steak, Steak))) //145.0 Made steak a non premium item so can test 20% without activating premium, expensive meal to activate £20 service charge limit 125 meal that is hot so should be a 20% service charge of £25 but the max will make this £20 so 125 + 20 = £145
  println(bill(List(Lobster, Lobster, Cola))) // 63.13 activate premium item 25% service charge
  println(bill(List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster))) //240.0, 200 bill with premium item at 25% will give 50 tip and activate the 40 limit so 200 + 40 output of 240

}
