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
  val age = 156
  if (age <= 4){
    println("you can not watch any films")
  } else if (age <= 7 && age >= 4){
    println("you can watch films rated U")
  } else if (age <= 11 && age >= 8){
    println("you can watch films rated U and PG")
  } else if (age <= 14 && age >= 12){
    println("you can watch films rated U, PG and 12A")
  } else if (age >= 15 && age <= 17){
    println("you can watch films rated U, PG, 12A and 15")
  } else if (age >= 18){
    println("you can watch any film you want")
  }

}
