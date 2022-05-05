package part101

object Collections extends App {
  val everybodyNames = Seq("Connie", "Cristian", "Jacob", "Robyn", "Sarina", "Yonis")
  val randomMap = Map(1 -> "red", 2 -> "yellow", 3 -> "blue", 4 -> "refrigerator")
  val numSeq = Seq(6, 8, 3, 9, 2, 7)
  val addOne = numSeq.map(x => x + 1)
  val removeAllEvenNums = numSeq.filter(x => x % 2 != 0)
  //  val characterSeq = Seq("t", "g", "j", "d")
  //  val containLetterT = characterSeq.contains("t")
  val containLetterT = everybodyNames.flatten.contains('t') //.contains("t")
  println(addOne)
  println(removeAllEvenNums)
  println(containLetterT)
}
