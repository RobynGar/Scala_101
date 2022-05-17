package part102
import part102.menuObjectsAndTraits.{CheeseSandwich, Coffee, Cola, Customer, Employee, FoodBeverage, Lobster, MenuItems, Steak, SteakSandwich, Temperature}
import java.time._


object CafeX extends App{
  //TODO all traits and objects should not live inside this object


  val karen = Customer(true, "Karen", 3)
  //TODO: Again I wouldn't really say a Karen is an object

  val keith = Customer(true, "Keith", 9)


  val alice= new Employee("Alice", "Manger")
  //TODO: Defining an object Alice is good, but what if we wanted to enter loads of employees? Would a class be better suited that a trait/object?
  val bob= new Employee("Bob", "Waiter")

  //TODO: Really happy with how these traits and objects are laid out, just in the wrong location


  val date: LocalDateTime = LocalDateTime.now()

  //TODO: when naming methods, think about what it is doing rather than an object, e.g. this is calculating the bill, its not initializing a Bill Type


  def whichServiceCharge(items: List[MenuItems], loyalService: Option[Customer]): BigDecimal = {
    if (items.exists(x => x.foodType == FoodBeverage.Food && x.premiumItem)){
      totalPlusServiceChargeWithPremium(items)
    } else if (items.exists(x => x.temp == Temperature.Hot && x.foodType == FoodBeverage.Food)) {
      totalPlusServiceChargeWithHotFood(items, loyalService).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    } else if (items.exists(x => x.foodType == FoodBeverage.Food)){
      totalPlusServiceCharge(items, loyalService, discount = 0.1)
    } else
      totalPlusServiceCharge(items, loyalService, discount = 0)
  }

  def totalPlusServiceCharge(items: List[MenuItems], loyalCustomer: Option[Customer], discount: BigDecimal): BigDecimal = { //TODO: Assuming discount is a percentage
    loyalCustomer match {
      case Some(x) => (applyLoyaltySchemeToOrder(x, items) + (applyLoyaltySchemeToOrder(x, items) * (1 - discount))).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None if (date.getHour >= 18 && date.getHour <= 21) => ((items.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2) * (1 - discount)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
      case None => items.map(x => x.cost).sum + (items.map(x => x.cost).sum * (1 - discount)).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }

  }


  def totalPlusServiceChargeWithHotFood(items: List[MenuItems], loyalCustomer: Option[Customer]): BigDecimal = {

    def notLoyal(notLoyalItems: List[MenuItems]): BigDecimal = { //TODO: Having methods embedded inside others is not usually recommended, especially 3 layers deep!
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


  def totalPlusServiceChargeWithPremium(items: List[MenuItems]): BigDecimal = { //TODO: again, a lot of this code looks similar to above, could it be re-used?
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

  def applyLoyaltySchemeToOrder(customerName: Customer, order: List[MenuItems]): BigDecimal = {
    customerName match {//TODO: rename to hasLoyaltyCard
      case customer if (customer.hasLoyaltyCard && customer.numOfStars >= 3 && customer.numOfStars <= 8) => discountTheOrder(customer.numOfStars * 0.025, order)
      case x if (x.hasLoyaltyCard && x.numOfStars >= 8) => discountTheOrder(8 * 0.025, order)
      case _ => 1.0
    }
  }
  def discountTheOrder(discount: BigDecimal, order: List[MenuItems]): BigDecimal = { //TODO: why is dicountOrder the input?
    if (date.getHour >= 18 && date.getHour <= 21) {
      val drinks = order.filter(x => x.foodType == FoodBeverage.Beverage).map(drinks => drinks.cost).sum / 2
      val happyTotal =((order.filter(food => food.foodType == FoodBeverage.Food).map(food => food.cost).sum) + drinks)
      happyTotal - happyTotal * discount
    } else {
      order.map(x => x.cost).sum - order.map(x => x.cost).sum * discount
    }
  }

  def addStarIfBillOver20(loyaltyCustomerName: Option[Customer], orderTotal: BigDecimal) ={
    loyaltyCustomerName match {
    case Some(customer) if(orderTotal >= 20) => customer.addStarWhenSpendingOver20()
    case Some(customer) => println(s"Welcome back ${customer.name}, spend over £20 to collect stars, you currently have ${customer.numOfStars}")
    case None => println("Join our loyalty scheme to get money off your next order")
  }}

  def calculateBill(order: List[MenuItems], loyaltyCustomerName: Option[Customer], staffName: Employee): BigDecimal = {
    println("-----------------------------------------------")
    val total = whichServiceCharge(order, loyaltyCustomerName)
    addStarIfBillOver20(loyaltyCustomerName, total)

    if (order.exists(x => x.foodType == FoodBeverage.Food)) { //TODO: I like the creative sentences, you're seeing how this could be used/applied. However, when we're testing we don't need these filler sentences, harder to match things
        println(s"Today you were served by ${staffName.name}(${staffName.positionTitle}).\n  Time of transaction ${date.getHour}:${date.getMinute}.\n    Your bill including service charge:")
      } else {
        println(s"Today you were served by ${staffName.name}(${staffName.positionTitle}).\n  Time of transaction ${date.getHour}:${date.getMinute}.\n    Your bill including service charge:")
      }


    total
  }





//non-loyal customers //TODO: Usually we go for test suites, I see you've made them before, it makes it easier to spot mistakes!
  //non-loyal customers
  println(calculateBill(List(Coffee, CheeseSandwich),None, alice))//3.30 no hot food so service charge of 10%
  println(calculateBill(List(Coffee, Coffee, Cola, Coffee), None, alice))//3.5 only drinks so no service charge should be applied
  println(calculateBill(List(Coffee, SteakSandwich), None, alice))//6.60 contains hot food so should add 20% to bill for service charge
  println(calculateBill(List(SteakSandwich, Coffee), None, alice))//6.60 this should be the same as above as the order the food and drinks are inputted should not make a difference
  println(calculateBill(List(Steak, Steak, Steak, Steak, Steak), None, alice)) //145.0 Made steak a non premium item so can test 20% without activating premium, expensive meal to activate £20 service charge limit 125 meal that is hot so should be a 20% service charge of £25 but the max will make this £20 so 125 + 20 = £145
  println(calculateBill(List(Lobster, Lobster, Cola), None, alice)) // 63.13 activate premium item 25% service charge
  println(calculateBill(List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster), None, alice)) //240.0, 200 bill with premium item at 25% will give 50 tip and activate the 40 limit so 200 + 40 output of 240
  //loyal customers
  println("--------START OF LOYAL---------" )
  println(calculateBill(List(Coffee, CheeseSandwich),Some(karen), bob)) //3.05 loyal discount then 10% tip added
  println(calculateBill(List(Coffee, Coffee, Cola, Coffee), Some(karen), bob))//3.24 loyal discount no tip
  println(calculateBill(List(Coffee, SteakSandwich), Some(karen), bob)) //6.10 loyal discount then 20% tip added
  println(calculateBill(List(SteakSandwich, Coffee), Some(karen), bob)) //6.10
  println(calculateBill(List(Steak, Steak, Steak, Steak, Steak, Steak), Some(karen), bob)) //158.75 loyal discount then activate premium item 25% service charge
  println(calculateBill(List(Lobster, Lobster, Cola), Some(karen), bob)) //63.13 contains premium so no loyal
  println(calculateBill(List(Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster, Lobster), Some(karen), bob)) //240


  println("--------loyal customer with over 8 stars---------")
  println(calculateBill(List(Coffee, CheeseSandwich), Some(keith), bob)) //2.64

}
