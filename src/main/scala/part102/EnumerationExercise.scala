package part102


object EnumerationExercise extends App{
//ex1
  //println(AnimalType.values)
//ex2
//  def allAnimals(): List[AnimalType] = {
//    List(Mammals, Fish, Birds, Amphibians, Reptiles, Invertebrates)
//  }
 // println(allAnimals())
//ex3
  //animal with sealed trait and case class
//  val bear = Animal(Mammals, Carnivore, Forest, CannotFly, SwimNoFins)
//  println(bear)
  //animal with object extending enumeration and case class
  val pig = Animal(AnimalType.Mammals, Diet.Carnivore, Habitat.Grasslands, CanFlyEnum.Wishes, CanSwimEnum.KindOf)
    pig match {
    case AnimalType.Mammals => println("I am a Mammal")
    case Diet.Carnivore => println("I eat anything, literally")
    case Habitat.Grasslands => println("I live in grasslands")
    case CanFlyEnum.Wishes => println("I wish I could fly")
    case CanSwimEnum.KindOf => println("I like to wallow in water")
    case _ => println("I am a pig")
    }


  println(pig)
  






}
//ex1
object AnimalType extends Enumeration {
  val Mammals, Birds, Fish, Amphibians, Reptiles, Invertebrates = Value
}
//ex2
//sealed trait AnimalType
//case object Mammals extends AnimalType
//case object Birds extends AnimalType
//case object Fish extends AnimalType
//case object Amphibians extends AnimalType
//case object Reptiles extends AnimalType
//case object Invertebrates extends AnimalType
//ex3
object CanFlyEnum extends Enumeration{
  val Wings, CannotFly, FlightlessWings, Wishes = Value
}
//sealed trait CanFlyEnum
//case object Wings extends CanFlyEnum
//case object CannotFly extends CanFlyEnum
//case object FlightlessWings extends CanFlyEnum
//case object Wishes extends CanFlyEnum
object CanSwimEnum extends Enumeration{
  val Fins, CannotSwim, SwimNoFins, KindOf = Value
}
//sealed trait CanSwimEnum
//case object Fins extends CanSwimEnum
//case object CannotSwim extends CanSwimEnum
//case object SwimNoFins extends CanSwimEnum
//case object KindOf extends CanSwimEnum
object Habitat extends Enumeration{
  val  Marine, Freshwater, Desert, Forest, Grasslands, CoralReef, Tundra, Evergreen, Polar, TropicalRainforest= Value
}
//sealed trait Habitat
//  case object Marine extends Habitat
//  case object Freshwater extends Habitat
//  case object Desert extends Habitat
//  case object Forest extends Habitat
//  case object Grasslands extends Habitat
//  case object CoralReef extends Habitat
//  case object Tundra extends Habitat
//  case object Polar extends Habitat
//  case object Evergreen extends Habitat
//  case object TropicalRainforest extends Habitat

object Diet extends Enumeration{
  val Herbivore, Carnivore, Cannibal = Value
}
//  sealed trait Diet
//  case object Herbivore extends Diet
//  case object Carnivore extends Diet
//  case object Cannibal extends Diet

//sealed trait enum case class
//case class Animal(animalTypes: AnimalType, diet: Diet, habitat: Habitat, fly: CanFlyEnum, swim: CanSwimEnum)
// object extending enumeration case class
case class Animal(animalTypes: AnimalType.Value, diet: Diet.Value, habitat: Habitat.Value, fly: CanFlyEnum.Value, swim: CanSwimEnum.Value)