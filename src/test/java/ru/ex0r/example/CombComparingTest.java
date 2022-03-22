package ru.ex0r.example;

import org.junit.jupiter.api.Test;
import ru.ex0r.example.ranging.PokerHand;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CombComparingTest{

    @Test
    public void testEqualRoyalFlushs(){

        PokerHand pokerHand1 = new PokerHand("AD JD TD QD KD");
        PokerHand pokerHand2 = new PokerHand("AH JH TH QH KH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualStraightFlushs(){

        PokerHand pokerHand1 = new PokerHand("QD JD TD 9D 8D");
        PokerHand pokerHand2 = new PokerHand("JH TH 9H 8H 7H");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("2H 3H 4H 5H 6H");
        pokerHand2 = new PokerHand("QD JD TD 9D 8D");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("2H 3H 4H 5H 6H");
        pokerHand2 = new PokerHand("AD 2D 3D 4D 5D");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("AD 2D 3D 4D 5D");
        pokerHand2 = new PokerHand("2H 3H 4H 5H 6H");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualStraightFlushs(){

        PokerHand pokerHand1 = new PokerHand("AD 2D 3D 4D 5D");
        PokerHand pokerHand2 = new PokerHand("AH 2H 3H 4H 5H");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));

        pokerHand1 = new PokerHand("AD KD QD JD TD");
        pokerHand2 = new PokerHand("AH KH QH JH TH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualFourOfAKinds(){

        PokerHand pokerHand1 = new PokerHand("QH QS QC QD 9H");
        PokerHand pokerHand2 = new PokerHand("9H 9S 9C 9D QH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("9H 9S 9C 9D QH");
        pokerHand2 = new PokerHand("QH QS QC QD 9H");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("9H 9S 9C 9D KH");
        pokerHand2 = new PokerHand("9H 9S 9C 9D QH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("9H 9S 9C 9D QH");
        pokerHand2 = new PokerHand("9H 9S 9C 9D KH");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualFourOfAKinds(){

        PokerHand pokerHand1 = new PokerHand("9H 9S 9C 9D QH");
        PokerHand pokerHand2 = new PokerHand("9H 9S 9C 9D QH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualFullHouses(){

        PokerHand pokerHand1 = new PokerHand("QH QS QC 9D 9H");
        PokerHand pokerHand2 = new PokerHand("JH JS JC AD AH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("JH JS JC AD AH");
        pokerHand2 = new PokerHand("QH QS QC 9D 9H");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualFullHouses(){

        PokerHand pokerHand1 = new PokerHand("JH JS JC AD AH");
        PokerHand pokerHand2 = new PokerHand("JH JS JC AD AH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualFlushs(){

        PokerHand pokerHand1 = new PokerHand("QS TS JS 8S AS");
        PokerHand pokerHand2 = new PokerHand("QH TH JH 8H KH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("QH TH JH 8H KH");
        pokerHand2 = new PokerHand("QS TS JS 8S AS");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualFlushs(){

        PokerHand pokerHand1 = new PokerHand("QH TH JH 9H KH");
        PokerHand pokerHand2 = new PokerHand("QS TS JS 9S KS");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualStraights(){

        PokerHand pokerHand1 = new PokerHand("AH KS QC JD TH");
        PokerHand pokerHand2 = new PokerHand("KS QC JD TH 9H");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("KS QC JD TH 9H");
        pokerHand2 = new PokerHand("AH KS QC JD TH");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("KS QC JD TH 9H");
        pokerHand2 = new PokerHand("AH 5S 4C 3D 2H");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("AH 2S 3C 4D 5H");
        pokerHand2 = new PokerHand("2H 3S 4C 5D 6H");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualStraights(){

        PokerHand pokerHand1 = new PokerHand("AS KC QS JH TD");
        PokerHand pokerHand2 = new PokerHand("AH KS QC JD TH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));

        pokerHand1 = new PokerHand("AS 2H 3D 4D 5C");
        pokerHand2 = new PokerHand("AH 2S 3C 4D 5H");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualThreeOfAKinds() {

        PokerHand pokerHand1 = new PokerHand("AH AS AC 8D 9H");
        PokerHand pokerHand2 = new PokerHand("KH KS KC 8D AH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("KH KS KC 8D AH");
        pokerHand2 = new PokerHand("AH AS AC 8D 9H");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("AH AS AC 8D KH");
        pokerHand2 = new PokerHand("AH AS AC 8D QH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("AH AS AC 8D QH");
        pokerHand2 = new PokerHand("AH AS AC 8D KH");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualThreeOfAKinds() {

        PokerHand pokerHand1 = new PokerHand("AH AS AC 8D KH");
        PokerHand pokerHand2 = new PokerHand("AH AS AC 8D KH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualTwoPairs() {

        PokerHand pokerHand1 = new PokerHand("AS AC QS QH TD");
        PokerHand pokerHand2 = new PokerHand("KH KS QC QD TH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("KH KS QC QD TH");
        pokerHand2 = new PokerHand("AS AC QS QH TD");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("AS AC QS QH TD");
        pokerHand2 = new PokerHand("AH AS JC JD TH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("AH AS JC JD TH");
        pokerHand2 = new PokerHand("AS AC QS QH TD");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("AS AC QS QH TD");
        pokerHand2 = new PokerHand("AH AS QC QD 9H");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("AH AS QC QD 9H");
        pokerHand2 = new PokerHand("AS AC QS QH TD");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualTwoPairs() {

        PokerHand pokerHand1 = new PokerHand("AS AC QS QH TD");
        PokerHand pokerHand2 = new PokerHand("AH AS QC QD TH");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualPairs() {

        PokerHand pokerHand1 = new PokerHand("AS AC QS JH TD");
        PokerHand pokerHand2 = new PokerHand("KH KS QC JD TH");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("KH KS QC JD TH");
        pokerHand2 = new PokerHand("AS AC QS JH TD");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("KH KS QC JD TH");
        pokerHand2 = new PokerHand("KS KC JS TH 9D");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("KH KS JC TD 9H");
        pokerHand2 = new PokerHand("KS KC QS JH TD");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualPairs() {

        PokerHand pokerHand1 = new PokerHand("AS AC QS JH TD");
        PokerHand pokerHand2 = new PokerHand("AD AG QC JC TS");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testNotEqualHighCards() {

        PokerHand pokerHand1 = new PokerHand("AS QS 9C JH 2H");
        PokerHand pokerHand2 = new PokerHand("KS QS 9C JH 2D");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("KS QS 9C JH 2D");
        pokerHand2 = new PokerHand("AS QS 9C JH 2H");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);

        pokerHand1 = new PokerHand("AS KC QS JH 2H");
        pokerHand2 = new PokerHand("AS QC JS 9H 2D");
        assertTrue(pokerHand1.compareTo(pokerHand2) > 0);

        pokerHand1 = new PokerHand("AS QC JS 9H 2H");
        pokerHand2 = new PokerHand("AS KC QS JH 2D");
        assertTrue(pokerHand1.compareTo(pokerHand2) < 0);
    }

    @Test
    public void testEqualHighCards() {

        PokerHand pokerHand1 = new PokerHand("KS QS 9C JH 2H");
        PokerHand pokerHand2 = new PokerHand("KS QS 9C JH 2D");
        assertEquals(0, pokerHand1.compareTo(pokerHand2));
    }

    @Test
    public void testEqualAllCombinations(){

        ArrayList<PokerHand> hands = new ArrayList<>();

        PokerHand RoyalFlush = new PokerHand("AS KS QS JS TS");
        PokerHand StraightFlush = new PokerHand("AC 2C 3C 4C 5C");
        PokerHand FourOfAKind = new PokerHand("AS AC AS AH 8D");
        PokerHand FullHouse = new PokerHand("AS AC AS TH TD");
        PokerHand Flush = new PokerHand("AD QD JD 9D 8D");
        PokerHand Straight = new PokerHand("QS JC TS 9H 8D");
        PokerHand ThreeOfAKind = new PokerHand("AS AC AH 9H 8D");
        PokerHand TwoPairs = new PokerHand("KS KC JS JH 8D");
        PokerHand Pair = new PokerHand("TS TC 2S 9H 8D");
        PokerHand HighCard = new PokerHand("AS QC JS 9H 8D");

        hands.add(RoyalFlush);
        hands.add(StraightFlush);
        hands.add(FourOfAKind);
        hands.add(FullHouse);
        hands.add(Flush);
        hands.add(Straight);
        hands.add(ThreeOfAKind);
        hands.add(TwoPairs);
        hands.add(Pair);
        hands.add(HighCard);

        Collections.sort(hands);

        assertEquals(HighCard, hands.get(0));
        assertEquals(Pair, hands.get(1));
        assertEquals(TwoPairs, hands.get(2));
        assertEquals(ThreeOfAKind, hands.get(3));
        assertEquals(Straight, hands.get(4));
        assertEquals(Flush, hands.get(5));
        assertEquals(FullHouse, hands.get(6));
        assertEquals(FourOfAKind, hands.get(7));
        assertEquals(StraightFlush, hands.get(8));
        assertEquals(RoyalFlush, hands.get(9));
    }

}
