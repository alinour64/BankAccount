import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program is a simple banking system that takes in purchases and income and displays them in a user friendly way in a text file.
 * @author Nour Ali
 *
 */
public class BankAccountDriver {
	private static double balance = 0.0;
	private static ArrayList<String> account = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Scanner input = null;
		
		try {
			input = new Scanner(new FileInputStream("BankAccount.txt"));
			while(input.hasNextLine()) {//reads every line in the text file and stores it in an array list.
				account.add(input.nextLine());
			}
			balance = Double.parseDouble(account.get(1).substring(1).replace(",", ""));//reads from the file and sets balance = to the existing balance.
			account.set(1, "$" + balance);//stores the total balance at index 1 of the array list
			input.close();
			print();
			incomePurchase();
		}catch (IOException e) {
			createAccount();//if there is no existing file a new account will be created.
		}
		boolean transaction = false;
		while(!transaction) {//asks to see if there are more transactions the user would like to add.
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
			output = new PrintWriter(new FileOutputStream("BankAccount.txt"));//reset file
			for(int i = 0; i < account.size(); i++) {//print every thing in the array list including updated balance as well as added income and purchases.
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
			output = new PrintWriter(new FileOutputStream("BankAccount.txt"));//creates a new text file

			account.add("Bank Account Balance:");
			account.add("$" + balance);//sets balance at $0
			account.add("-----------------------");
			System.out.println("Welcome to your bank account!");
			incomePurchase();
		}catch(IOException e){
			System.out.println("Error: Can't locate file!");
			System.exit(0);
		}
		output.close(); 
	}
	
	/**
	 * Checks if the user would like to add income or purchases and redirects them to the correct method.
	 */
	public static void incomePurchase() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to make a purchase or deposit income? (P for purchase and I for income)");
		boolean valid = false;
		while(!valid) {
			String pOrI = sc.next();
			if(pOrI.toLowerCase().equals("i")) {
				valid = true;
				addIncome();//if user wants to add income they will be redirected to so they can add income
			}
			else if(pOrI.toLowerCase().equals("p")) {
				valid = true;
				addPurchase();//if user wants to purchase an item they will be redirected to purchase items
			}else {
				valid = false;
				System.out.println("Invalid input. Please try again. (P for purchase and I for income)");
				sc.nextLine();
			}
		}
	}
	/**
	 * This method is similar to the purchases method but instead of deducting money it adds money to the total balance.
	 */
	public static void addIncome() {
		DecimalFormat format = new DecimalFormat("#,###.00");
		
		Scanner sc = new Scanner(System.in);
		BankHistory item = new BankHistory();
		boolean valid = false;
		System.out.println("Where did this income come from?");
		item.setPurchase("I");//gets user input as well as checks for class invariants. 
		
		System.out.println("How much was your Income? (Ex. 25000)");
		do {
			valid = item.setAmount();//if the amount is invalid the user will be prompted to enter another input.
		}while(!valid);
		System.out.println("On what date did you get this income? (MM/DD/YYYY)");
		do {
			valid = item.setDate();//if the date is invalid the user will be prompted to enter another input.
			
		}while(!valid);
		balance += item.getAmount();
		account.set(1, "$" + format.format(balance));//formats balance
		account.add(item.toString());//adds the income history to the string so it can be outputed to the text file.
		System.out.println("You now have $" + format.format(balance) + " in your account!");//displays the total balance after adding income in the console.
		print();//outputs everything to the text file.
	}
	/**
	 * This method is similar to the income method but instead of adding money it deducts money to the total balance.
	 */
	public static void addPurchase() {
		DecimalFormat format = new DecimalFormat("#,###.00");
		Scanner sc = new Scanner(System.in);
		BankHistory item = new BankHistory();
		boolean valid = false;
		System.out.println("What did you purchase?");
		item.setPurchase("P");//gets user input as well as checks for class invariants.
		
		System.out.println("How much was your purchase? (Ex. 25000)");
		do {
			valid = item.setAmount();//if user enters an invalid input it prompts the user to re-enter
		}while(!valid);
		System.out.println("On what date did you purchase this? (MM/DD/YYYY)");
		do {
			valid = item.setDate();//if user enters an invalid input it prompts the user to re-enter
			
		}while(!valid);
		balance -= item.getAmount();//subtracts from balance
		if(balance >= 0) {//checks if balance is still greater than or equal to 0
			account.set(1, "$" + format.format(balance));//formats balance and sets balance to index 1 of array list to be outputed to the text file.
			account.add(item.toString());//adds the purchase history in a user friendly way to the array list.
		}else {
			System.out.println("Sorry you do not have enough money! You need $" + balance*-1 + " more in order to make this purchase!");
			balance += item.getAmount();//if balance is negative after purchase it will prevent the user from making the purchase.
		}
		System.out.println("You now have $" + format.format(balance) + " in your account!");//displays total money after purchase in the console
		print();//outputs everything to the text file.
	}

}
