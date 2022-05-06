package part102

object StringInterpolation extends App{

  //s interpolator
  val name = "Maple the Bear"
  val age = 4
  println(s"My name is $name and I am $age")

  //f interpolator
  val pi: Double = 3.141592653589793
  println(f"Pi to 2 d.p is $pi%.2f") // 2 d.p
  println(f"Pi to 2 d.p is $pi%.6f") //6 decimal places

  //raw interpolator
  println(raw"I'm going to go on a \nnew line")
//opposes to:
  println("I'm going to go on a \nnew line")


  ////////////exercise///////

  val older = "Kev"
  val younger = "Amy"
  println(s"$older is older than $younger")

  val olderAge: Double = 58.5700984
  val youngerAge: Double = 31.347898
  val ageDiff : Double = olderAge - youngerAge
  println(f"$older is ${olderAge - youngerAge} years older than $younger")
println(f"Kev is $ageDiff%.2f years older than Amy")
println(f"Kev is ${olderAge - youngerAge}%.2f years older than Amy")
println(f"$older is ${olderAge - youngerAge}%.2f years older than $younger")


  println(raw"\n\n\n\n\n\n\n\nsameline\n\n")

  case class Person(name: String, age: Double, numOfSiblings: Int, bloodType: String, countryOfResidence: String)
  val ken = Person("kenneth", 82.89764, 8, "A+", "England")

  println(f"${ken.name} is ${ken.age}%.1f")
  println(s"${ken.name} lives in ${ken.countryOfResidence}")
  println(raw"${ken.name} has a blood type ${ken.bloodType} and \n ${ken.numOfSiblings} siblings")

}
