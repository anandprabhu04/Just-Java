package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // Variables with global scope - can be accessed anywhere inside this class
    // Initial number of Coffee and Coffee Price for a Cup
    int quantity = 1;
    int coffeePrice = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameText = (EditText) findViewById(R.id.name_field);
        String personName = nameText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        displayMessage(createOrderSummary(personName, price, hasWhippedCream, hasChocolate));

    }

    /**
     * This method increments the number of Coffee ordered
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the number of Coffee ordered
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        int totalPrice = 0;

        if (addWhippedCream) {
            totalPrice = (coffeePrice + whippedCreamPrice) * quantity;
        } else if (addChocolate) {
            totalPrice = (coffeePrice + chocolatePrice) * quantity;
        } else if (addWhippedCream && addChocolate) {
            totalPrice = (coffeePrice + whippedCreamPrice + chocolatePrice) * quantity;
        } else {
            totalPrice = coffeePrice * quantity;
        }
        return totalPrice;

    }

/*    *//**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     *//*
    private int calculatePrice(int quantity) {
        int price = quantity * coffeePrice;
        return price;
    }

    *//**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     * @param pricePerCup is the cost of one cup of coffee
     *//*
    private int calculatePrice(int quantity, int pricePerCup) {
        int price = quantity * pricePerCup;
        return price;
    }*/

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the order summary
     *
     * @param name name of the customer
     * @param price total price of the order
     * @param hasWhippedCream true or false value for adding whipped cream
     * @param hasChocolate true or false value for adding chocolate
     * @return summary of the order
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String message = "Name: " + name
                + "\nAdd Whipped Cream ? " + hasWhippedCream
                + "\nAdd Chocolate ? " + hasChocolate
                + "\nQuantity: " + quantity
                + "\nTotal: $" + price
                + "\nThank You!";
        return message;
    }
}
