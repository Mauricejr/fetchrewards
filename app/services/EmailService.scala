package services

import scala.concurrent.Future
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[EmailServicesImpl])
trait EmailService {
  def countUniqueEmails(emails: List[String]): Future[Int]
}
