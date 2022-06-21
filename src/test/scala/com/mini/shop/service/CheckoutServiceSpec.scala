package com.mini.shop.service

import com.mini.shop.models.{DiscountOffer, FreeItemOffer, ItemOffers}
import com.mini.shop.repo.{ItemRepo, OfferRepo}
import com.mini.shop.util.MockDataModels
import com.mini.shop.util.MockDataModels.{item1, item2}
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import org.scalatestplus.mockito.MockitoSugar.mock

class CheckoutServiceSpec extends AnyFlatSpec{

  val itemRepoMock = mock[ItemRepo]
  val offerRepoMock = mock[OfferRepo]
  val checkoutService = new CheckoutService(itemRepoMock, offerRepoMock)

  "CheckoutServiceSpec" should "return correct amount for valid items" in {
    val items = List("Apple", "Apple")
    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(Seq.empty)

    val result  = checkoutService.checkout(items)
    result mustBe 1.20
  }

  "CheckoutServiceSpec" should "return correct amount only for valid items when invalid items is also in input list" in {
    val items = List("Apple", "Apple", "Grapes")
    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(Seq.empty)

    val result  = checkoutService.checkout(items)
    result mustBe 1.20
  }

  "CheckoutServiceSpec" should "return 0 invalid items" in {
    val items = List("Apple", "Apple", "Grapes")
    when(itemRepoMock.getItemByName(item1.name)) thenReturn(None)
    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(Seq.empty)


    val result  = checkoutService.checkout(items)
    result mustBe 0
  }

  "CheckoutServiceSpec" should "return 0 for empty items" in {
    val items = List()

    val result  = checkoutService.checkout(items)
    result mustBe 0
  }

  "CheckoutServiceSpec" should "return correct amount for valid items with valid free item offers" in {
    val items = List("Apple", "Apple")
    val itemOffers1 = ItemOffers(1, 1, 1, "freeItem", true, 1655802999016L)
    val itemOffers2 = ItemOffers(2, 1, 1, "discount", false, 1655802999017L)
    val offers = Seq(itemOffers1, itemOffers2)
    val freeItemOffer = FreeItemOffer(1, ">=1", 1)

    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(offers)
    when(offerRepoMock.getFreeItemOffers(item1.id)) thenReturn(Some(freeItemOffer))

    val result  = checkoutService.checkout(items)
    result mustBe 0.60
  }

  "CheckoutServiceSpec" should "return correct amount for valid items with valid discount offers" in {
    val items = List("Apple", "Apple")
    val itemOffers1 = ItemOffers(2, 1, 1, "discount", true, 1655802999017L)
    val itemOffers2 = ItemOffers(1, 1, 1, "freeItem", false, 1655802999016L)
    val offers = Seq(itemOffers1, itemOffers2)
    val discountItemOffer = DiscountOffer(1, ">=2", 3)

    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(offers)
    when(offerRepoMock.getDiscountOffers(item1.id)) thenReturn(Some(discountItemOffer))

    val result  = checkoutService.checkout(items)
    result mustBe 1.17
  }

  /***
   * For ease of use this is the test scenario given in the documentation
   */
  "CheckoutServiceSpec" should "return correct amount for valid items (Phase1 Use case)" in {
    val items = List("Apple", "Apple", "Orange", "Apple")
    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(itemRepoMock.getItemByName(item2.name)) thenReturn(Some(item2))

    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(Seq.empty)
    when(offerRepoMock.getItemOffersByItemId(item2.id)) thenReturn(Seq.empty)


    val result  = checkoutService.checkout(items)
    result mustBe 2.05
  }


  "CheckoutServiceSpec" should "return correct amount for valid items (Phase2 Use case - Buy 1 apple get 1)" in {
    val items = List("Apple", "Apple", "Orange", "Apple")
    val itemOffers1 = ItemOffers(1, 1, 1, "freeItem", true, 1655802999016L)
    val itemOffers2 = ItemOffers(3, 2, 2, "freeItem", true, 1655802999018L)
    val freeItemOffer1 = FreeItemOffer(1, ">1", 1)
    val freeItemOffer2 = FreeItemOffer(2, ">2", 1)
    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(itemRepoMock.getItemByName(item2.name)) thenReturn(Some(item2))

    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(Seq(itemOffers1))
    when(offerRepoMock.getItemOffersByItemId(item2.id)) thenReturn(Seq(itemOffers2))

    when(offerRepoMock.getFreeItemOffers(1)) thenReturn(Some(freeItemOffer1))
    when(offerRepoMock.getFreeItemOffers(2)) thenReturn(Some(freeItemOffer2))


    val result  = checkoutService.checkout(items)
    result mustBe 1.45
  }

  "CheckoutServiceSpec" should "return correct amount for valid items (Phase2 Use case - Buy 3 oranges pay for 2)" in {
    val items = List("Orange", "Orange", "Orange", "Apple")
    val itemOffers1 = ItemOffers(1, 1, 1, "freeItem", true, 1655802999016L)
    val itemOffers2 = ItemOffers(3, 2, 2, "freeItem", true, 1655802999018L)
    val freeItemOffer1 = FreeItemOffer(1, ">1", 1)
    val freeItemOffer2 = FreeItemOffer(2, ">2", 1)
    when(itemRepoMock.getItemByName(item1.name)) thenReturn(Some(item1))
    when(itemRepoMock.getItemByName(item2.name)) thenReturn(Some(item2))

    when(offerRepoMock.getItemOffersByItemId(item1.id)) thenReturn(Seq(itemOffers1))
    when(offerRepoMock.getItemOffersByItemId(item2.id)) thenReturn(Seq(itemOffers2))

    when(offerRepoMock.getFreeItemOffers(1)) thenReturn(Some(freeItemOffer1))
    when(offerRepoMock.getFreeItemOffers(2)) thenReturn(Some(freeItemOffer2))


    val result  = checkoutService.checkout(items)
    result mustBe 1.10
  }

}
