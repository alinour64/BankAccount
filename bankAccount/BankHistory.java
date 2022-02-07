import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * This class works with every single purchased item as well as income.
 * @author Nour Ali
 *
 */
public class BankHistory {

	private String purchase;
	private double amount;
	private Date date;
	private String type;
	
	public BankHistory(){}
	/**
	 * Constructor that sets the purchase, amount, and date
	 * @param purchase
	 * @param amount
	 * @param date
	 */
	public BankHistory(String purchase, double amount, Date date){
		this.purchase = purchase;
		this.amount = Math.round(amount * 100.0) / 100.0;//rounds amount to nearest cent.
		this.date = date;
	}
	/**
	 * This method sets the purchase type as well as which purchase/income is being added.
	 * @param type
	 */
	public void setPurchase(String type) {
		Scanner sc = new Scanner(System.in);
		purchase = sc.nextLine();
		this.type = type;
	}
	/**
	 * This method sets the amount in this class.
	 * @return boolean
	 */
	public boolean setAmount() {
		Scanner sc = new Scanner(System.in);
		try {
			amount = Math.round(sc.nextDouble() * 100.0) / 100.0;//rounds to the nearest cent.
			if(amount <= 0) {//checks for class invariant (amount has to be greater than 0).
				System.out.println("Please enter a number greater than 0!");
				return false;
			}
			return true;
		}catch(Exception e) {
			System.out.println("Invalid entry! Please enter a number. (EX. 25000)");
			return false;
		}
	}
	/**
	 * this method checks if the date is valid by accessing the Date class.
	 * @return boolean
	 */
	public boolean setDate() {
		Scanner sc = new Scanner(System.in);
		Date date1 = null;
		try {
			String date = sc.next();
			String[] dateArray = date.split("/");//split the date into 3 ints. (Month, day, year)
			int month = Integer.parseInt(dateArray[0]);
			int day = Integer.parseInt(dateArray[1]);
			int year = Integer.parseInt(dateArray[2]);
			date1 = new Date(month, day, year);//create new date object to validate if the date is correct accounting for leap years.
			if(date1.isValid() == false) {//if date isn't valid user will be prompted to try again.
				System.out.println("Please enter a date. (EX. MM/DD/YYYY)");
				return false;
			}
			this.date = date1;
			return true;
		}catch(Exception e) {
			System.out.println("Invalid entry! Please enter a date. (EX. MM/DD/YYYY)");
			return false;
		}
	}
	/**
	 * This method is used to get the purchase item.
	 * @return String
	 */
	public String getPurchase() {
		return purchase;
	}
	/**
	 * This method is used to get the amount.
	 * @return double
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * This method is used to get the date.
	 * @return Date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * This method formats and converts the bank history to a string in a user friendly way.
	 * @return String
	 * @Override
	 */
	public String toString() {
		DecimalFormat format = new DecimalFormat("#,###.00");
		
		String spacesP = " ";
		String spacesA = " ";
		String amountString = format.format(amount);//format amount to include commas after every 3 digits.
		for(int i = 0; i < 25; i++) {//this for loop helps with aligning and centering the information in the text file.
			if(!(i < purchase.length())) {//this for loop sees how long the purchase item is and adds spaces for the rest of the characters.
				spacesP += " ";
			}
		}
		
		for(int i = 0; i < 25; i++) {//this for loop helps with aligning and centering the information in the text file.
			if(!(i < amountString.length())) {//this for loop sees how long the amount item is and adds spaces for the rest of the characters.
				spacesA += " ";
			}
		}
		
		
		
		if(type.equals("P")) {
			return purchase + spacesP + "-$" + amountString + spacesA + date;//if purchase this string will be returned
		}
		return purchase + spacesP + "+$" + format.format(amount) + spacesA + date;//if income this string will be returned
		
	}
}
