//import scala.math.BigDecimal.double2bigDecimal
// above for the rounding to specific decimal place
class TaxPractical {
  val calculateTax = (x: Int) => {
    if(x >= 100000){
      (x * 0.4).round
      // .round rounds it up to a whole number so we will return an int
      //if you wanted to round it to a certain number of decimal places you could use the below method instead of .round
      //.setScale(1, BigDecimal.RoundingMode.HALF_UP).toDouble
    } else if (x >= 50000){
      (x * 0.2).round
    } else if (x >= 10000){
      (x * 0.15).round
    } else{
      (x * 0.1).round
    }

  }
}
