package com.mini.shop.service

import com.mini.shop.models.Item
import com.mini.shop.repo.{ItemRepo, OfferRepo}
import com.mini.shop.util.ExpressionEval

class CheckoutService(itemRepo: ItemRepo, offerRepo: OfferRepo) {
  val defaultAmount: BigDecimal = 0
  val maxApplicableOffersPerItem = 1

  def checkout(items: List[String]): BigDecimal ={
    val itemsMap = items.groupMapReduce(identity)(_ => 1)(_ + _) /* converts list of items into map with name as key and quantity as value */
    itemsMap.foldLeft(defaultAmount){
      case(sum, (itemName, qty)) =>
        val currentSum = itemRepo.getItemByName(itemName) match {
          case Some(item) => {
            val currentTotal = qty * item.amount
            val totalAmount = calculateOffers(item, qty, currentTotal)
            totalAmount.setScale(2, BigDecimal.RoundingMode.UP)
          }
          case _ => Console.println(s"${itemName} is invalid! Please check again"); defaultAmount
        }
        sum + currentSum
    }
  }

  private def calculateOffers(item: Item, qty: Int, currentTotal: BigDecimal): BigDecimal ={
   val validOffers = offerRepo.getItemOffersByItemId(item.id).take(maxApplicableOffersPerItem) map{
     case i if i.offerType == "freeItem" => applyFreeItems(i.offerId, item, qty)
     case i if i.offerType == "discount" => applyDiscounts(i.offerId, item, qty)
     case _ => defaultAmount
   }
    currentTotal - validOffers.sum
  }

  private def applyFreeItems(offerId: Int, item: Item, qty: Int): BigDecimal = {
    offerRepo.getFreeItemOffers(offerId) match {
      case Some(freeItem) =>
        val validate = ExpressionEval.resolve(s"""$qty${freeItem.condition}""")
        if(validate) {
          freeItem.freeItems * item.amount
        }
        else defaultAmount
      case _ => defaultAmount
    }
  }

  private def applyDiscounts(offerId: Int, item: Item, qty: Int): BigDecimal = {
    offerRepo.getDiscountOffers(offerId) match {
      case Some(discount) =>
        val validate = ExpressionEval.resolve(s"""$qty${discount.condition}""")
        if(validate) {
          val currentTotal = qty * item.amount
          (currentTotal * (discount.discountPercentage/100))
        }
        else defaultAmount
      case _ => defaultAmount
    }
  }


}
