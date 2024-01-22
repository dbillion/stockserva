package org.oludayofinance.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class RSocketStockClientIntegrationTest {
    private WebClient webClient= WebClient.builder().build();
    @Test
    void ShouldRetrieveStockPricesFromTheService() {
//        given
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);

//        when
        Flux<StockPrice> prices =webClientStockClient.pricesFor("SYMBOL");

//        then
        Assertions.assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertEquals(5,fivePrices.count().block());
        Assertions.assertEquals("SYMBOL",fivePrices.blockFirst().getSymbol());
     //        Assertions.assertTrue(fivePrices.count().block() > 0);
//        Assertions.assertTrue(prices.take(5).count().block() > 0); first change
    }

}
