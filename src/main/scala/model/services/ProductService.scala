package model.services

import model.entities.ProductEntity

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by joaquinbucca on 9/27/16.
  */
class ProductService(implicit executionContext: ExecutionContext) {

  import model.db.ProductionDatabase.database._

  def getAllProducts: Future[Seq[ProductEntity]] = productsModel.getAll

  def getProductById(productId: String): Future[Option[ProductEntity]] = productsModel.getById(productId)

  def createProduct(product: ProductEntity): Future[ProductEntity] = {
    productsModel.store(product).flatMap(p =>  productsModel.getById(product.id).map(f => f.get ))
  }

  def updateProduct(productUpdate: ProductEntity): Future[ProductEntity] = {
    productsModel.store(productUpdate).flatMap(p =>  productsModel.getById(productUpdate.id).map(f => f.get))
  }

  def deleteProduct(productId: String): Future[String] = productsModel.deleteById(productId).map(rs => productId)


}
