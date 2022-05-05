package part101

object Methods extends App {
  def getBigVal(input1: Int, input2: Int): String = {
    val first = "first"
    val second = "second"
    val same = "same"
    if (input1 > input2) {
      first
    } else if (input1 < input2) {
      second
    } else if (input1 == input2) {
      same
    } else {
      "i do not know"
    }
  }

  println(getBigVal(8, 9))

  def nameLength(firstName: String, surname: String): Int = {
    if (firstName.length > surname.length) {
      firstName.length
    } else if (firstName.length < surname.length) {
      surname.length
    } else {
      0
    }
  }

  println(nameLength("Arnold", "Schwarzenegger"))
  println(nameLength("Bruce", "Lee"))
  println(nameLength("Ethan", "Hawke"))

}
