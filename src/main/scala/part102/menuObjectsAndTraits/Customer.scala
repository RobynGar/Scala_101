package part102.menuObjectsAndTraits

case class Customer(hasLoyaltyCard: Boolean, name: String, numOfStars: Int){
  def addStarWhenSpendingOver20(): Unit = {
    val updatedStars= numOfStars + 1
    println(s"Welcome back ${name.capitalize}, one star has been added to you loyalty card, you now have $updatedStars")
  }
}
