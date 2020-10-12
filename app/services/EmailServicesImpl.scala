package services

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class EmailServicesImpl extends EmailService {


  private def filterOutPlus(data: String): String = {
    @tailrec
    def filterOutPlusRecur(s: String, value: String): String = {

      if (s.isEmpty) value
      else if (s.head == '@') value + (s.head + s.tail)
      else filterOutPlusRecur(s.tail, value)
    }

    filterOutPlusRecur(data, "")
  }

  private def filterOutPeriod(data: String): String = {
    @tailrec
    def filterOutPeriodRecur(s: String, value: String): String = {
      if (s.isEmpty) value
      else if (s.head == '.' && s.tail.length != 3) filterOutPeriodRecur(s.tail, value)
      else if (s.head == '+') value + filterOutPlus(s.tail)
      else if (s.head == '.' && s.tail.length == 3) filterOutPeriodRecur(s.tail, value + s.head)
      else filterOutPeriodRecur(s.tail, value + s.head)
    }

    filterOutPeriodRecur(data, "")
  }

  def countUniqueEmails(emails: List[String]): Future[Int] = {
    Future {
      val extract = emails.map(x => filterOutPeriod(x))
      val mapOfEmails = extract.map(x => (x -> x)).toMap
      mapOfEmails.size
    }
  }
}
