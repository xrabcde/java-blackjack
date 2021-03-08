package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(Dealer::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러가 Participant에게 상속받았는지 확인")
    void extend() {
        final Participant participant = new Dealer();
        participant.receiveOneCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 받았는지 확인")
    void receiveCard() {
        dealer.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(dealer.cardCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인")
    void checkMoreCardAvailable() {
        dealer.receiveOneCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.checkMoreCardAvailable()).isTrue();
    }

    @Test
    @DisplayName("딜러가 갖고 있는 카드의 합을 확인")
    void calculateCardSum() {
        dealer.receiveOneCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateCardSumWhenAceIsOne() {
        dealer.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.TWO, CardType.CLOVER));
        assertThat(dealer.calculateScore()).isEqualTo(13);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12"}, delimiter = ':')
    @DisplayName("에이스 카드가 여러 개일 때 합 구하기")
    void calculateCardSumWhenAceIsTwo(final String input, final int expected) {
        final String[] inputs = input.split(",");
        for (final String number : inputs) {
            final CardNumber cardNumber = CardNumber.valueOf(number);
            dealer.receiveOneCard(new Card(cardNumber, CardType.CLOVER));
        }
        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트인지 확인")
    void isBust() {
        dealer.receiveOneCard(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.NINE, CardType.HEART));
        dealer.receiveOneCard(new Card(CardNumber.EIGHT, CardType.HEART));
        assertThat(dealer.isBust()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"19:true", "21:false"}, delimiter = ':')
    @DisplayName("딜러가 승패계산을 잘 하는지 확인")
    void isWinner(final int playerResult, final boolean expected) {
        dealer.receiveOneCard(new Card(CardNumber.ACE, CardType.CLOVER));
        dealer.receiveOneCard(new Card(CardNumber.NINE, CardType.CLOVER));
        assertThat(dealer.isWinner(playerResult)).isEqualTo(expected);
    }
}
