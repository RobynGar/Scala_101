package part102

object CafeX2 extends App{
  sealed trait Temperature
  case object Hot extends Temperature
  case object Cold extends Temperature

  sealed trait FoodType
  case object Food extends FoodType
  case object Drink extends FoodType

  case class MenuItems(cost: BigDecimal, temp: Temperature, foodType: FoodType, premiumItem: Boolean)

  val coffee = MenuItems(1.0, Hot, Drink, false)
  val cola = MenuItems(0.5, Cold, Drink, false)
  val cheeseSandwich = MenuItems(2.0, Cold, Food, false)
  val steakSandwich = MenuItems(4.5, Hot, Food, false)
  val expensiveDinner = MenuItems(100.0, Hot, Food, false)
  val lobster = MenuItems(50.0, Hot, Food, true)


  def billWithoutTip(order: List[MenuItems]): BigDecimal = {
    order.map(items => items.cost).sum
  }

  def serviceCharge(order: List[MenuItems]): String = {
    ???
  }


}
