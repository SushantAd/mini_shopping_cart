package com.mini.shop.repo

import com.mini.shop.models.Item
import com.mini.shop.util.MockDataModels

class ItemRepo {

  def getItemByName(name: String): Option[Item] = MockDataModels.items.find(_.name == name)

}
