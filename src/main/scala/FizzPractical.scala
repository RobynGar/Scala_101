class FizzPractical {
  val fizzBuzz= (x: Int) =>{
    if (x % 3 == 0 && x % 5 != 0 && x != 0){
      x.toString + " Fizz"
    } else if (x % 5 == 0 && x % 3 != 0 && x != 0){
      x.toString + " Buzz"
    } else if ((x % 3 == 0) && (x % 5 == 0) && x != 0){
      x.toString + " FizzBuzz"
    } else {
      "no fizz buzz for you"
    }
  }
}
