package ru.ex0r.example.ranging;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {

    private final static int SIZE_CARDS = 5;

    private final List<PokerCard> PokerCards = new ArrayList<>();
    private Set<Integer> distinctValues;
    private Set<Integer> repeatingValues;
    private int Size;
    private final String handString;

    public PokerHand(String handString) {
        this.handString = handString;
    }

    private void fillPokerCards(String[] cards){

        try {
            if (cards.length != SIZE_CARDS)
                throw new Exception("Number of pokerCards [" + handString + "] is not 5!");
        }catch (Exception e){
            e.printStackTrace();
        }

        for (String card : cards)
            PokerCards.add(PokerCard.parseCard(card));
    }

    private void setCardValuesToHashSets(){

        distinctValues = new HashSet<>();
        repeatingValues = new HashSet<>();

        for (PokerCard pokerCard : PokerCards) {

            int tempValue = pokerCard.cardValue.getValue();

            if(distinctValues.contains(tempValue))
                repeatingValues.add(tempValue);
            else
                distinctValues.add(tempValue);
        }
    }

    public List<PokerCard> getPokerCards() {
        return PokerCards;
    }

    public int getSize() {
        return Size;
    }

    private void sortPokerCards() {
        Collections.sort(PokerCards, Comparator.comparingInt(card -> card.cardValue.getValue()));
    }

    private String[] parseHandString(String handString) {
        return handString.split(" ");
    }

    @Override
    public String toString() {
        return "PokerHand{" + "PokerCards=" + PokerCards + '}';
    }

    private Set<Integer> getDistinctValues() {
        return distinctValues;
    }

    private Set<Integer> getRepeatingValues() {
        return repeatingValues;
    }

    public PokerHandRankings getCombination() {

        fillPokerCards(parseHandString(handString));

        Size = PokerCards.size();

        sortPokerCards();

        setCardValuesToHashSets();

        if (isFlush()){
            if(isStraight()){
                if(PokerCards.get(0).cardValue.getValue() == PokerCard.CardValue.TEN.getValue())
                    return PokerHandRankings.ROYALFLUSH;
                else
                    return PokerHandRankings.STRAIGHTFLUSH;
            }
            return PokerHandRankings.FLUSH;
        }
        else {
            if(isFourOfAKind())
                return PokerHandRankings.FOUROFAKIND;

            if(isFullHouse())
                return PokerHandRankings.FULLHOUSE;

            if(isStraight())
                return PokerHandRankings.STRAIGHT;

            if(isThreeOfAKind())
                return PokerHandRankings.THREEOFAKIND;

            if(isTwoPairs())
                return PokerHandRankings.TWOPAIRS;

            if(isPair())
                return PokerHandRankings.PAIR;
        }
        return PokerHandRankings.HIGHCARD;
    }

    private boolean CheckingSizeHashSets(int sizeOfDistinctValues, int sizeOfRepeatingValues){
        return (distinctValues.size() == sizeOfDistinctValues) &&
                (repeatingValues.size() == sizeOfRepeatingValues);
    }

    private boolean isFourOfAKind(){
        return CheckingSizeHashSets(2,1);
    }

    private boolean isFullHouse(){
        return CheckingSizeHashSets(2,2);
    }

    private boolean isThreeOfAKind(){
        return CheckingSizeHashSets(3,1);
    }

    private boolean isTwoPairs(){
        return CheckingSizeHashSets(3,2);
    }

    private boolean isPair(){
        return CheckingSizeHashSets(4,1);
    }

    private boolean isHighCard(){
        return CheckingSizeHashSets(5,0);
    }

    private boolean isStraight() {

        int tempValue = PokerCards.get(0).cardValue.getValue() + 1;

        for (int numbCard = 1; numbCard < PokerCards.size() - 1; numbCard++) {
            if(PokerCards.get(numbCard).cardValue.getValue() != tempValue)
                return false;
            tempValue++;
        }

        int diffAceToTwo = PokerCard.CardValue.ACE.getValue() - PokerCard.CardValue.TWO.getValue();
        int valueLastCard = PokerCards.get(PokerCards.size() - 1).cardValue.getValue();

        if(valueLastCard != tempValue && valueLastCard - PokerCards.get(0).cardValue.getValue() != diffAceToTwo)
            return false;

        return true;
    }

    private boolean isFlush() {

        String firstCard = PokerCards.get(0).cardSuit.getCode();

        for (PokerCard pokerCard : PokerCards) {
            if(!firstCard.equals(pokerCard.cardSuit.getCode()))
                return false;
        }
        return true;
    }

    private int defaultComparing(int thisVal, int thatVal){

        if(thisVal > thatVal){
            return 1;
        }else if (thisVal < thatVal){
            return -1;
        }else
            return 0;
    }

    private int highCardComparing(PokerHand that){

        int thisLastCardValue = this.getPokerCards().get(this.getSize() - 1).cardValue.getValue();
        int thatLastCardValue = that.getPokerCards().get(that.getSize() - 1).cardValue.getValue();

        if(thisLastCardValue > thatLastCardValue){
            return 1;
        }else if (thisLastCardValue < thatLastCardValue){
            return -1;
        }else{
            int thisPrevLastCardValue = this.getPokerCards().get(this.getSize() - 2).cardValue.getValue();
            int thatPrevLastCardValue = that.getPokerCards().get(that.getSize() - 2).cardValue.getValue();

            return defaultComparing(thisPrevLastCardValue, thatPrevLastCardValue);
        }
    }

    private int getHighCard(PokerHand that, int thisFirstVal, int thatFirstVal){

        int thisTempValue = this.getPokerCards().get(this.getSize() - 1).cardValue.getValue();
        int tempIndex = this.getSize() - 1;

        while(thisTempValue == thisFirstVal){
            thisTempValue = this.getPokerCards().get(tempIndex).cardValue.getValue();
            tempIndex--;
        }

        int thatTempValue = that.getPokerCards().get(that.getSize() - 1).cardValue.getValue();
        tempIndex = that.getSize() - 1;

        while(thatTempValue == thatFirstVal){
            thatTempValue = that.getPokerCards().get(tempIndex).cardValue.getValue();
            tempIndex--;
        }

        return defaultComparing(thisTempValue, thatTempValue);
    }

    private int getHighCard(PokerHand that,
                            int thisFirstVal, int thatFirstVal,
                            int thisSecondVal, int thatSecondVal){

        int thisTempValue = this.getPokerCards().get(this.getSize() - 1).cardValue.getValue();
        int tempIndex = this.getSize() - 1;

        while(thisTempValue == thisFirstVal || thisTempValue == thisSecondVal){
            thisTempValue = this.getPokerCards().get(tempIndex).cardValue.getValue();
            tempIndex--;
        }

        int thatTempValue = that.getPokerCards().get(that.getSize() - 1).cardValue.getValue();
        tempIndex = that.getSize() - 1;

        while(thatTempValue == thatFirstVal || thatTempValue == thatSecondVal){
            thatTempValue = that.getPokerCards().get(tempIndex).cardValue.getValue();
            tempIndex--;
        }

        return defaultComparing(thisTempValue, thatTempValue);
    }

    private int straightComparing(PokerHand that){

        int thisFirstCardValue = this.getPokerCards().get(0).cardValue.getValue();
        int thisLastCardValue = this.getPokerCards().get(this.getSize() - 1).cardValue.getValue();

        int thatFirstCardValue = that.getPokerCards().get(0).cardValue.getValue();
        int thatLastCardValue = that.getPokerCards().get(that.getSize() - 1).cardValue.getValue();

        int thisComparingVal, thatComparingVal;

        if(thisFirstCardValue == PokerCard.CardValue.TWO.getValue() &&
                thisLastCardValue == PokerCard.CardValue.ACE.getValue())
            thisComparingVal = 0;
        else
            thisComparingVal = thisFirstCardValue;

        if(thatFirstCardValue == PokerCard.CardValue.TWO.getValue() &&
                thatLastCardValue == PokerCard.CardValue.ACE.getValue())
            thatComparingVal = 0;
        else
            thatComparingVal = thatFirstCardValue;

        return defaultComparing(thisComparingVal, thatComparingVal);
    }

    private int fullHouseComparing(PokerHand that){

        int thisThirdValue = this.getPokerCards().get(2).cardValue.getValue();
        int thisLastValue = this.getPokerCards().get(this.getSize() - 1).cardValue.getValue();

        int thisValOfThree, thisValOfPair;

        if(thisThirdValue == thisLastValue){
            thisValOfThree = thisLastValue;
            thisValOfPair = this.getPokerCards().get(0).cardValue.getValue();
        }
        else{
            thisValOfThree = this.getPokerCards().get(0).cardValue.getValue();
            thisValOfPair = thisLastValue;
        }

        int thatValOfThree, thatValOfPair;

        int thatThirdValue = that.getPokerCards().get(2).cardValue.getValue();
        int thatLastValue = that.getPokerCards().get(that.getSize() - 1).cardValue.getValue();

        if(thatThirdValue == thatLastValue){
            thatValOfThree = thatLastValue;
            thatValOfPair = that.getPokerCards().get(0).cardValue.getValue();
        }
        else{
            thatValOfThree = that.getPokerCards().get(0).cardValue.getValue();
            thatValOfPair = thatLastValue;
        }

        if (thisValOfThree > thatValOfThree) {
            return 1;
        } else if (thisValOfThree < thatValOfThree) {
            return -1;
        } else {
            if (thisValOfPair > thatValOfPair) {
                return 1;
            } else if (thisValOfPair < thatValOfPair) {
                return -1;
            } else
                return 0;
        }
    }

    private int twoPairsComparing(PokerHand that){

        Iterator<Integer> thisIterRepeatVal = this.getRepeatingValues().iterator();
        Iterator<Integer> thatIterRepeatVal = that.getRepeatingValues().iterator();

        int thisFirstVal = thisIterRepeatVal.next();
        int thatFirstVal = thatIterRepeatVal.next();

        int thisSecondVal = thisIterRepeatVal.next();
        int thatSecondVal = thatIterRepeatVal.next();

        if (thisSecondVal > thatSecondVal) {
            return 1;
        } else if (thisSecondVal < thatSecondVal) {
            return -1;
        } else {
            if(thisFirstVal > thatFirstVal){
                return 1;
            }else if (thisFirstVal < thatFirstVal){
                return -1;
            }else
                return getHighCard(that, thisFirstVal, thatFirstVal, thisSecondVal, thatSecondVal);
        }
    }

    private int algorithmComparing(PokerHand that, int thisFirstVal, int thatFirstVal){

        if(thisFirstVal > thatFirstVal){
            return 1;
        }else if (thisFirstVal < thatFirstVal){
            return -1;
        }else
            return getHighCard(that, thisFirstVal, thatFirstVal);
    }

    private int pairComparing(PokerHand that){

        Iterator<Integer> thisIterRepeatVal = this.getRepeatingValues().iterator();
        Iterator<Integer> thatIterRepeatVal = that.getRepeatingValues().iterator();

        int thisFirstVal = thisIterRepeatVal.next();
        int thatFirstVal = thatIterRepeatVal.next();

        return algorithmComparing(that, thisFirstVal, thatFirstVal);
    }

    private int threeOfAKindComparing(PokerHand that){

        Iterator<Integer> thisIterRepeatVal = this.getRepeatingValues().iterator();
        Iterator<Integer> thatIterRepeatVal = that.getRepeatingValues().iterator();

        int thisFirstVal = thisIterRepeatVal.next();
        int thatFirstVal = thatIterRepeatVal.next();

        return algorithmComparing(that, thisFirstVal, thatFirstVal);
    }

    private int fourOfAKindComparing(PokerHand that){

        Iterator<Integer> thisIterRepeatVal = this.getRepeatingValues().iterator();
        Iterator<Integer> thatIterRepeatVal = that.getRepeatingValues().iterator();

        int thisFirstVal = thisIterRepeatVal.next();
        int thatFirstVal = thatIterRepeatVal.next();

        return algorithmComparing(that, thisFirstVal, thatFirstVal);
    }

    private int flushComparing(PokerHand that){

        int thisLastCardValue = this.getPokerCards().get(this.getSize() - 1).cardValue.getValue();
        int thatLastCardValue = that.getPokerCards().get(that.getSize() - 1).cardValue.getValue();

        return defaultComparing(thisLastCardValue, thatLastCardValue);
    }

    @Override
    public int compareTo(PokerHand that) {

        PokerHandRankings thisHandComb = this.getCombination();
        PokerHandRankings thatHandComb = that.getCombination();

        if(thisHandComb.getValue() > thatHandComb.getValue()){
            return 1;
        }
        else if(thisHandComb.getValue() < thatHandComb.getValue()){
            return -1;
        }
        else{

            if(thisHandComb.getValue() == PokerHandRankings.HIGHCARD.getValue()) {
                return highCardComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.PAIR.getValue()) {
                return pairComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.TWOPAIRS.getValue()) {
                return twoPairsComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.THREEOFAKIND.getValue()) {
                return threeOfAKindComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.STRAIGHT.getValue()){
                return straightComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.FLUSH.getValue()){
                return flushComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.FULLHOUSE.getValue()) {
                return fullHouseComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.FOUROFAKIND.getValue()) {
                return fourOfAKindComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.STRAIGHTFLUSH.getValue()){
                return straightComparing(that);
            }

            if(thisHandComb.getValue() == PokerHandRankings.ROYALFLUSH.getValue()){
                return 0;
            }
        }
        return 0;
    }


}
