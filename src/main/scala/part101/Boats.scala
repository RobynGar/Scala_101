package part101

object Boats extends App {
  class Boat(length: Int = 0, width: Int = 0, val topSpeed: Int = 0) {
    val isFasterThan = (a: Boat) => {
      //      if(Boat.this.topSpeed > a.topSpeed){
      //        true
      //      } else if (Boat.this.topSpeed < a.topSpeed){
      //        false
      //      } else {
      //        false
      //      }
      Boat.this.topSpeed > a.topSpeed
    }
  }

  trait Artillery {
    var numGuns: Int
    var range: Int
  }

  trait RenownedDesigner {
    var name: String
    var location: String
  }


  class SailBoat(numSails: Int, hasOars: Boolean = false, canTrack: Boolean = false) extends Boat {

  }

  class LuxurySailBoat(hasJacuzzi: Boolean = false, hasBooze: Boolean = false) extends SailBoat(3, false, true) with RenownedDesigner {
    override var name: String = _
    override var location: String = _
  }

  class PirateShip(numOfGangPlanks: Int = 0) extends SailBoat(5, true, false) with Artillery {
    override var numGuns: Int = _
    override var range: Int = _
    val canOutGun = (a: PirateShip) => {
      PirateShip.this.numGuns > a.numGuns
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
    name = "Maple the Bear"
    location = "Brighton"
  }

  //  println(theMapleBoat.numSails)
  //  println(theMapleBoat.hasBooze)

  val theGrumpyMoose = new PirateShip(6) {

    numGuns = 8
    range = 300
    //    hasOars= true
    //    canTrack= false
    //    numSails= 4
    //    width = 900
    //    length = 1500
    //    topSpeed= 100

  }
  val comparisonPirates = new PirateShip {
    numGuns = 3
    range = 500
  }
  println(theGrumpyMoose.canOutGun(comparisonPirates))
  println(theGrumpyMoose.range)
  println("can the ship fly: " + theGrumpyMoose.canFly("wings"))
  println("can the ship fly: " + theGrumpyMoose.canFly("pixie dust"))
  println(theMapleBoat.isFasterThan(theGrumpyMoose))


}
