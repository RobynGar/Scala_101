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
}
