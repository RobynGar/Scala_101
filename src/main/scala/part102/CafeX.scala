package part102
import java.time._
//import scala.math.BigDecimal.RoundingMode //this does not seem to be needed

object CafeX extends App{
  //TODO all traits and objects should not live inside this object
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

  object Steak extends MenuItem { //TODO: Yes, a steak is an object!
    override val cost: BigDecimal = 25.0
    override val temp: Temperature = Hot
    override val name: String = "Steak and chips"
    override val vegetarian: Boolean = false
    override val vegan: Boolean = false
    override val foodType: FoodBeverage.Value = FoodBeverage.Food
    override val premiumItem: Boolean = false
  }
  trait Customer{
    val hasLoyaltyCard: Boolean
    val name: String
    val numOfStars: Int
  }

  object Karen extends Customer { //TODO: Again I wouldn't really say a Karen is an object
    override val hasLoyaltyCard: Boolean = true
    override val name: String = "Karen"
    override val numOfStars: Int = 3
  }

  object Keith extends Customer {
    override val hasLoyaltyCard: Boolean = true
    override val name: String = "Keith"
    override val numOfStars: Int = 9
  }

  trait Employee{
    val name: String
    val positionTitle: String
  }
  object Alice extends Employee { //TODO: Defining an object Alice is good, but what if we wanted to enter loads of employees? Would a class be better suited that a trait/object?
    override val name: String = "Alice"
    override val positionTitle: String = "Manger"
  }
  object Bob extends Employee {
    override val name: String = "Bob"
    override val positionTitle: String = "Waiter"
  }
  //TODO: Really happy with how these traits and objects are laid out, just in the wrong location


  val date: LocalDateTime = LocalDateTime.now()

  //TODO: when naming methods, think about what it is doing rather than an object, e.g. this is calculating the bill, its not initializing a Bill Type


  def whichServiceCharge(items: List[MenuItem], loyalService: Option[Customer]): BigDecimal = {
    if (items.exists(x => x.foodType == FoodBeverage.Food && x.premiumItem == true)){
      totalWithPremium(items)
    } else if (items.exists(x => x.temp == Hot && x.foodType == FoodBeverage.Food)) {
      totalWithHotFood(items, loyalService).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    } else if (items.exists(x => x.foodType == FoodBeverage.Food)){
      total(items, loyalService, dicount = 0.1)
    } else
      total(items, loyalService, dicount = 0)
  }

