package com.threaddemo;

import java.util.Scanner;

//Custom exception for insufficient balance during a withdrawal
class InsufficientBalanceException extends Exception {
 public InsufficientBalanceException(String message) {
     super(message);
 }
}

//Class representing a bank account
class Account {
 private double balance;

 // Constructor to initialize the account with an initial balance
 public Account(double initialBalance) {
     this.balance = initialBalance;
 }

 // Method to get the current balance
 public double getBalance() {
     return balance;
 }

 // Method to handle deposit operation
 public void deposit(double amount) {
     try {
         // Check if the deposit amount is valid
         if (amount <= 0) {
             throw new IllegalArgumentException("Deposit amount must be greater than zero.");
         }

         // Update the balance with the deposit amount
         balance += amount;
         System.out.println("Deposit successful. Current balance: $" + balance);
     } catch (IllegalArgumentException e) {
         // Catch and handle the exception for an invalid deposit amount
         System.out.println("Error: " + e.getMessage());
     } finally {
         // Code in the finally block will execute regardless of whether an exception occurred
         System.out.println("Deposit operation completed.");
     }
 }

 // Method to handle withdrawal operation
 public void withdraw(double amount) throws InsufficientBalanceException {
     try {
         // Check if the withdrawal amount is valid
         if (amount <= 0) {
             throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
         }

         // Check if there are sufficient funds for the withdrawal
         if (amount > balance) {
             throw new InsufficientBalanceException("Insufficient funds. Current balance: $" + balance);
         }

         // Update the balance after a successful withdrawal
         balance -= amount;
         System.out.println("Withdrawal successful. Current balance: $" + balance);
     } catch (IllegalArgumentException | InsufficientBalanceException e) {
         // Catch and handle exceptions for invalid withdrawal amounts or insufficient balance
         System.out.println("Error: " + e.getMessage());
     } finally {
         // Code in the finally block will execute regardless of whether an exception occurred
         System.out.println("Withdrawal operation completed.");
     }
 }
}





public class BankingApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);

	        // Get the initial balance from the user
	        System.out.print("Enter initial balance: $");
	        double initialBalance = scanner.nextDouble();

	        // Create an account with the initial balance
	        Account account = new Account(initialBalance);

	        // Perform a deposit operation
	        System.out.print("Enter deposit amount: $");
	        double depositAmount = scanner.nextDouble();
	        account.deposit(depositAmount);

	        // Perform a withdrawal operation with exception handling
	        System.out.print("Enter withdrawal amount: $");
	        double withdrawalAmount = scanner.nextDouble();
	        try {
	            account.withdraw(withdrawalAmount);
	        } catch (InsufficientBalanceException e) {
	            System.out.println("Unable to complete withdrawal: " + e.getMessage());
	        }

	        // Display the final balance
	        System.out.println("Final balance: $" + account.getBalance());

	        // Close the scanner
	        scanner.close();
	}

}
