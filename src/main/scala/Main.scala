import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer


/**
  * Created by joaquinbucca on 9/27/16.
  */
object Main extends App with Config {
  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()


  val logger = Logging(system, getClass)


  private val productsService: ProductService = new ProductService
  val routeHandler = new RouteHandler(productsService)

  Http().bindAndHandle(routeHandler.routes, httpHost, httpPort)

}