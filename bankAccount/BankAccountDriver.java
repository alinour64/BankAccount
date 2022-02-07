import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountDriver {

	private static double balance = 0.0;
	private static ArrayList<String> account = new ArrayList();
	
	public static void main(String[] args) {
		
		Scanner input = null;
		
		try {
			input = new Scanner(new FileInputStream("BankAccount.txt"));
			while(input.hasNextLine()) {
				account.add(input.nextLine());
			}
			balance = Double.parseDouble(account.get(1).substring(1));
			account.set(1, "$" + balance);
			input.close();
			print();
			addingToBankHistory();
		}catch (IOException e) {
			createAccount();
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
			output.println("Bank Account Balance:");
			output.println("$" + balance);
			System.out.println("Welcome to your bank account!");
			addingToBankHistory();
		}catch(IOException e){
			System.out.println("Error: Can't locate file!");
			System.exit(0);
		}
		output.close(); 
	}
	
	public static void addingToBankHistory() {
		Scanner sc = new Scanner(System.in);
		BankHistory item = new BankHistory();
		boolean valid = false;
		System.out.println("What did you purchase?");
		item.setPurchase();
		
		System.out.println("How much was your purchase?");
		do {
			valid = item.setAmount();
		}while(!valid);
		
		
		
		System.out.println("On what date did you purchase this? (MM/DD/YYYY)");
		
		do {
			valid = item.setDate();
			
		}while(!valid);
		
		account.add(item.toString());
		print();
	}

}
