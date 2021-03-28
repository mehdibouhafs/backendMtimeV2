package ma.munisys.model;

public class DureeActiviteMois {
	
	private Double duration; 
	private String typeActivite;
	private Integer month;
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getTypeActivite() {
		return typeActivite;
	}
	public void setTypeActivite(String typeActivite) {
		this.typeActivite = typeActivite;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public DureeActiviteMois(Double duration, String typeActivite, Integer month) {
		super();
		this.duration = duration;
		this.typeActivite = typeActivite;
		this.month = month;
	}
	
	
	
	
	

}
