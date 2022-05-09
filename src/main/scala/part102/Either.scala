package part102

object Either extends App{

  def getIntAndAddOne(s: String): Either[String, Int] = {
    try{
      Right(s.toInt)
    } catch {
      case e: Exception => Left("Failed")
    }
  }

  println(getIntAndAddOne("1"))
  println(getIntAndAddOne("blue"))

  println(getIntAndAddOne("2").isLeft) //RETURNS A BOOLEAN = false
  println(getIntAndAddOne("2").isRight) //true


}
