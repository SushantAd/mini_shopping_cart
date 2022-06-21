package com.mini.shop.repo

import com.mini.shop.models.{DiscountOffer, FreeItemOffer, Item, ItemOffers}
import com.mini.shop.util.MockDataModels

/***
 * Due to time constraints, adding all offer related repo in single class, can be later separated for individual data models
 */
class OfferRepo {

  def getItemOffersByItemId(id: Int): Seq[ItemOffers] =
    MockDataModels.offers
      .filter(offer => offer.itemId == id && offer.isActive)
      .sortBy(_.updatedAt)

  def getFreeItemOffers(offerId: Int): Option[FreeItemOffer] = MockDataModels.freeItems.find(_.id == offerId)

  def getDiscountOffers(offerId: Int): Option[DiscountOffer] = MockDataModels.discounts.find(_.id == offerId)
}
