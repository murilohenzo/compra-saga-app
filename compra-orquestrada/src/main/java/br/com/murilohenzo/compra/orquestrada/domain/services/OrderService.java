package br.com.murilohenzo.compra.orquestrada.domain.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private Set<Long> orders = new HashSet<>();

  // do
  public void newOrder(Long id) {
    orders.add(id);
  }

  // undo: compensation transaction
  public void cancelOrder(Long id) {
    orders.remove(id);
  }
  
}
