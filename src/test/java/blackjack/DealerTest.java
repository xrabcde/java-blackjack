package blackjack;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {

    @Test
    @DisplayName("딜러가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Dealer("딜러"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러가 카드를 받았는지 확인")
    void receiveCard() {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(dealer.getCardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인")
    void checkMoreCardAvailable() {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.checkMoreCardAvailable()).isTrue();
    }

    @Test
    @DisplayName("딜러가 갖고 있는 카드의 합을 확인")
    void calculateMyCardSum() {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.calculate()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateMyCardSumWhenAceIsOne() {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.TWO, CardType.CLOVER));
        Assertions.assertThat(dealer.calculate()).isEqualTo(13);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12"}, delimiter = ':')
    @DisplayName("에이스 카드가 여러 개일 때 합 구하기")
    void calculateMyCardSumWhenAceIsTwo(final String input, final int expected) {
        Dealer dealer = new Dealer("딜러");
        final String[] inputs = input.split(",");
        for (String number : inputs) {
            CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveCard(new Card(cardNumber, CardType.CLOVER));
        }
        Assertions.assertThat(dealer.calculate()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트인지 확인")
    void isBust() {
        Dealer dealer = new Dealer("딜러");
        dealer.receiveCard(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.NINE, CardType.HEART));
        dealer.receiveCard(new Card(CardNumber.EIGHT, CardType.HEART));
        AssertionsForClassTypes.assertThat(dealer.isBust()).isTrue();
    }
}
