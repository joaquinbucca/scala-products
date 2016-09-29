package routes

import akka.http.scaladsl.server.Directives._
import model.services.ProductService

import scala.concurrent.ExecutionContext

/**
  * Created by joaquinbucca on 9/27/16.
  */
class RouteHandler(productsService: ProductService)(implicit ex : ExecutionContext) {

  val productRouter = new ProductRouter(productsService)

  val routes = {
    logRequestResult("akka-http-microservice") {
      productRouter.route

    }
  }

}
