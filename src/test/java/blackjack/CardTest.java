package blackjack;

import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CardTest {
    @Test
    @DisplayName("카드가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Card(CardNumber.TWO, CardType.CLOVER))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("해당 카드가 ACE 넘버인지 확인")
    void isAce() {
        Card aceCard = new Card(CardNumber.ACE, CardType.CLOVER);
        assertThat(aceCard.isAce()).isTrue();

        Card notAceCard = new Card(CardNumber.TWO, CardType.CLOVER);
        assertThat(notAceCard.isAce()).isFalse();
    }
}
