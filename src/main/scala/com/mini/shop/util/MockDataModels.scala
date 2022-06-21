package com.mini.shop.util

import com.mini.shop.models.Item

/***
 * Only for testing purpose
 */
object MockDataModels {

  val item1 =  Item(1, "Apple", 0.60)
  val item2 =  Item(2, "Orange", 0.25)
  val item3 =  Item(3, "Grapes", 1.25)

  val items = List(item1, item2, item2)

}
