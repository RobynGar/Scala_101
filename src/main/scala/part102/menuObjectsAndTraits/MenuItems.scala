package part102.menuObjectsAndTraits

trait MenuItems {

    val cost: BigDecimal
    val temp: Temperature.Value
    val name: String
    val vegetarian: Boolean
    val vegan: Boolean
    val foodType: FoodBeverage.Value
    val premiumItem: Boolean

}
