package controllers

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import services.{EmailService, EmailServicesImpl}

class Module extends AbstractModule {
  override protected def configure(): Unit = {
    bind(classOf[EmailService])
      .annotatedWith(Names.named("en"))
      .to(classOf[EmailServicesImpl])
  }
}
