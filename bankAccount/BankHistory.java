import java.util.Scanner;

public class BankHistory {

	private String purchase;
	private double amount;
	private Date date;
	private String type;
	
	public BankHistory(){}
	
	public BankHistory(String purchase, double amount, Date date){
		this.purchase = purchase;
		this.amount = Math.round(amount * 100.0) / 100.0;
		this.date = date;
	}
	
	public void setPurchase(String type) {
		Scanner sc = new Scanner(System.in);
		purchase = sc.nextLine();
		this.type = type;
	}
	public boolean setAmount() {
		Scanner sc = new Scanner(System.in);
		try {
			amount = Math.round(sc.nextDouble() * 100.0) / 100.0;
			if(amount <= 0) {
				System.out.println("Please enter a number greater than 0!");
				return false;
			}
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
		String spacesP = " ";
		String spacesA = " ";
		String amountString = amount + "";
		for(int i = 0; i < 25; i++) {
			if(!(i < purchase.length())) {
				spacesP += " ";
			}
		}
		
		for(int i = 0; i < 25; i++) {
			if(!(i < amountString.length())) {
				spacesA += " ";
			}
		}
		
		
		
		if(type.equals("P")) {
			return purchase + spacesP + "-$" + amount + spacesA + date;
		}
		return purchase + spacesP + "+$" + amount + spacesA + date;
		
	}
}
