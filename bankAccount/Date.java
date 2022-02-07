/**
 * Date class:
 * This class takes in a Date and checks if the Date is valid then returns the Date in a user friendly way. This class checks for leap years as well.
 *
 * @author Nour Ali
 */
public class Date {

	private int month;
	private int day;
	private int year;
	private boolean valid = true;
	/**
	 * Pre: takes ints month, day, and year as parameters
	 * Post: sets the month day and year.
	 * @param month
	 * @param day
	 * @param year
	 */
	public Date(int month, int day, int year) {
		setMonth(month);
		setYear(year);
		setDay(day);
	}
	/**
	 * Pre: takes object date as parameter
	 * Post: sets the month day and year (copy constructor).
	 * @param date
	 */
	public Date(Date date) {
		setMonth(date.month);
		setYear(date.year);
		setDay(date.day);
	}
	/**
	 * Pre: Month is initialized.
	 * Post: returns the month
	 * @return int
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * Pre: day is initialized.
	 * Post: returns the day
	 * @return int
	 */
	public int getDay() {
		return day;
	}
	/**
	 * Pre: year is initialized.
	 * Post: returns the year
	 * @return int
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Pre: int month as parameter.
	 * Post: sets the month
	 * @param month
	 */
	public void setMonth(int month) {
		if(month > 0 && month < 13) {//if month is greater than 0 and less than 13 month will be set
			this.month = month;
		}
		else{
			valid = false;
		}
	}
	/**
	 * Pre: int day as parameter.
	 * Post: sets the day
	 * @param day
	 */
	public void setDay(int day) {
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ) {//checks if month has 31 days
			if(day <= 31 && day > 0) {//checks if day is valid for specific month
				this.day = day;//sets day
			}else {
				valid = false;
			}
		}else if(month == 4 || month == 6 || month == 9 || month == 11) {//checks if month has 30 days.
			if(day <= 30 && day > 0) {//checks if day is valid for month
				this.day = day;//sets day.
			}else {
				valid = false;
			}
		}else if(month == 2 && ((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0)) {//checks if leap year
			if(day <= 29 && day > 0) {//if leap year then checks if day is valid for February. 
				this.day = day;//sets day
			}else {
				valid = false;
			}
		}else if(month == 2) {//if not leap year
			if(day <= 28 && day > 0) {
				this.day = day;//sets day if day is valid for specific month.
			}else {
				valid = false;
			}
		}else {
			valid = false;
		}
		
	}
	/**
	 * Pre: int year as parameter.
	 * Post: sets the year 
	 * @param year
	 */
	public void setYear(int year) {
		if(year >= 2000 && year <= 2050) {//checks if year is within allowed years
			this.year = year;//sets year if valid
		}else {
			System.out.println("Invalid Year! Year must be greater than 1999 and less than 2051!");
			valid = false;
		}
	}
	/**
	 * This method checks if the date is valid.
	 * @return boolean
	 */
	public boolean isValid() {
		if(!valid) {
			System.out.println("Invalid Date!");
		}
		return valid;
	}
	
	/**
	 * Pre: Object date as parameter
	 * Post: checks if 2 objects are equal.
	 * @param date
	 * @return boolean
	 * @override
	 */
	public boolean equals(Date date) {
		if (date == null) {//if object date is empty returns false.
			return false;
		}
		if(this.getMonth() == date.getMonth() && this.getDay() == date.getDay() && this.getYear() == date.getYear()) {//if all the primitive types are equal returns true.
			return true;
		}
		return false;
	}
	/**
	 * Pre: month, day, and year are initialized.
	 * Post: returns the date in a user friendly way.
	 * @override
	 */
	public String toString() {
		if(!valid) {//if month is invalid an error message will be displayed.
			return "Invalid Date";
		}
		return month + "/" + day + "/" + year;
	}
}
