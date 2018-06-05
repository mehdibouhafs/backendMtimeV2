package ma.munisys.model;

public class ActivityRealisedParMonth {

	private int month;
	private int year;
	private long nbreActivityRealised;

	public ActivityRealisedParMonth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityRealisedParMonth(int month, int year, long nbreActivityRealised) {
		super();
		this.month = month;
		this.year = year;
		this.nbreActivityRealised = nbreActivityRealised;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public long getNbreActivityRealised() {
		return nbreActivityRealised;
	}

	public void setNbreActivityRealised(long nbreActivityRealised) {
		this.nbreActivityRealised = nbreActivityRealised;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
