package edu.nyu.cs;
import java.util.*;
/**
 * A variation of the game of Blackjack.  
 * Complete this program according to the instructions in the README.md file as well as within the given comments below.
 */
public class Blackjack {

  /**
   * The main function is automatically called first in a Java program.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int userTotal = 0;
        int dealerTotal = 0;
        List<Integer> userCards = new ArrayList<>();
        List<Integer> dealerCards = new ArrayList<>();
        ArrayList<Integer> cards = new ArrayList<>();
          for(int i = 2; i < 12; i++){
              cards.add(i);
          }
        ArrayList<String> dealerChoices = new ArrayList<String>();
        dealerChoices.add("hits");
        dealerChoices.add("stands");
        
        for (int i = 0; i < 2; i++) {
            int card = cards.get(random.nextInt(cards.size()));
            userTotal += card;
            userCards.add(card);
        }
        System.out.println("Welcome to Blackjack!");
        System.out.println("Your cards are: " + userCards.get(0) + " and " + userCards.get(1));
        
        for (int i = 0; i < 2; i++) {
            int card = cards.get(random.nextInt(cards.size()));
            dealerTotal += card;
            dealerCards.add(card);
        }
        
        while (true) {
            System.out.print("Would you like to hit or stand? ");
            String response = scanner.nextLine();
            if (response.equals("hit")) {
                int card = cards.get(random.nextInt(cards.size()));
                userTotal += card;
                userCards.add(card);
                System.out.println("Your cards are: " + userCards);
                if (userTotal > 21) {
                    System.out.println("You have bust!");
                    System.out.println(userTotal);
                    System.out.println("Dealer wins!");
                    return;
                }
            } else if (response.equals("stand") || response.equals("stop") || response.equals("pass")) {
                break;
            } else {
                System.out.println("Invalid response. Please enter 'hit' or 'stand'.");
            }
        }
        
        while (true) {
            String dealerChoice = dealerChoices.get(random.nextInt(dealerChoices.size()));
            if (dealerChoice.equals("stands")) {
                System.out.println("The dealer stands");
                break;
            }
            else if (dealerChoice.equals("hits")){
                int card = cards.get(random.nextInt(cards.size()));
                dealerTotal += card;
                dealerCards.add(card);
                System.out.println("The dealer hits");
                if (dealerTotal > 21) {
                    System.out.println("Your cards are: " + userCards);
                    System.out.println("The dealer's cards are: " + dealerCards);
                    System.out.println("The dealer has bust!");
                    System.out.println(dealerTotal);
                    System.out.println("You win!");
                return;
                }
            }
        }
        
        
        if (userTotal > dealerTotal) {
            System.out.println("Your cards are: " + userCards);
            System.out.println("The dealer's cards are: " + dealerCards);
            System.out.println("You win!");
            System.out.println("userTotal: " + userTotal);
            System.out.println("dealerTotal: " + dealerTotal);
        } else if (userTotal < dealerTotal) {
            System.out.println("Your cards are: " + userCards);
            System.out.println("The dealer's cards are: " + dealerCards);
            System.out.println("Dealer wins!");
            System.out.println("userTotal: " + userTotal);
            System.out.println("dealerTotal: " + dealerTotal);
        } else {
            System.out.println("Your cards are: " + userCards);
            System.out.println("The dealer's cards are: " + dealerCards);
            System.out.println("Tie!");
            System.out.println("userTotal: " + userTotal);
            System.out.println("dealerTotal: " + dealerTotal);
        }
        }
    

}
