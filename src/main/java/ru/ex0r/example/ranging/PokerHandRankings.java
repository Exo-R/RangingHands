package ru.ex0r.example.ranging;

public enum PokerHandRankings {

    HIGHCARD("HighCard", 1),
    PAIR("Pair", 2),
    TWOPAIRS("TwoPairs", 3),
    THREEOFAKIND("ThreeOfAKind", 4),
    STRAIGHT("Straight", 5),
    FLUSH("Flush", 6),
    FULLHOUSE("FullHouse", 7),
    FOUROFAKIND("FourOfAKind", 8),
    STRAIGHTFLUSH("StraightFlush", 9),
    ROYALFLUSH("RoyalFlush", 10);

    private final String code;
    private final int value;

    PokerHandRankings(String code, int value) {
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
