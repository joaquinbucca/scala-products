import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext

/**
  * Created by joaquinbucca on 9/27/16.
  */
class ProductRouter(val productService: ProductService)(implicit executionContext: ExecutionContext) {

  import productService._

  val route = pathPrefix("products") {
      pathEndOrSingleSlash {
        post {
          entity(as[ProductEntity]) { prod =>
            complete("")
          }
        }
      } ~
      pathEndOrSingleSlash {
        get {
          complete("")
        }
      }
  }


}