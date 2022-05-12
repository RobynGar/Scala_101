package part102


object EnumerationExercise extends App{
//ex1
  //println(Animal.values)
//ex2
  def allAnimals(): List[AnimalType] = {
    List(Mammals, Fish, Birds, Amphibians, Reptiles, Invertebrates)
  }
  println(allAnimals())
//ex3
  val bear = Animal(Mammals, Carnivore, Forest, CannotFly, SwimNoFins)
  println(bear)
}
//ex1
//object Animal extends Enumeration {
//  val Mammals, Birds, Fish, Amphibians, Reptiles, Invertebrates = Value
//}
//ex2
sealed trait AnimalType
case object Mammals extends AnimalType
case object Birds extends AnimalType
case object Fish extends AnimalType
case object Amphibians extends AnimalType
case object Reptiles extends AnimalType
case object Invertebrates extends AnimalType
//ex3
//object CanFlyEnum extends Enumeration{
//  val Yes, No, SortOf = Value
//}
sealed trait CanFlyEnum
case object Wings extends CanFlyEnum
case object CannotFly extends CanFlyEnum
case object Flightless extends CanFlyEnum
//object CanSwimEnum extends Enumeration{
//  val Yes, No, SortOf = Value
//}
sealed trait CanSwimEnum
case object Fins extends CanSwimEnum
case object CannotSwim extends CanSwimEnum
case object SwimNoFins extends CanSwimEnum


sealed trait Habitat
  case object Marine extends Habitat
  case object Freshwater extends Habitat
  case object Desert extends Habitat
  case object Forest extends Habitat
  case object Grasslands extends Habitat
  case object CoralReef extends Habitat
  case object Tundra extends Habitat
  case object Polar extends Habitat
  case object Evergreen extends Habitat
  case object TropicalRainforest extends Habitat


  sealed trait Diet
  case object Herbivore extends Diet
  case object Carnivore extends Diet
  case object Cannibal extends Diet


case class Animal(animalTypes: AnimalType, diet: Diet, habitat: Habitat, fly: CanFlyEnum, swim: CanSwimEnum)
