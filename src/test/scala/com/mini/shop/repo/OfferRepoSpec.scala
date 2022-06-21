package com.mini.shop.repo

import com.mini.shop.models.{DiscountOffer, FreeItemOffer, ItemOffers}
import com.mini.shop.util.MockDataModels.item1
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

/***
 * Can currently only test happy scenario since we are using static mock data models
 */

class OfferRepoSpec extends AnyFlatSpec{

  val offerRepo = new OfferRepo

  val validItemOffers = Seq(ItemOffers(1, 1, 1, "freeItem", true, 1655802999016L))

  val validSortedItemOffers = Seq(ItemOffers(6, 5, 3, "discount", true, 1655802999020L),
    ItemOffers(8, 5, 5, "discount", true, 1655802999022L),
    ItemOffers(7, 5, 4, "discount", true, 1655802999021L))

  val itemOffers = Seq(
    ItemOffers(1, 1, 1, "freeItem", true, 1655802999016L),
    ItemOffers(2, 1, 1, "discount", false, 1655802999017L)
  )

  val freeItemOffer =  FreeItemOffer(1, ">1", 1)
  val discountOffer =  DiscountOffer(1, ">=5", 5.00)


  "OfferRepoSpec" should "return itemOffers for valid item id" in {
    val result  = offerRepo.getItemOffersByItemId(item1.id)
    result mustBe validItemOffers
  }

  "OfferRepoSpec" should "return empty list for invalid item id" in {
    val result  = offerRepo.getItemOffersByItemId(99)
    result mustBe Seq.empty
  }

  "OfferRepoSpec" should "return sorted itemOffers for valid item id" in {
    val result  = offerRepo.getItemOffersByItemId(5)
    result mustBe validSortedItemOffers.sortBy(_.updatedAt)
  }

  "OfferRepoSpec" should "return FreeItemOffers for valid offerId" in {
    val result  = offerRepo.getFreeItemOffers(1)
    result mustBe Some(freeItemOffer)
  }

  "OfferRepoSpec" should "return None - FreeItemOffers for valid offerId" in {
    val result  = offerRepo.getFreeItemOffers(99)
    result mustBe None
  }

  "OfferRepoSpec" should "return DiscountOffers for valid offerId" in {
    val result  = offerRepo.getDiscountOffers(1)
    result mustBe Some(discountOffer)
  }

  "OfferRepoSpec" should "return None - DiscountOffers for valid offerId" in {
    val result  = offerRepo.getDiscountOffers(99)
    result mustBe None
  }


}
