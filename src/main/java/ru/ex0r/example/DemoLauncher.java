package ru.ex0r.example;

import ru.ex0r.example.ranging.PokerHand;
import java.util.ArrayList;

public class DemoLauncher {

    public static void main(String[] args) {

        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("AC 6C 3C 4C 2C"));
        hands.add(new PokerHand("JC 5H JD JH 5C"));

        Collections.sort(hands, Collections.reverseOrder());

        for(PokerHand pokerHand : hands)
            System.out.println(pokerHand.getCombination() + "\n" + pokerHand  + "\n\n");

        Collections.sort(hands);

        for(PokerHand pokerHand : hands)
            System.out.println(pokerHand.getCombination() + "\n" + pokerHand  + "\n\n");


        PokerHand hand = new PokerHand("R 2H 5C JD TD");
        System.out.println(hand.getCombination());

    }
}
