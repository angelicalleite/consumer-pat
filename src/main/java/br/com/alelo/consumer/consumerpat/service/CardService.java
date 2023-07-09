package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Card save(Card card) {
        log.info("Salvar card:" + card.toString());
        return cardRepository.save(card);
    }

    public Card getCardByNumber(String cardNumber) {
        log.info("Salvar card por id:" + cardNumber);
        return cardRepository.findByNumber(cardNumber);
    }

}