  def total(items: List[MenuItem], loyalCustomer: Option[Customer], dicount: BigDecimal): BigDecimal = { //TODO: Assuming discount is a percentage
    loyalCustomer match {
      case Some(x) => (applyLoyaltySchemeToOrder(x, items) + (applyLoyaltySchemeToOrder(x, items) * (1 - dicount))).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None if (date.getHour >= 18 && date.getHour <= 21) => ((items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2) * (1 - dicount)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None => items.map(x => x.cost).sum + (items.map(x => x.cost).sum * (1 - dicount)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }

  }


  def totalWithHotFood(items: List[MenuItem], loyalCustomer: Option[Customer]): BigDecimal = {

    def notLoyal(notLoyalItems: List[MenuItem]): BigDecimal = { //TODO: Having methods embedded inside others is not usually recommended, especially 3 layers deep!
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
      case Some(x) if((applyLoyaltySchemeToOrder(x, items) * 0.2) >= 20) => applyLoyaltySchemeToOrder(x, items) + 20
      case Some(x) => applyLoyaltySchemeToOrder(x, items) + (applyLoyaltySchemeToOrder(x, items) * 0.2)
      case None => notLoyal(items)
    }
  }


  def totalWithPremium(items: List[MenuItem]): BigDecimal = { //TODO: again, a lot of this code looks similar to above, could it be re-used?
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

  def applyLoyaltySchemeToOrder(customerName: Customer, order: List[MenuItem]): BigDecimal = {
    customerName match {//TODO: rename to hasLoyaltyCard
      case customer if (customer.hasLoyaltyCard && customer.numOfStars >= 3 && customer.numOfStars <= 8) => discountTheOrder(customer.numOfStars * 0.025, order)
      case x if (x.hasLoyaltyCard && x.numOfStars >= 8) => discountTheOrder(8 * 0.025, order)
      case _ => 1.0
    }
  }
  def discountTheOrder(discount: BigDecimal, order: List[MenuItem]): BigDecimal = { //TODO: why is dicountOrder the input?
    if (date.getHour >= 18 && date.getHour <= 21) {
      val drinks = order.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      val happyTotal =((order.filter(food => food.foodType == FoodBeverage.Food).map(food => food.cost).sum) + drinks)
      happyTotal - happyTotal * discount
    } else {
      order.map(x => x.cost).sum - order.map(x => x.cost).sum * discount
    }
  }

  def calculateBill(staffName: Employee, loyaltyCustomerName: Option[Customer], order: List[MenuItem]): String = {

    val orderTotal = whichServiceCharge(order, loyaltyCustomerName)

   println("-----------------------------------------------")


    if (order.exists(x => x.foodType == FoodBeverage.Food)) { //TODO: I like the creative sentences, you're seeing how this could be used/applied. However, when we're testing we don't need these filler sentences, harder to match things
        s"Today you were served by ${staffName.name}(${staffName.positionTitle}). \n Your bill including service charge is £$orderTotal. \n  Time of transaction ${date.getHour.toString ++ date.getMinute.toString}"
      } else {
        s"Today you were served by ${staffName.name}(${staffName.positionTitle}). \n Your bill is £$orderTotal. \n  Time of transaction ${date.getHour}"
      }
  }






//non-loyal customers //TODO: Usually we go for test suites, I see you've made them before, it makes it easier to spot mistakes!
  println(calculateBill(Alice, None, List(Coffee, CheeseSandwich)))//3.30 no hot food so service charge of 10%
  println(calculateBill(Alice, None, List(Coffee, Coffee, Cola, Coffee)))//3.5 only drinks so no service charge should be applied
  println(calculateBill(Alice, None, List(Coffee, SteakSandwich)))//6.60 contains hot food so should add 20% to bill for service charge
  println(calculateBill(Alice, None, List(SteakSandwich, Coffee)))//6.60 this should be the same as above as the order the food and drinks are inputted should not make a difference
  println(calculateBill(Alice, None, List(Steak, Steak, Steak, Steak, Steak))) //145.0 Made steak a non premium item so can test 20% without activating premium, expensive meal to activate £20 service charge limit 125 meal that is hot so should be a 20% service charge of £25 but the max will make this £20 so 125 + 20 = £145
  println(calculateBill(Alice, None, List(Lobster, Lobster, Cola))) // 63.13 activate premium item 25% service charge
  println(calculateBill(Alice, None, List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster))) //240.0, 200 bill with premium item at 25% will give 50 tip and activate the 40 limit so 200 + 40 output of 240
//loyal customers
  println("--------START OF LOYAL---------" )
  println(calculateBill(Bob, Some(Karen), List(Coffee, CheeseSandwich))) //3.05 loyal discount then 10% tip added
  println(calculateBill(Bob, Some(Karen), List(Coffee, Coffee, Cola, Coffee)))//3.24 loyal discount no tip
  println(calculateBill(Bob, Some(Karen), List(Coffee, SteakSandwich))) //6.10 loyal discount then 20% tip added
  println(calculateBill(Bob, Some(Karen), List(SteakSandwich, Coffee))) //6.10
  println(calculateBill(Bob, Some(Karen), List(Steak, Steak, Steak, Steak, Steak, Steak))) //158.75 loyal discount then activate premium item 25% service charge
  println(calculateBill(Bob, Some(Karen), List(Lobster, Lobster, Cola))) //63.13 contains premium so no loyal
  println(calculateBill(Bob, Some(Karen), List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster))) //240


  println("--------loyal customer with over 8 stars---------")
  println(calculateBill(Bob, Some(Keith), List(Coffee, CheeseSandwich))) //2.64

}
