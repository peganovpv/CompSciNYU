package src;
import java.util.*;

class Stock {
    int quantity;
    int price;

    public Stock(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }
}

class StockTransactions {
    Queue<Stock> fifoQueue = new LinkedList<>();
    Stack<Stock> lifoStack = new Stack<>();

    public void buy(int quantity, int price) {
        Stock stock = new Stock(quantity, price);
        fifoQueue.add(stock);
        lifoStack.push(stock);
    }

    public void sellFifo(int quantity, int price) {
        int gain = 0;
        while (quantity > 0) {
            Stock stock = fifoQueue.peek();
            int sellQuantity = Math.min(stock.quantity, quantity);
            gain += sellQuantity * (price - stock.price);
            stock.quantity -= sellQuantity;
            if (stock.quantity == 0) fifoQueue.poll();
            quantity -= sellQuantity;
        }
        System.out.println("FIFO Capital Gain: " + gain);
    }

    public void sellLifo(int quantity, int price) {
        int gain = 0;
        while (quantity > 0) {
            Stock stock = lifoStack.peek();
            int sellQuantity = Math.min(stock.quantity, quantity);
            gain += sellQuantity * (price - stock.price);
            stock.quantity -= sellQuantity;
            if (stock.quantity == 0) lifoStack.pop();
            quantity -= sellQuantity;
        }
        System.out.println("LIFO Capital Gain: " + gain);
    }
}

public class QueueRecitation {
    public static void main(String[] args) {
        StockTransactions stockTransactions = new StockTransactions();
        /*
         * Test Data:
            B,30,100
            B,40,110
            S,50, 120
            S,20,125
         */
        stockTransactions.buy(30, 100);
        stockTransactions.buy(40, 110);
        stockTransactions.sellFifo(50, 120);
        stockTransactions.sellFifo(20, 125);
    }
}