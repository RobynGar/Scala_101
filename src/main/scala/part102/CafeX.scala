package part102
import java.time._
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
    override val cost: BigDecimal = 4.5
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
  trait Customer{
    val loyaltyCard: Boolean
    val name: String
    val numOfStars: Int
  }

  object Karen extends Customer {
    override val loyaltyCard: Boolean = true
    override val name: String = "Karen"
    override val numOfStars: Int = 3
  }

  object Keith extends Customer {
    override val loyaltyCard: Boolean = true
    override val name: String = "Keith"
    override val numOfStars: Int = 9
  }

  trait Employee{
    val name: String
    val positionTitle: String
  }
  object Alice extends Employee {
    override val name: String = "Alice"
    override val positionTitle: String = "Manger"
  }
  object Bob extends Employee {
    override val name: String = "Bob"
    override val positionTitle: String = "Waiter"
  }


  val date: LocalDateTime = LocalDateTime.now()

  def whichServiceCharge(items: List[MenuItem], loyalService: Option[Customer]): BigDecimal = {
    if (items.exists(x => x.foodType == FoodBeverage.Food && x.premiumItem)){
      totalWithPremium(items)
    } else if (items.exists(x => x.temp == Hot && x.foodType == FoodBeverage.Food)) {
      totalWithHotFood(items, loyalService).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    } else if (items.exists(x => x.foodType == FoodBeverage.Food)){
      total(items, loyalService)
    } else totalOnlyDrinks(items, loyalService)
  }

  def total(items: List[MenuItem], loyalCustomer: Option[Customer]): BigDecimal = {
    loyalCustomer match {
      case Some(x) => (loyaltyScheme(x, items) + (loyaltyScheme(x, items) * 0.1)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None if (date.getHour >= 18 && date.getHour <= 21) => ((items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2) * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None => items.map(x => x.cost).sum + (items.map(x => x.cost).sum * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }

  }
  def totalOnlyDrinks(items: List[MenuItem], loyalCustomer: Option[Customer]): BigDecimal = {
    loyalCustomer match {
      case Some(x) => loyaltyScheme(x, items).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None if (date.getHour >= 18 && date.getHour <= 21) => items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      case None => items.map(x => x.cost).sum
    }

  }

  def totalWithHotFood(items: List[MenuItem], loyalCustomer: Option[Customer]): BigDecimal = {

    def notLoyal(notLoyalItems: List[MenuItem]): BigDecimal = {
      val happyHour = if (date.getHour >= 18 && date.getHour <= 21){
        val drinks = notLoyalItems.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
        (notLoyalItems.filter(x => x.foodType == FoodBeverage.Food).map(x => x.cost).sum) + drinks
      }else {
        notLoyalItems.map(x => x.cost).sum
      }
      val serviceCharge = (happyHour * 0.2).setScale(2, BigDecimal.RoundingMode.HALF_UP)

      if (serviceCharge >= 20.0) 20 + happyHour
      else happyHour + serviceCharge
    }


    loyalCustomer match {
      case Some(x) if((loyaltyScheme(x, items) * 0.2) >= 20) => loyaltyScheme(x, items) + 20
      case Some(x) => loyaltyScheme(x, items) + (loyaltyScheme(x, items) * 0.2)
      case None => notLoyal(items)
    }
  }


  def totalWithPremium(items: List[MenuItem]): BigDecimal = {
    val hotFood = if (date.getHour >= 18 && date.getHour <= 21){
      val drinks = items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      (items.filter(food => food.foodType == FoodBeverage.Food).map(food => food.cost).sum) + drinks
    } else {
      items.map(x => x.cost).sum
    }
    val serviceCharge = (hotFood * 0.25).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    if(serviceCharge >= 40.0) 40 + hotFood
    else hotFood + serviceCharge
  }

  def loyaltyScheme(customerName: Customer, order: List[MenuItem]): BigDecimal = {
    customerName match {
      case x if (x.loyaltyCard && x.numOfStars >= 3 && x.numOfStars <= 8) => discountedTotal(x.numOfStars * 0.025, order)
      case x if (x.loyaltyCard && x.numOfStars >= 8) => discountedTotal(8 * 0.025, order)
      case _ => 1.0
    }
  }
  def discountedTotal(discount: BigDecimal, discountOrder: List[MenuItem]): BigDecimal = {
    if (date.getHour >= 18 && date.getHour <= 21) {
      val drinks = discountOrder.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      val happyTotal =((discountOrder.filter(food => food.foodType == FoodBeverage.Food).map(food => food.cost).sum) + drinks)
      happyTotal - happyTotal * discount
    } else {
      discountOrder.map(x => x.cost).sum - discountOrder.map(x => x.cost).sum * discount
    }
  }

// the most complicated parameter comes first in parameter
  def bill(order: List[MenuItem], loyaltyCustomerName: Option[Customer], staffName: Employee): String = {

    val orderTotal = whichServiceCharge(order, loyaltyCustomerName)

   println("-----------------------------------------------")


    if (order.exists(x => x.foodType == FoodBeverage.Food)) {
        s"Today you were served by ${staffName.name}(${staffName.positionTitle}). \n Your bill including service charge is £$orderTotal. \n  Time of transaction ${date.getHour}"
      } else {
        s"Today you were served by ${staffName.name}(${staffName.positionTitle}). \n Your bill is £$orderTotal. \n  Time of transaction ${date.getHour}"
      }
    }

//non-loyal customers
  println(bill(List(Coffee, CheeseSandwich),None, Alice))//3.30 no hot food so service charge of 10%
  println(bill(List(Coffee, Coffee, Cola, Coffee), None, Alice))//3.5 only drinks so no service charge should be applied
  println(bill(List(Coffee, SteakSandwich), None, Alice))//6.60 contains hot food so should add 20% to bill for service charge
  println(bill(List(SteakSandwich, Coffee), None, Alice))//6.60 this should be the same as above as the order the food and drinks are inputted should not make a difference
  println(bill(List(Steak, Steak, Steak, Steak, Steak), None, Alice)) //145.0 Made steak a non premium item so can test 20% without activating premium, expensive meal to activate £20 service charge limit 125 meal that is hot so should be a 20% service charge of £25 but the max will make this £20 so 125 + 20 = £145
  println(bill(List(Lobster, Lobster, Cola), None, Alice)) // 63.13 activate premium item 25% service charge
  println(bill(List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster), None, Alice)) //240.0, 200 bill with premium item at 25% will give 50 tip and activate the 40 limit so 200 + 40 output of 240
//loyal customers
  println("--------START OF LOYAL---------" )
  println(bill(List(Coffee, CheeseSandwich),Some(Karen), Bob)) //3.05 loyal discount then 10% tip added
  println(bill(List(Coffee, Coffee, Cola, Coffee), Some(Karen), Bob))//3.24 loyal discount no tip
  println(bill(List(Coffee, SteakSandwich), Some(Karen), Bob)) //6.10 loyal discount then 20% tip added
  println(bill(List(SteakSandwich, Coffee), Some(Karen), Bob)) //6.10
  println(bill(List(Steak, Steak, Steak, Steak, Steak, Steak), Some(Karen), Bob)) //158.75 loyal discount then activate premium item 25% service charge
  println(bill(List(Lobster, Lobster, Cola), Some(Karen), Bob)) //63.13 contains premium so no loyal
  println(bill(List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster), Some(Karen), Bob)) //240


  println("--------loyal customer with over 8 stars---------")
  println(bill(List(Coffee, CheeseSandwich), Some(Keith), Bob)) //2.64

}
