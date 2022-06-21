package com.mini.shop.service

import com.mini.shop.Application.defaultAmount
import com.mini.shop.repo.ItemRepo

class CheckoutService(itemRepo: ItemRepo) {

  def checkout(items: List[String]): BigDecimal ={
    val itemsMap = items.groupMapReduce(identity)(_ => 1)(_ + _) /* converts list of items into map with name as key and quantity as value */
    itemsMap.foldLeft(defaultAmount){
      case(sum, (itemName, qty)) =>
        val currentSum = itemRepo.getItemByName(itemName) match {
          case Some(item) => {
            val totalAmount = qty * item.amount
            BigDecimal(totalAmount).setScale(2, BigDecimal.RoundingMode.UP)
          }
          case _ => Console.println(s"${itemName} is invalid! Please check again"); defaultAmount
        }
        sum + currentSum
    }
  }

}
