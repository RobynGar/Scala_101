package part102.menuObjectsAndTraits

object Steak extends MenuItems {
  override val cost: BigDecimal = 25.0
  override val temp: Temperature.Value = Temperature.Hot
  override val name: String = "Steak and chips"
  override val vegetarian: Boolean = false
  override val vegan: Boolean = false
  override val foodType: FoodBeverage.Value = FoodBeverage.Food
  override val premiumItem: Boolean = false
}
