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
  println(getIntAndAddOne("8").map(num => num + 1))// this is  the same as val rightSide as the .right notation is deprecated and not needed
  println(getIntAndAddOne("will not work").map(num => + 1)) //this will return Left(Failed) as the input cannot be made into a Int and will therefore use the left option
  println(getIntAndAddOne("also fail").left.map(word => word.toUpperCase)) //will return the Left(FAILED)

  getIntAndAddOne("4") match {
    case Left(_) => println("Left")
    case Right(_) => println("Right")
  }

  println(getIntAndAddOne("4"))
  println(getIntAndAddOne("bye"))

  def userExists(username:String, password: String): Boolean = {
    (username, password) match {
      case ("Boris", "pword123") => true
      case ("Barack", "merica") => true
      case _ => false
    }
  }

  def eitherUser(username: String, password: String): Either[String, Boolean] = {
    try {
      Right(userExists(username, password))
    } catch {
      case e: Exception => Left("UserNotFoundError")
    }
  }

  println(eitherUser("name", "password"))
  println(eitherUser("Barack", "merica"))


  object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] = {
      if (s.matches("-?[0-9]+")) Right(s.toInt)
      else Left(new NumberFormatException(s"${s} is not not a valid integer"))
    }
  }

  println("EitherStyle is right?: " + EitherStyle.parse("23").isRight)
  println("EitherStyle is left?: " + EitherStyle.parse("twenty").isLeft)
  //println("EitherStyle is left?: " + EitherStyle.parse(100).isLeft)// this will not compile as it is a type mismatch we have said this should expect a string but given it an int
  println("EitherStyle is left int toString?: " + EitherStyle.parse(100.toString).isLeft) //this returns false meaning it is not using the left side of the either but the right side
  println("EitherStyle is right with double?: " + EitherStyle.parse("21.5").isRight) //returns false so is using the left failing side of the either and getting the number format exception
}
