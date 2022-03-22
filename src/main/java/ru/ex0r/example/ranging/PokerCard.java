package ru.ex0r.example.ranging;

public class PokerCard {

    public CardValue cardValue;
    public CardSuit cardSuit;

    public PokerCard(CardValue cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    @Override
    public String toString() {
        return "PokerCard{" + "cardValue=" + cardValue + ", cardSuit=" + cardSuit + '}';
    }

    public static PokerCard parseCard(String card){

        String initCardVal = null;
        String initCardSuit = null;

        try {
            initCardVal = card.substring(0, card.length() - 1);
            initCardSuit = card.substring(card.length() - 1);

            if(initCardVal.isEmpty() || initCardSuit.isEmpty()){
                throw new Exception("The pokerCard [" + card + "] is incorrect!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        CardValue finalCardVal = null;
        CardSuit finalCardSuit  = null;

        try {
            for (CardValue cardvalue : CardValue.values()) {
                if (cardvalue.code.equals(initCardVal)) {
                    finalCardVal = cardvalue;
                    break;
                }
            }
            for (CardSuit cardSuit : CardSuit.values()) {
                if (cardSuit.code.equals(initCardSuit)) {
                    finalCardSuit = cardSuit;
                    break;
                }
            }

            if(finalCardVal == null || finalCardSuit == null){
                throw new Exception("Incorrect data of pokerCard: [" + initCardVal + initCardSuit + "]");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new PokerCard(finalCardVal, finalCardSuit);
    }

    public enum CardValue {

        TWO("2",2),
        THREE("3",3),
        FOUR("4",4),
        FIVE("5",5),
        SIX("6",6),
        SEVEN("7",7),
        EIGHT("8",8),
        NINE("9",9),
        TEN("T",10),
        JACK("J",11),
        QUEEN("Q",12),
        KING("K", 13),
        ACE("A",14);

        private final String code;
        private final int value;

        CardValue(String code, int value) {
            this.code = code;
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public int getValue() {
            return value;
        }
    }

    public enum CardSuit {

        SPADES("S"),
        HEARTS("H"),
        DIAMONDS("D"),
        CLUBS("C");

        private final String code;

        CardSuit(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
