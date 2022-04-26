object Boats extends App{
  class Boat() {
    var length: Int = 0
    var width: Int = 0
    var topSpeed: Int = 0
  }

  trait Artillery {
    var numGuns: Int
    var range: Int
  }

  trait RenownedDesigner{
    val name: String
    val location: String
  }


  class SailBoat extends Boat {
    var numSails: Int = 0
    var hasOars: Boolean = false
    var canTrack: Boolean = false
  }

  class LuxurySailBoat extends SailBoat {
    var hasJacuzzi: Boolean = false
    var hasBooze: Boolean = false
  }

  class PirateShip extends SailBoat {
    var numOfGangPlanks: Int = 0
  }

  class MotorBoat extends Boat{
    var engineSize: Int = 0
    var fuelType: String = ""
  }



  val theMapleBoat = new LuxurySailBoat with RenownedDesigner {
    hasOars= false
    canTrack = true
    numSails = 5
    width = 700
    length= 1000
    topSpeed= 90
    hasJacuzzi= true
    hasBooze = true
    override val name: String = "Maple the Bear"
    override val location: String = "Brighton"
  }

  println(theMapleBoat.numSails)
  println(theMapleBoat.hasBooze)

  val theGrumpyMoose = new PirateShip with Artillery {
    override var numGuns: Int = 8
    override var range: Int = 300
    hasOars= true
    canTrack= false
    numSails= 4
    width = 900
    length = 1500
    topSpeed= 50
    numOfGangPlanks= 6
  }

  println(theGrumpyMoose.numOfGangPlanks)





}
