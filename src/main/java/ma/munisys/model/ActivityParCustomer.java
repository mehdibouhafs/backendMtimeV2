package ma.munisys.model;

public class ActivityParCustomer {
	
	private String name;
	private long nbreActivities;
	
	public ActivityParCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ActivityParCustomer(String name, long nbreActivities) {
		super();
		this.name = name;
		this.nbreActivities = nbreActivities;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNbreActivities() {
		return nbreActivities;
	}
	public void setNbreActivities(long nbreActivities) {
		this.nbreActivities = nbreActivities;
	}
	
	
	
}
