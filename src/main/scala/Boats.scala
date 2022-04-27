object Boats extends App{
  class Boat() {
    var length: Int = 0
    var width: Int = 0
    var topSpeed: Int = 0
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

  trait RenownedDesigner{
    var name: String
    var location: String
  }


  class SailBoat extends Boat {
    var numSails: Int = 0
    var hasOars: Boolean = false
    var canTrack: Boolean = false
  }

  class LuxurySailBoat extends SailBoat with RenownedDesigner {
    var hasJacuzzi: Boolean = false
    var hasBooze: Boolean = false
    override var name: String = _
    override var location: String = _
  }

  class PirateShip extends SailBoat with Artillery {
    var numOfGangPlanks: Int = 0
    override var numGuns: Int = _
    override var range: Int = _
  }

  class MotorBoat extends Boat{
    var engineSize: Int = 0
    var fuelType: String = ""
  }



  val theMapleBoat = new LuxurySailBoat{
    hasOars= false
    canTrack = true
    numSails = 5
    width = 700
    length= 1000
    topSpeed= 90
    hasJacuzzi= true
    hasBooze = true
    name = "Maple the Bear"
    location = "Brighton"
  }

//  println(theMapleBoat.numSails)
//  println(theMapleBoat.hasBooze)

  val theGrumpyMoose = new PirateShip {
    numGuns = 8
    range = 300
    hasOars= true
    canTrack= false
    numSails= 4
    width = 900
    length = 1500
    topSpeed= 100
    numOfGangPlanks= 6
  }

//  println(theGrumpyMoose.numOfGangPlanks)
  println(theGrumpyMoose.range)

  println(theMapleBoat.isFasterThan(theGrumpyMoose))



}
