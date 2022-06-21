package com.mini.shop.service

import com.mini.shop.repo.ItemRepo
import com.mini.shop.util.MockDataModels.item1
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.mockito.MockitoSugar.mock

class CheckoutServiceSpec extends AnyFlatSpec{

  val itemRepo = mock[ItemRepo]
  val checkoutService = new CheckoutService(itemRepo)

  "CheckoutServiceSpec" should "return correct amount for valid items" in {
    val items = List("Apple", "Apple")
    when(itemRepo.getItemByName(item1.name)) thenReturn(Some(item1))

    val result  = checkoutService.checkout(items)
    result mustBe 1.20
  }

  "CheckoutServiceSpec" should "return correct amount only for valid items when invalid items is also in input list" in {
    val items = List("Apple", "Apple", "Grapes")
    when(itemRepo.getItemByName(item1.name)) thenReturn(Some(item1))

    val result  = checkoutService.checkout(items)
    result mustBe 1.20
  }

  "CheckoutServiceSpec" should "return 0 invalid items" in {
    val items = List("Apple", "Apple", "Grapes")
    when(itemRepo.getItemByName(item1.name)) thenReturn(None)

    val result  = checkoutService.checkout(items)
    result mustBe 0
  }

  "CheckoutServiceSpec" should "return 0 for empty items" in {
    val items = List()

    val result  = checkoutService.checkout(items)
    result mustBe 0
  }

}
