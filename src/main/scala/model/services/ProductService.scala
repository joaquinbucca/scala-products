package model.services

import model.entities.ProductEntity

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by joaquinbucca on 9/27/16.
  */
class ProductService(implicit executionContext: ExecutionContext) {

  def getAllProducts: Future[Seq[ProductEntity]] = productsModel.getAll

  def getProductById(productId: String): Future[Option[ProductEntity]] = productsModel.getById(productId)

  def createProduct(product: ProductEntity): Future[ProductEntity] = {
    productsModel.store(product)
    productsModel.getByUsername(product.username).map(f => f.get )
  }

  def updateProduct(productUpdate: ProductEntity): Future[ProductEntity] = productsModel.store(productUpdate).map(r => productsModel.fromResultSet(r))

  def deleteProduct(productId: String): Future[String] = productsModel.deleteById(productId).map(rs => productId)


}
