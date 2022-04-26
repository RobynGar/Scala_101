object Conditionals extends App {
  val grade = 80
  if(grade == 90){
    println("Received a A")
  } else if(grade == 80){
    println("Received a B")
  } else if(grade == 70){
    println("Received a C")
  } else if(grade == 60){
    println("Received a D")
  } else if(grade == 50){
    println("Received a E")
  } else {
    println("Received a F")
  }

  ///////////films exercise/////////////
  val age = 17
  val fourPlusFilms: List[String] = List("U")
  val eightPlusFilms: List[String] = List("U", "PG")
  val twelvePlusFilms: List[String] = List("U", "PG", "12A")
  val fifteenthPlusFilms: List[String] = List("U", "PG", "12A", "15")

  if (age <= 4){
    println("You can not watch any films")
  } else if (age <= 7 && age >= 4){
    println(s"You can watch films with the rating $fourPlusFilms")
  } else if (age <= 11 && age >= 8){
    println(s"You can watch films rated ${eightPlusFilms.mkString(", ")}")
    //.mkString(", ") on the end of a list separates the items in the list with whatever is in the comers
  } else if (age <= 14 && age >= 12){
    println(s"You can watch films rated ${twelvePlusFilms.mkString(", ")}")
  } else if (age >= 15 && age <= 17){
    println(s"You can watch films rated ${fifteenthPlusFilms.mkString(", ")}")
  } else if (age >= 18){
    println("You can watch any film you want")
  }


}
