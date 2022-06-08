package part102


import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime, LocalTime}

object DateTimeExperiment extends App{
  //val billDate: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now())
  val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
 // println(formatter.format(billDate))
  println(formatter.format(LocalTime.now()))
  println(LocalDate.now())
  println(LocalTime.now())

  case class DataModel(_id: String, name: String, description: String, numSales: Int)

  val testModel: DataModel= DataModel("764", "ghrt", "sffr", 4)

  println(testModel)
}
