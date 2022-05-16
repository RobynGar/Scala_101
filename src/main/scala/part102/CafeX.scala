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


  val date: LocalDateTime = LocalDateTime.now()


  def bill(loyaltyCustomerName: Option[Customer], order: List[MenuItem]): String = {

    def whichServiceCharge(items: List[MenuItem], loyalService: Option[Customer]): BigDecimal = {
      if (items.exists(x => x.foodType == FoodBeverage.Food && x.premiumItem == true)){
        totalWithPremium(items)
      } else if (items.exists(x => x.temp == Hot && x.foodType == FoodBeverage.Food)) {
        totalWithHotFood(items, loyalService).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      } else if (items.exists(x => x.foodType == FoodBeverage.Food)){
        total(items, loyalService)
      } else totalOnlyDrinks(items, loyalService)
    }

    def total(items: List[MenuItem], loyalCustomer: Option[Customer]): BigDecimal = {
        loyalCustomer match {
          case Some(x) => (loyaltyScheme(x) + (loyaltyScheme(x) * 0.1)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
          case None if (date.getHour >= 18 && date.getHour <= 21) => ((items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2) * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
          case None => items.map(x => x.cost).sum + (items.map(x => x.cost).sum * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      }

    }
    def totalOnlyDrinks(items: List[MenuItem], loyalCustomer: Option[Customer]): BigDecimal = {
      loyalCustomer match {
        case Some(x) => loyaltyScheme(x).setScale(2, BigDecimal.RoundingMode.HALF_UP)
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
        case Some(x) if((loyaltyScheme(x) * 0.2) >= 20) => loyaltyScheme(x) + 20
        case Some(x) => loyaltyScheme(x) + (loyaltyScheme(x) * 0.2)
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

    def loyaltyScheme(customerName: Customer): BigDecimal = {
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

    val orderTotal = whichServiceCharge(order, loyaltyCustomerName)

    val billOutput = {
      if (order.exists(x => x.foodType == FoodBeverage.Food)) {

        s"Your bill including service charge is £$orderTotal, time of transaction ${date.getHour}"
      } else {
        s"Your bill is £$orderTotal, time of transaction ${date.getHour}"
      }
    }
// attempt at incrementing stars after purchase of over £20 but not sure if works
    if (orderTotal >= 20){
      loyaltyCustomerName.map(x => x.numOfStars + 1)
      billOutput
    } else {billOutput}


  }


//non-loyal customers
  println(bill(None, List(Coffee, CheeseSandwich)))//3.30 no hot food so service charge of 10%
  println(bill(None, List(Coffee, Coffee, Cola, Coffee)))//3.5 only drinks so no service charge should be applied
  println(bill(None, List(Coffee, SteakSandwich)))//6.60 contains hot food so should add 20% to bill for service charge
  println(bill(None, List(SteakSandwich, Coffee)))//6.60 this should be the same as above as the order the food and drinks are inputted should not make a difference
  println(bill(None, List(Steak, Steak, Steak, Steak, Steak))) //145.0 Made steak a non premium item so can test 20% without activating premium, expensive meal to activate £20 service charge limit 125 meal that is hot so should be a 20% service charge of £25 but the max will make this £20 so 125 + 20 = £145
  println(bill(None, List(Lobster, Lobster, Cola))) // 63.13 activate premium item 25% service charge
  println(bill(None, List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster))) //240.0, 200 bill with premium item at 25% will give 50 tip and activate the 40 limit so 200 + 40 output of 240
//loyal customers
  println("start of loyal" )
  println(bill(Some(Karen), List(Coffee, CheeseSandwich))) //3.05 loyal discount then 10% tip added
  println(bill(Some(Karen), List(Coffee, Coffee, Cola, Coffee)))//3.24 loyal discount no tip
  println(bill(Some(Karen), List(Coffee, SteakSandwich))) //6.10 loyal discount then 20% tip added
  println(bill(Some(Karen), List(SteakSandwich, Coffee))) //6.10
  println(bill(Some(Karen), List(Steak, Steak, Steak, Steak, Steak, Steak))) //158.75 loyal discount then activate premium item 25% service charge
  println(bill(Some(Karen), List(Lobster, Lobster, Cola))) //63.13 contains premium so no loyal
  println(bill(Some(Karen), List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster))) //240


  println("loyal customer with over 8 stars")
  println(bill(Some(Keith), List(Coffee, CheeseSandwich))) //2.64

}
