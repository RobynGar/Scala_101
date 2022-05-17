package part102.menuObjectsAndTraits

case class Customer(hasLoyaltyCard: Boolean, name: String, val numOfStars: Int){
//  def addStarWhenSpendingOver20(): Unit = {
//    numOfStars += 1
//    println(s"Welcome back ${name.capitalize}, one star has been added to you loyalty card, you now have $numOfStars")
//  }
  def addStar(customer: Customer): Customer = customer.copy(numOfStars = numOfStars + 1)
}
