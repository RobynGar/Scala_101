package part102

object CaseClasses extends App{
  case class Car(make: String, model: String, year: Int)

  // instantiate the car case class
  val aprilsCar= Car("Mazda", "sport", 2018)
  val mumsCar= Car("Nissan", "qashqai", 2015)

  val oneCappuccino = Car("Suzuki", "Cappuccino", 1998)
  val makeThatTwo = Car("Suzuki", "Cappuccino", 1998)

  val bothTheSame = oneCappuccino == makeThatTwo
  println(bothTheSame)

  case class Aircraft(name: String, inService: Boolean, noBuild: Int)

  val current = Aircraft("A-10 Thunderbolt II", true, 716)
  val future = current.copy(inService = false)
  println(current)
  println(future)
  val revised = current.copy(name = "A-10 Thunderbolt II V2")
  println(revised)

  ////////////dog practical////////////

  case class Dog(name: String, breed: String, age: Int)

  val moose = Dog("Moose", "Cockapoo", 4)
  val maple = Dog("Maple", "Cockapoo", 4)
  val loki = Dog("Loki", "Mix", 2)
  val okey = Dog("Okey", "Samoyed", 12)

  case class Cat(name: String, loafness: String, plansForWorldDomination: Boolean)
  case class Bird(name: String, numberOfLanguages: Int, isPirate: Boolean)

  val horhay = Cat("Horhay", "unproven", true)
  val pineapple = Bird("Pineapple", 5, true)

  case class Kennel(name: String, dogs: List[Dog], cats: List[Cat], birds: List[Bird])

  val debsDogs = Kennel("Deb's Dogs", List(moose, maple, loki, okey), List(horhay), List(pineapple))

  println(debsDogs)

  val rebrand = debsDogs.copy(name = "Deb's Doggy Daycare")

  val rebrandExpand = debsDogs.copy(name = "Marvelous Menagerie")

  println(rebrand)





}
