package br.com.murilohenzo.compra.coreografada.adapters.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilohenzo.compra.coreografada.domain.services.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("purchases")
@RequiredArgsConstructor
public class ChoreographedBuying {

  private final OrderService orderService;

  @GetMapping("test")
  public ResponseEntity<Void> saga() {

    // credit = 100
    Long id = 0L;

    orderService.newOrder(++id, 20);
    orderService.newOrder(++id, 30);
    orderService.newOrder(++id, 30);
    orderService.newOrder(++id, 25);

    return ResponseEntity.ok().build();
  }
}
