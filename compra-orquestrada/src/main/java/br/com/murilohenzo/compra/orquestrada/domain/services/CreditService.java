package br.com.murilohenzo.compra.orquestrada.domain.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CreditService {

  private int totalCredit;
  private Map<Long, Integer> valueOrders = new HashMap<>();

  public CreditService() {
    this.totalCredit = 100;
  }

  public void newOrderValue(Long orderId, int value) {
    if (value > totalCredit) {
      throw new IllegalStateException("Saldo insuficiente");
    }

    totalCredit = totalCredit - value;
    valueOrders.put(orderId, value);
  }

  // undo: compensation transaction
  public void cancelOrderValue(Long orderId) {
    totalCredit = totalCredit + valueOrders.get(orderId);
    valueOrders.remove(orderId);
  }

  public int getTotalCredit() {
    return totalCredit;
  }
  
}
