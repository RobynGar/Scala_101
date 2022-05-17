package part102.menuObjectsAndTraits

object CheeseSandwich extends MenuItems {
  override val cost: BigDecimal = 2.0
  override val temp: Temperature.Value = Temperature.Cold
  override val name: String = "Cheese Sandwich"
  override val vegetarian: Boolean = true
  override val vegan: Boolean = false
  override val foodType: FoodBeverage.Value = FoodBeverage.Food
  override val premiumItem: Boolean = false
}
