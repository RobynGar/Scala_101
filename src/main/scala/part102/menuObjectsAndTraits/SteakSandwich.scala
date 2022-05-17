package part102.menuObjectsAndTraits

object SteakSandwich extends MenuItems {
  override val cost: BigDecimal = 4.5
  override val temp: Temperature.Value = Temperature.Hot
  override val name: String = "Steak Sandwich"
  override val vegetarian: Boolean = false
  override val vegan: Boolean = false
  override val foodType: FoodBeverage.Value = FoodBeverage.Food
  override val premiumItem: Boolean = false
}
