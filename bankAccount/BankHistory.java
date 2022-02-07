import java.util.Scanner;

public class BankHistory {

	private String purchase;
	private double amount;
	private Date date;
	
	public BankHistory(){}
	
	public BankHistory(String purchase, double amount, Date date){
		this.purchase = purchase;
		this.amount = amount;
		this.date = date;
	}
	
	public void setPurchase() {
		Scanner sc = new Scanner(System.in);
		purchase = sc.nextLine();
	}
	public boolean setAmount() {
		Scanner sc = new Scanner(System.in);
		try {
			amount = sc.nextDouble();
			return true;
		}catch(Exception e) {
			System.out.println("Invalid entry! Please enter a number. (EX. 25000)");
			return false;
		}
	}
	public boolean setDate() {
		Scanner sc = new Scanner(System.in);
		Date date1 = null;
		try {
			String date = sc.next();
			String[] dateArray = date.split("/");
			int month = Integer.parseInt(dateArray[0]);
			int day = Integer.parseInt(dateArray[1]);
			int year = Integer.parseInt(dateArray[2]);
			date1 = new Date(month, day, year);
			if(date1.isValid() == false) {
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
	
	public String getPurchase() {
		return purchase;
	}
	public double getAmount() {
		return amount;
	}
	public Date getDate() {
		return date;
	}
	
	public String toString() {
		return purchase + "      $" + amount + "      " + date;
	}
}
