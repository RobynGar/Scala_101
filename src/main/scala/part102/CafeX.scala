package part102
import part102.menuObjectsAndTraits.{CheeseSandwich, Coffee, Cola, Customer, Employee, FoodBeverage, Lobster, MenuItems, Caviar, SteakSandwich, Temperature}
import java.time._


object CafeX extends App{
  //TODO all traits and objects should not live inside this object


  val karen = Customer(true, "Karen", 3)
  //TODO: Again I wouldn't really say a Karen is an object

  val keith = Customer(true, "Keith", 9)


  val alice= Employee("Alice", "Manger")
  //TODO: Defining an object Alice is good, but what if we wanted to enter loads of employees? Would a class be better suited that a trait/object?
  val bob= Employee("Bob", "Waiter")

  //TODO: Really happy with how these traits and objects are laid out, just in the wrong location


  val date: Int = LocalDateTime.now().getHour
  val dateMin: Int = LocalDateTime.now().getMinute

  //TODO: when naming methods, think about what it is doing rather than an object, e.g. this is calculating the bill, its not initializing a Bill Type


  def whichServiceCharge(items: List[MenuItems], loyalService: Option[Customer], date: Int): BigDecimal = {
    if (items.exists(x => x.foodType == FoodBeverage.Food && x.premiumItem)){
      totalPlusServiceChargeWithPremium(items, date)
    } else if (items.exists(x => x.temp == Temperature.Hot && x.foodType == FoodBeverage.Food)) {
      totalPlusServiceChargeWithHotFood(items, loyalService, date).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    } else if (items.exists(x => x.foodType == FoodBeverage.Food)){
      totalPlusServiceCharge(items, loyalService, date)
    } else
      totalPlusServiceChargeOnlyDrinks(items, loyalService, date)
  }


  def totalPlusServiceCharge(items: List[MenuItems], loyalCustomer: Option[Customer], date: Int): BigDecimal = {
    loyalCustomer match {
      case Some(x) => (applyLoyaltySchemeToOrder(x, items, date) + (applyLoyaltySchemeToOrder(x, items, date) * 0.1)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None if (date >= 18 && date <= 21) => ((items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2) * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None => items.map(x => x.cost).sum + (items.map(x => x.cost).sum * 0.1).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }

  }

  def totalPlusServiceChargeOnlyDrinks(items: List[MenuItems], loyalCustomer: Option[Customer], date: Int): BigDecimal = {
    loyalCustomer match {
      case Some(x) => applyLoyaltySchemeToOrder(x, items, date).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None if (date >= 18 && date <= 21) => items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      case None => items.map(x => x.cost).sum
    }
  }


  def totalPlusServiceChargeWithHotFood(items: List[MenuItems], loyalCustomer: Option[Customer], date: Int): BigDecimal = {

    def notLoyal(notLoyalItems: List[MenuItems]): BigDecimal = { //TODO: Having methods embedded inside others is not usually recommended, especially 3 layers deep!
      val happyHour = if (date >= 18 && date <= 21){
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
      case Some(x) if((applyLoyaltySchemeToOrder(x, items, date) * 0.2) >= 20) => applyLoyaltySchemeToOrder(x, items, date) + 20
      case Some(x) => applyLoyaltySchemeToOrder(x, items, date) + (applyLoyaltySchemeToOrder(x, items, date) * 0.2)
      case None => notLoyal(items)
    }
  }


  def totalPlusServiceChargeWithPremium(items: List[MenuItems], date: Int): BigDecimal = { //TODO: again, a lot of this code looks similar to above, could it be re-used?
    val hotFood = if (date >= 18 && date <= 21){
      val drinks = items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      (items.filter(food => food.foodType == FoodBeverage.Food).map(food => food.cost).sum) + drinks
    } else {
      items.map(x => x.cost).sum
    }
    val serviceCharge = (hotFood * 0.25).setScale(2, BigDecimal.RoundingMode.HALF_UP)

    if(serviceCharge >= 40.0) 40 + hotFood
    else hotFood + serviceCharge
  }

  def applyLoyaltySchemeToOrder(customerName: Customer, order: List[MenuItems], date: Int): BigDecimal = {
    customerName match { //TODO: rename to hasLoyaltyCard
      case customer if (customer.hasLoyaltyCard && customer.numOfStars >= 3 && customer.numOfStars <= 8) => discountTheOrder(customer.numOfStars * 0.025, order, date)
      case customer if (customer.hasLoyaltyCard && customer.numOfStars >= 8) => discountTheOrder(8 * 0.025, order, date)
      case _ => 1.0
    }
  }
  def discountTheOrder(discount: BigDecimal, order: List[MenuItems], date: Int): BigDecimal = { //TODO: why is dicountOrder the input?
    if (date >= 18 && date <= 21) {
      val drinks = order.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      val happyTotal =((order.filter(food => food.foodType == FoodBeverage.Food).map(food => food.cost).sum) + drinks)
      happyTotal - happyTotal * discount
    } else {
      order.map(x => x.cost).sum - order.map(x => x.cost).sum * discount
    }
  }

  def addStarIfBillOver20(loyaltyCustomerName: Option[Customer], orderTotal: BigDecimal): Int ={
    loyaltyCustomerName match {
    case Some(customer) if(orderTotal >= 20) =>
      customer.addStar(customer).numOfStars
      println(customer.addStar(customer).numOfStars)
      customer.numOfStars
    case Some(customer) =>
      println(s"Welcome back ${customer.name}, spend over £20 to collect stars, you currently have ${customer.numOfStars}")
      customer.numOfStars
    case None =>
      println("Join our loyalty scheme to get money off your next order")
        0
  }}

  def calculateBill(order: List[MenuItems], loyaltyCustomerName: Option[Customer], staffName: Employee, date: Int): BigDecimal = {
   println("-----------------------------------------------")
    val total = whichServiceCharge(order, loyaltyCustomerName, date)
    addStarIfBillOver20(loyaltyCustomerName, total)

    if (order.exists(x => x.foodType == FoodBeverage.Food)) { //TODO: I like the creative sentences, you're seeing how this could be used/applied. However, when we're testing we don't need these filler sentences, harder to match things
        println(s"Today you were served by ${staffName.name}(${staffName.positionTitle}).\n  Time of transaction ${date}:${dateMin}.\n    Your bill including service charge:")
      } else {
        println(s"Today you were served by ${staffName.name}(${staffName.positionTitle}).\n  Time of transaction ${date}:${dateMin}.\n    Your bill including service charge:")
      }


    total

  }





//non-loyal customers //TODO: Usually we go for test suites, I see you've made them before, it makes it easier to spot mistakes!
   println(calculateBill(List(Coffee, Coffee, Coffee, CheeseSandwich, SteakSandwich), None, alice, date))//6.60 contains hot food so should add 20% to bill for service charge


 //loyal customers
//  println("--------START OF LOYAL---------" )
println(calculateBill(List(Coffee, CheeseSandwich, SteakSandwich),Some(karen), bob, date)) //3.05 loyal discount then 10% tip added

}
