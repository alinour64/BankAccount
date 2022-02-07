import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountDriver {
	private static double balance = 0.0;
	private static ArrayList<String> account = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Scanner input = null;
		
		try {
			input = new Scanner(new FileInputStream("BankAccount.txt"));
			while(input.hasNextLine()) {
				account.add(input.nextLine());
			}
			balance = Double.parseDouble(account.get(1).substring(1).replace(",", ""));
			account.set(1, "$" + balance);
			input.close();
			print();
			incomePurchase();
		}catch (IOException e) {
			createAccount();
		}
		boolean transaction = false;
		while(!transaction) {
			System.out.println("If you would like to make another transaction please press 'Y'. Otherwise press any key.");
			String again = sc.next();
			if(again.toLowerCase().equals("y")) {
				incomePurchase();
			}else {
				transaction = true;
				System.out.println("GoodBye!");
			}
		}
		
	}
	/**
	 * Prints every transaction to the text file as well as updating the bank account balance.
	 */
	public static void print() {
		
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream("BankAccount.txt"));
			for(int i = 0; i < account.size(); i++) {
				output.println(account.get(i));
			}
			
		}catch(IOException e){
			System.out.println("Error: Can't locate file!");
			System.exit(0);
		}
		output.close(); 
		
		
	}
	/**
	 * If the account doesn't exist a new account will be created
	 */
	public static void createAccount() {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream("BankAccount.txt"));

			account.add("Bank Account Balance:");
			account.add("$" + balance);
			account.add("-----------------------");
			System.out.println("Welcome to your bank account!");
			incomePurchase();
		}catch(IOException e){
			System.out.println("Error: Can't locate file!");
			System.exit(0);
		}
		output.close(); 
	}
	
	public static void incomePurchase() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to make a purchase or deposit income? (P for purchase and I for income)");
		boolean valid = false;
		while(!valid) {
			String pOrI = sc.next();
			if(pOrI.toLowerCase().equals("i")) {
				valid = true;
				addIncome();
			}
			else if(pOrI.toLowerCase().equals("p")) {
				valid = true;
				addingToBankHistory();
			}else {
				valid = false;
				System.out.println("Invalid input. Please try again. (P for purchase and I for income)");
				sc.nextLine();
			}
		}
	}
	
	public static void addIncome() {
		DecimalFormat format = new DecimalFormat("#,###.00");
		
		Scanner sc = new Scanner(System.in);
		BankHistory item = new BankHistory();
		boolean valid = false;
		System.out.println("Where did this income come from?");
		item.setPurchase("I");
		
		System.out.println("How much was your Income? (Ex. 25000)");
		do {
			valid = item.setAmount();
		}while(!valid);
		
		
		
		System.out.println("On what date did you get this income? (MM/DD/YYYY)");
		
		do {
			valid = item.setDate();
			
		}while(!valid);
		balance += item.getAmount();
		account.set(1, "$" + format.format(balance));
		account.add(item.toString());
		System.out.println("You now have $" + format.format(balance) + " in your account!");
		print();
	}
	
	public static void addingToBankHistory() {
		DecimalFormat format = new DecimalFormat("#,###.00");
		Scanner sc = new Scanner(System.in);
		BankHistory item = new BankHistory();
		boolean valid = false;
		System.out.println("What did you purchase?");
		item.setPurchase("P");
		
		System.out.println("How much was your purchase? (Ex. 25000)");
		do {
			valid = item.setAmount();
		}while(!valid);
		
		
		
		System.out.println("On what date did you purchase this? (MM/DD/YYYY)");
		
		do {
			valid = item.setDate();
			
		}while(!valid);
		balance -= item.getAmount();
		if(balance >= 0) {
			account.set(1, "$" + format.format(balance));
			account.add(item.toString());
		}else {
			System.out.println("Sorry you do not have enough money! You need $" + balance*-1 + " more in order to make this purchase!");
			balance += item.getAmount();
		}
		System.out.println("You now have $" + format.format(balance) + " in your account!");
		print();
	}

}
