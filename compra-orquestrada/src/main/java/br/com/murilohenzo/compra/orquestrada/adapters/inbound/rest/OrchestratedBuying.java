package br.com.murilohenzo.compra.orquestrada.adapters.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilohenzo.compra.orquestrada.domain.services.CreditService;
import br.com.murilohenzo.compra.orquestrada.domain.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("purchases")
@RequiredArgsConstructor
public class OrchestratedBuying {

  private final OrderService orderService;
  private final CreditService creditService;

  @GetMapping("test")
  public ResponseEntity<Void> saga() {

    // credit = 100
    Long id = 0L;

    buy(++id, 20);
    buy(++id, 30);
    buy(++id, 30);
    buy(++id, 25);

    return ResponseEntity.ok().build();
  }

  private void buy(Long id, int value) {
    orderService.newOrder(id);
    try {
      creditService.newOrderValue(id, value);
      log.info("Pedido {} registrado no valor de {}. Saldo disponivel: {}", id, value, creditService.getTotalCredit());
    } catch (IllegalStateException e) {
      orderService.cancelOrder(id);
      log.info("Pedido {} estornado no valor de {}. Saldo disponivel: {}", id, value, creditService.getTotalCredit());
    }
  }
}
