package br.com.murilohenzo.compra.coreografada.domain.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  private Set<Long> orders = new HashSet<>();

  private final CreditService creditService;

  // do
  public void newOrder(Long id, int value) {
    orders.add(id);
    log.info("Pedido {} registrado no valor de {}. Saldo disponivel {}", id, value, creditService.getTotalCredit());
    try {
      creditService.newOrderValue(id, value);
    } catch (IllegalStateException e) {
      log.info("Pedido {} estornado no valor de {}. Saldo disponivel: {}", id, value, creditService.getTotalCredit());
      cancelOrder(id);
    }
  }

  // undo: compensation transaction
  public void cancelOrder(Long id) {
    orders.remove(id);
  }
  
}
