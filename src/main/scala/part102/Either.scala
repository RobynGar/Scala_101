package part102

object Either extends App{

  def getIntAndAddOne(s: String): Either[String, Int] = {
    try{
      Right(s.toInt) //Right(s.toInt + 1) - could write this if wanted this method to add 1 as name suggests
    } catch {
      case e: Exception => Left("Failed")
    }
  }

  println(getIntAndAddOne("1"))
  println(getIntAndAddOne("blue"))

  println(getIntAndAddOne("2").isLeft) //RETURNS A BOOLEAN = false
  println(getIntAndAddOne("2").isRight) //true

  val rightSide = getIntAndAddOne("6").right.map(num => num + 1)
  println(rightSide)
  println(getIntAndAddOne("8").map(num => num + 1))// this is  the same as val rightSide as the .right notation is depreciated and not needed
  println(getIntAndAddOne("will not work").map(num => + 1)) //this will return Left(Failed) as the input cannot be made into a Int and will therefore use the left option
  println(getIntAndAddOne("also fail").left.map(word => word.toUpperCase)) //will return the Left(FAILED)


}
