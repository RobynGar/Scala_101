package part101

object Boats extends App {
  class Boat(length: Int = 0, width: Int = 0, val topSpeed: Int = 0) {
    val isFasterThan = (a: Boat) => {
     topSpeed > a.topSpeed
    }
  }

  trait Artillery {
    val numGuns: Int
    val range: Int
  }

  trait RenownedDesigner {
    val name: String
    val location: String
  }


  class SailBoat(numSails: Int = 0, hasOars: Boolean = false, canTrack: Boolean = false) extends Boat {

  }

  class LuxurySailBoat(hasJacuzzi: Boolean = false, hasBooze: Boolean = false) extends SailBoat with RenownedDesigner {
    override val name: String = ""
    override val location: String = ""
  }

  class PirateShip(numOfGangPlanks: Int = 0) extends SailBoat(5, true, false) with Artillery {
    override val numGuns: Int = 0
    override val range: Int = 0
    val canOutGun = (a: PirateShip) => {
      numGuns > a.numGuns
    }

    val canFly = (whatTheyAreUsingToFly: String) => {
      if (whatTheyAreUsingToFly == "pixie dust") {
        true
      } else {
        false
      }
    }
  }


  val theMapleBoat = new LuxurySailBoat(true, false) {
    override val name = "Maple the Bear"
    override val location = "Brighton"
  }

  //  println(theMapleBoat.numSails)
  //  println(theMapleBoat.hasBooze)

  val theGrumpyMoose = new PirateShip(6) {

    override val numGuns = 8
    override val range = 300
    //    hasOars= true
    //    canTrack= false
    //    numSails= 4
    //    width = 900
    //    length = 1500
    //    topSpeed= 100

  }
  val comparisonPirates = new PirateShip {
    override val numGuns = 3
    override val range = 500
  }
  println("outgun: " + theGrumpyMoose.canOutGun(comparisonPirates))
  println(theGrumpyMoose.range)
  println("can the ship fly: " + theGrumpyMoose.canFly("wings"))
  println("can the ship fly: " + theGrumpyMoose.canFly("pixie dust"))
  println(theMapleBoat.isFasterThan(theGrumpyMoose))


}
//object Boats extends App {
//
//  class Boat (val length: Int, val width: Int = 0, val speed: Int = 0) {
//    def isFaster(newBoatSpeed: Boat): Boolean =
//      speed > newBoatSpeed.speed
//  }
//
//  trait hasArtillery {
//    val hasArtillary: Boolean
//  }
//
//  trait hasDesigner {
//    val hasDesigner: Boolean
//  }
//
// class Sailboat (val numSails: Int = 1, val hasOars: Boolean = true, val canTrack: Boolean = true ) extends Boat(0)
//
//  class LuxurySailBoat (val hasJacuzzi: Boolean, val hasBooze: Boolean) extends Sailboat
//
//  class PirateShip (val numGangPlanks: Int) extends Sailboat(3,true,true)
//
// val luxurySailboatMako = new LuxurySailBoat(true, true) with hasDesigner {
//   override val hasDesigner: Boolean = true
//   override val speed: Int = 5
// }
//
//  lazy val pirateShipRolo = new PirateShip(4) with hasArtillery{
//    override val hasArtillary: Boolean = true
//    override val speed = 1
//    override val numSails: Int = 4
//  }
//
//  println(luxurySailboatMako.hasJacuzzi.toString)
//  println(pirateShipRolo.numGangPlanks.toString)
//  println(pirateShipRolo.speed.toString)
//  println(luxurySailboatMako.isFaster(pirateShipRolo).toString)
//
//}