package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private static final String name = "딜러";
    private int winCount = 0;

    public Dealer() {
        super(name);
    }

    public boolean isWinner(final int playerResult) {
        return (playerResult <= calculateScore() && !isBust());
    }

    public void increaseWinCount() {
        this.winCount++;
    }

    public int getWinCount() {
        return winCount;
    }

    @Override
    public List<Card> showInitialCards() {
        return cards.subList(0,1);
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return calculateScore() <= MAX_SUM_FOR_MORE_CARD;
    }
}
