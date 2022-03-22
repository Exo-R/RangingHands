package ru.ex0r.example;

import org.junit.jupiter.api.Test;
import ru.ex0r.example.ranging.PokerHand;
import ru.ex0r.example.ranging.PokerHandRankings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CombinationTest {

    @Test
    public void testEqualRoyalFlush(){

        PokerHand pokerHand = new PokerHand("AD JD TD QD KD");
        assertEquals(PokerHandRankings.ROYALFLUSH, pokerHand.getCombination());

        pokerHand = new PokerHand("AH JH TH QH KH");
        assertEquals(PokerHandRankings.ROYALFLUSH, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualRoyalFlush(){

        PokerHand pokerHand = new PokerHand("9D JD TD QD KD");
        assertNotSame(PokerHandRankings.ROYALFLUSH, pokerHand.getCombination());

        pokerHand = new PokerHand("AD JD TD QD KS");
        assertNotSame(PokerHandRankings.ROYALFLUSH, pokerHand.getCombination());
    }

    @Test
    public void testEqualStraightFlush(){

        PokerHand pokerHand = new PokerHand("9D TD JD QD KD");
        assertEquals(PokerHandRankings.STRAIGHTFLUSH, pokerHand.getCombination());

        pokerHand = new PokerHand("AD 2D 3D 4D 5D");
        assertEquals(PokerHandRankings.STRAIGHTFLUSH, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualStraightFlush(){

        PokerHand pokerHand = new PokerHand("7D 5D 2D 3D 4D");
        assertNotSame(PokerHandRankings.STRAIGHTFLUSH, pokerHand.getCombination());

        pokerHand = new PokerHand("6D 5D 2D 3D 4H");
        assertNotSame(PokerHandRankings.STRAIGHTFLUSH, pokerHand.getCombination());
    }

    @Test
    public void testEqualFourOfAKind(){

        PokerHand pokerHand = new PokerHand("KS KH KC QD KD");
        assertEquals(PokerHandRankings.FOUROFAKIND, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualFourOfAKind(){

        PokerHand pokerHand = new PokerHand("KS KH KC QD QH");
        assertNotSame(PokerHandRankings.FOUROFAKIND, pokerHand.getCombination());
    }

    @Test
    public void testEqualFullHouse(){

        PokerHand pokerHand = new PokerHand("KS KH KC QD QH");
        assertEquals(PokerHandRankings.FULLHOUSE, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualFullHouse(){

        PokerHand pokerHand = new PokerHand("KS KH JC QD QD");
        assertNotSame(PokerHandRankings.FULLHOUSE, pokerHand.getCombination());
    }

    @Test
    public void testEqualFlush(){

        PokerHand pokerHand = new PokerHand("2S AS KS QS TS");
        assertEquals(PokerHandRankings.FLUSH, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualFlush(){

        PokerHand pokerHand = new PokerHand("2S AS KS QS TC");
        assertNotSame(PokerHandRankings.FLUSH, pokerHand.getCombination());
    }

    @Test
    public void testEqualStraight(){

        PokerHand pokerHand = new PokerHand("2H AD 5S 3S 4C");
        assertEquals(PokerHandRankings.STRAIGHT, pokerHand.getCombination());

        pokerHand = new PokerHand("9H TD 8S QS JC");
        assertEquals(PokerHandRankings.STRAIGHT, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualStraight(){

        PokerHand pokerHand = new PokerHand("JH TD 8S QS JC");
        assertNotSame(PokerHandRankings.STRAIGHT, pokerHand.getCombination());

        pokerHand = new PokerHand("2H AD 6S 3S 4C");
        assertNotSame(PokerHandRankings.STRAIGHT, pokerHand.getCombination());
    }

    @Test
    public void testEqualThreeOfAKind(){

        PokerHand pokerHand = new PokerHand("5H AD 5D 5S 4C");
        assertEquals(PokerHandRankings.THREEOFAKIND, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualThreeOfAKind2(){

        PokerHand pokerHand = new PokerHand("KS KH KC QD QH");
        assertNotSame(PokerHandRankings.THREEOFAKIND, pokerHand.getCombination());
    }

    @Test
    public void testEqualTwoPairs(){

        PokerHand pokerHand = new PokerHand("5H AD 5D AS 4C");
        assertEquals(PokerHandRankings.TWOPAIRS, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualTwoPairs(){

        PokerHand pokerHand = new PokerHand("5H AD 5D AS 5C");
        assertNotSame(PokerHandRankings.TWOPAIRS, pokerHand.getCombination());
    }

    @Test
    public void testEqualPair(){

        PokerHand pokerHand = new PokerHand("4H 6D 5D AS 4C");
        assertEquals(PokerHandRankings.PAIR, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualPair2(){

        PokerHand pokerHand = new PokerHand("4H 6D 5D 4S 4C");
        assertNotSame(PokerHandRankings.PAIR, pokerHand.getCombination());
    }

    @Test
    public void testEqualHighCard(){

        PokerHand pokerHand = new PokerHand("4H 6D 5D AS 2C");
        assertEquals(PokerHandRankings.HIGHCARD, pokerHand.getCombination());
    }

    @Test
    public void testNotEqualHighCard(){

        PokerHand pokerHand = new PokerHand("4H 3D 5D 6S 2C");
        assertNotSame(PokerHandRankings.HIGHCARD, pokerHand.getCombination());
    }

}
