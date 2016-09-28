package model.db

/**
  * Created by joaquinbucca on 9/20/16.
  */

import com.websudos.phantom.db.DatabaseImpl
import com.websudos.phantom.dsl._
import model.tables.ConcreteProducts
import utils.DbConnector._

class ProductsDatabase(override val connector: KeySpaceDef) extends DatabaseImpl(connector) {
  object productsModel extends ConcreteProducts with connector.Connector
}

/**
  * This is the production database, it connects to a secured cluster with multiple contact points
  */
object ProductionDb extends ProductsDatabase(connector)

trait ProductionDatabaseProvider {
  def database: ProductsDatabase
}

trait ProductionDatabase extends ProductionDatabaseProvider {
  override val database = ProductionDb
}

object ProductionDatabase extends ProductionDatabase with ProductionDatabaseProvider

/**
  * Thanks for the Phantom plugin, you can start an embedded cassandra in memory,
  * in this case we are using it for tests
  */
object EmbeddedDb extends ProductsDatabase(testConnector)

trait EmbeddedDatabaseProvider {
  def database: ProductsDatabase
}

trait EmbeddedDatabase extends EmbeddedDatabaseProvider {
  override val database = EmbeddedDb
}