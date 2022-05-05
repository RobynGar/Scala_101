package part101

object Scope extends App {
  //  object Numbers {
  //    val x = 10
  //    val y = 20
  //    val z = 30
  //  }
  object Calculator {

    //    import Numbers._
    def addXAndY(x: Int, y: Int) = {
      //      val x =100
      x + Numbers.y //do not need to import Numbers as we are calling on Numbers here with Numbers.y meaning the y value in the Numbers object
    }
    // uncomment the val x = 100 and this will use that value over the 1 passed in as x in the parenthesis
    println(addXAndY(1, 2)) // the addXAndY method which will not use 2 as it is getting the value of y from the numbers object
    //    println(x + y) // x and y cannot be found in this object so it looks to the imported object to find them need to have the import Numbers._
  }

  Calculator // calling object


}
