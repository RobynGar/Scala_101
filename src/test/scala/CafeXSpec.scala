import org.scalatest.FlatSpec
import part102.CafeX
import part102.CafeX.{Customer, Employee, FoodBeverage, Hot, MenuItem, Temperature}
import org.scalamock.scalatest.MockFactory

import java.time.LocalDateTime


class CafeXSpec extends FlatSpec {

  "bill only drinks" should "should not add a tip on to bill" in {

//    val date: LocalDateTime = LocalDateTime.now()
//
//    val mockedMenu = new MenuItem {
//      override val cost: BigDecimal = 2
//      override val temp: CafeX.Temperature = Hot
//      override val name: String = "test food"
//      override val vegetarian: Boolean = true
//      override val vegan: Boolean = true
//      override val foodType: CafeX.FoodBeverage.Value = FoodBeverage.Food
//      override val premiumItem: Boolean = false
//    }
//
//    val mockCustomer = new Customer {
//      override val loyaltyCard: Boolean = false
//      override val name: String = "test customer"
//      override val numOfStars: Int = 0
//    }
//    val mockEmployee = new Employee {
//      override val name: String = "test employee"
//      override val positionTitle: String = "tester"
//    }
//
//    val bill = CafeX.bill(mockEmployee, Some(mockCustomer), List(mockedMenu, mockedMenu))
//    assert(bill === s"Today you were served by ${mockEmployee.name}(${mockEmployee.positionTitle}). \n Your bill including service charge is £. \n  Time of transaction ${date.getHour}")
//
  }
}
