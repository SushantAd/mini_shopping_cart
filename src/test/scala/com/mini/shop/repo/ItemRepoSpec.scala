package com.mini.shop.repo

import com.mini.shop.util.MockDataModels.item1
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

/***
 * Can currently only test happy scenario since we are using static mock data models
 */
class ItemRepoSpec extends AnyFlatSpec{

  val itemRepo = new ItemRepo

  "ItemRepoSpec" should "return item for valid item name" in {
    val result  = itemRepo.getItemByName(item1.name)
    result mustBe Some(item1)
  }

  "ItemRepoSpec" should "return none for invalid item name" in {
    val result  = itemRepo.getItemByName("invalid_item")
    result mustBe None
  }

}
