object Operators extends App {
  val less = (55* (4 + 3)) < 300
  println(less) // false

  val greaterOrEqual = (96 / 12) >= 8
  println(greaterOrEqual)

  val lessString = "Hamster" < "Hippo"
  println(lessString)

  val even = 8 % 2 == 0
  println(s"Is even?: $even")

  val multipleLess = ((156 / 8) < 20) && ((54 / 4) < 14)
  println(multipleLess)

  val less2 = (57 *(3 + 4)) < 300
  println(less2)

  val greaterOrEq = (144 / 12) >= 12
  println(greaterOrEq)

  val stringLess = "cat" < "dog"
  println(stringLess)

  val odd= 17 % 2 != 0
  println(s"Is odd?: $odd")

  val lessAnd = ((75 / 9) < 30) && ((89 / 6) < 20)
  println(lessAnd)


}
