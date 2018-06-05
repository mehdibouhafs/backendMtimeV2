package ma.munisys.model;

public class DurtionEstimeParMonth {

	int month;
	int year;
	double durtion;

	public DurtionEstimeParMonth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DurtionEstimeParMonth(int month, int year, double durtion) {
		super();
		this.month = month;
		this.year = year;
		this.durtion = durtion;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDurtion() {
		return durtion;
	}

	public void setDurtion(double durtion) {
		this.durtion = durtion;
	}

}
