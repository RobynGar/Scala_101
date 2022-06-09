package part102.menuObjectsAndTraits

object Caviar extends MenuItems {
  override val cost: BigDecimal = 100.0
  override val temp: Temperature.Value = Temperature.Hot
  override val name: String = "Caviar"
  override val vegetarian: Boolean = false
  override val vegan: Boolean = false
  override val foodType: FoodBeverage.Value = FoodBeverage.Food
  override val premiumItem: Boolean = false
}
