package routes

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.CirceSupport
import model.entities.ProductEntity
import model.services.ProductService
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

/**
  * Created by joaquinbucca on 9/27/16.
  */
class ProductRouter(val productService: ProductService)(implicit executionContext: ExecutionContext) extends CirceSupport {

  import productService._

  val route = pathPrefix("products") {
    pathEndOrSingleSlash {
      get {
        complete(getAllProducts.map(_.asJson))
      } ~
      post {
        entity(as[ProductEntity]) { product =>
          complete(createProduct(product).map(_.asJson))
        }
      }
    } ~
    pathPrefix(Rest) { productId =>
      pathEndOrSingleSlash {
        get {
          complete(getProductById(productId).map(_.asJson))
        } ~
          post {
            entity(as[ProductEntity]) { productUpdate =>
              complete(updateProduct(productUpdate).map(_.asJson))
            }
          } ~
          delete {
            onSuccess(deleteProduct(productId)) { ignored =>
              complete(NoContent)
            }
          }
      }
    }
  }


}