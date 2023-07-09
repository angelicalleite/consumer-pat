package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor

@Validated
@RestController
@RequestMapping("/v1/consumer")
public class ConsumerV1Controller {

    private final ConsumerService service;

    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(@PageableDefault(size = 50) final Pageable pageable) {
        return ResponseEntity.ok(service.findConsumersPageable(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumer(@RequestBody @Valid ConsumerDTO consumerDTO) {
        service.createConsumer(service.toEntity(consumerDTO));
    }

    @PutMapping("/{id}")
    public void updateConsumer(@PathVariable("id") Long id, @RequestBody ConsumerDTO consumerDTO) {
        Optional<Consumer> consumer = service.getById(id);

        if (consumer.isPresent())
            service.updateConsumer(consumerDTO);
    }

    @PutMapping("/{cardNumber}/balance")
    public void setCardBalance(@PathVariable("cardNumber") Integer cardNumber,
                               @RequestParam("value") Double value) {
        service.setBalance(cardNumber, value);
    }

    @PostMapping("/{cardNumber}/transactions")
    public void makeTransaction(@PathVariable("cardNumber") int cardNumber,
                                @RequestParam("establishmentType") int establishmentType,
                                @RequestParam("establishmentName") String establishmentName,
                                @RequestParam("productDescription") String productDescription,
                                @RequestParam("value") double value) {

        service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
