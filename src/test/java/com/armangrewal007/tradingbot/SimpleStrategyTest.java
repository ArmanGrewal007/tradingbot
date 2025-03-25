package com.armangrewal007.tradingbot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
// import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.dto.position.PositionStatusDTO;
import tech.cassandre.trading.bot.dto.util.CurrencyDTO;
import static tech.cassandre.trading.bot.dto.util.CurrencyDTO.USDT;
import tech.cassandre.trading.bot.dto.util.GainDTO;
import tech.cassandre.trading.bot.test.mock.TickerFluxMock;

import static org.awaitility.Awaitility.await;
// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
/**
 * Simple strategy test.
 */
@SpringBootTest
@Import(TickerFluxMock.class)
@DisplayName("Simple strategy test")
public class SimpleStrategyTest {

	@Autowired
	private TickerFluxMock tickerFluxMock;

	/** Dumb strategy. */
	@Autowired
	private SimpleStrategy strategy;

	/**
	 * Check data reception.
	 */
	@Test
	@DisplayName("Check strategy behavioir")
	public void checkStrategy() {
		await().forever().until(() -> tickerFluxMock.isFluxDone());

    // =============================================================================================================
		System.out.println("");
		System.out.println("Gains by position");
		strategy.getPositions()
				.values()
				.forEach(positionDTO -> {
					if (positionDTO.getStatus().equals(PositionStatusDTO.CLOSED)) {
						System.out.println("Position " + positionDTO.getPositionId() + " closed with gain: " + positionDTO.getGain());
					} else {
						System.out.println("Position " + positionDTO.getPositionId() + " NOT closed with latest gain: " + positionDTO.getLatestCalculatedGain().get());
					}
				});

		// =============================================================================================================
		System.out.println("");
		System.out.println("Global gains");
		Map<CurrencyDTO, GainDTO> gains = strategy.getGains();
		gains.values().forEach(gainDTO -> System.out.println(gainDTO.getAmount()));
		assertFalse(gains.isEmpty(), "Failure, no gains");
		assertNotNull(gains.get(USDT), "Failure, USDT gains");
		assertTrue(gains.get(USDT).isSuperiorTo(GainDTO.ZERO), "Failure, USDT inferior to zero");

		// // Waiting to see if the strategy received the accounts update.
		// await().untilAsserted(() -> assertEquals(strategy.getAccounts().size(), 3));
	}

}
