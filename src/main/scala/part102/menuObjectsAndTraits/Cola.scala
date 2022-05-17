package part102.menuObjectsAndTraits

object Cola extends MenuItems {

    override val cost: BigDecimal = 0.50
    override val temp: Temperature.Value = Temperature.Cold
    override val name: String = "Cola"
    override val vegetarian: Boolean = true
    override val vegan: Boolean = true
    override val foodType: FoodBeverage.Value = FoodBeverage.Beverage
    override val premiumItem: Boolean = false


}
