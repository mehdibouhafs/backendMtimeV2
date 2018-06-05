package ma.munisys.model;

import java.util.List;

public class StatisticActivities {

	private double productivite;
	private double productiviteThisMonth;

	private double durtionActivitiesThisMonth;
	private double durtionEstimeThisMonth;

	private double durtionActivities;
	private double durtionEstime;

	private int realisedActivities;
	private int planifiedActivities;

	private int realisedActivitiesOnthisMonth;
	private int planifiedActivitiesOnthisMonth;

	private int activitiesRealisedOnWeekEnd;
	private int activitiesRealisedOnWeekEndThisMonth;

	private List<ActivityParType> activityParType;
	private List<ActivityParCustomer> activityParCustomer;

	private List<DurtionActivityParMonth> DurtionActivityParMonth;
	private List<DurtionEstimeParMonth> DurtionEstimeParMonth;

	private List<ActivityRealisedParMonth> activityRealisedParMonth;

	public StatisticActivities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatisticActivities(double productivite, double productiviteThisMonth, double durtionActivitiesThisMonth,
			double durtionEstimeThisMonth, double durtionActivities, double durtionEstime, int realisedActivities,
			int planifiedActivities, int realisedActivitiesOnthisMonth, int planifiedActivitiesOnthisMonth,
			int activitiesRealisedOnWeekEnd, int activitiesRealisedOnWeekEndThisMonth,
			List<ActivityParType> activityParType, List<ActivityParCustomer> activityParCustomer,
			List<ma.munisys.model.DurtionActivityParMonth> durtionActivityParMonth,
			List<ma.munisys.model.DurtionEstimeParMonth> durtionEstimeParMonth,
			List<ActivityRealisedParMonth> activityRealisedParMonth) {
		super();
		this.productivite = productivite;
		this.productiviteThisMonth = productiviteThisMonth;
		this.durtionActivitiesThisMonth = durtionActivitiesThisMonth;
		this.durtionEstimeThisMonth = durtionEstimeThisMonth;
		this.durtionActivities = durtionActivities;
		this.durtionEstime = durtionEstime;
		this.realisedActivities = realisedActivities;
		this.planifiedActivities = planifiedActivities;
		this.realisedActivitiesOnthisMonth = realisedActivitiesOnthisMonth;
		this.planifiedActivitiesOnthisMonth = planifiedActivitiesOnthisMonth;
		this.activitiesRealisedOnWeekEnd = activitiesRealisedOnWeekEnd;
		this.activitiesRealisedOnWeekEndThisMonth = activitiesRealisedOnWeekEndThisMonth;
		this.activityParType = activityParType;
		this.activityParCustomer = activityParCustomer;
		DurtionActivityParMonth = durtionActivityParMonth;
		DurtionEstimeParMonth = durtionEstimeParMonth;
		this.activityRealisedParMonth = activityRealisedParMonth;
	}

	public List<DurtionEstimeParMonth> getDurtionEstimeParMonth() {
		return DurtionEstimeParMonth;
	}

	public void setDurtionEstimeParMonth(List<DurtionEstimeParMonth> durtionEstimeParMonth) {
		DurtionEstimeParMonth = durtionEstimeParMonth;
	}

	public List<DurtionActivityParMonth> getDurtionActivityParMonth() {
		return DurtionActivityParMonth;
	}

	public void setDurtionActivityParMonth(List<DurtionActivityParMonth> durtionActivityParMonth) {
		DurtionActivityParMonth = durtionActivityParMonth;
	}

	public double getProductivite() {
		return productivite;
	}

	public void setProductivite(double productivite) {
		this.productivite = productivite;
	}

	public double getDurtionEstime() {
		return durtionEstime;
	}

	public void setDurtionEstime(double durtionEstime) {
		this.durtionEstime = durtionEstime;
	}

	public double getDurtionActivities() {
		return durtionActivities;
	}

	public void setDurtionActivities(double durtionActivities) {
		this.durtionActivities = durtionActivities;
	}

	public List<ActivityParType> getActivityParType() {
		return activityParType;
	}

	public void setActivityParType(List<ActivityParType> activityParType) {
		this.activityParType = activityParType;
	}

	public List<ActivityParCustomer> getActivityParCustomer() {
		return activityParCustomer;
	}

	public void setActivityParCustomer(List<ActivityParCustomer> activityParCustomer) {
		this.activityParCustomer = activityParCustomer;
	}

	public int getActivitiesRealisedOnWeekEnd() {
		return activitiesRealisedOnWeekEnd;
	}

	public void setActivitiesRealisedOnWeekEnd(int activitiesRealisedOnWeekEnd) {
		this.activitiesRealisedOnWeekEnd = activitiesRealisedOnWeekEnd;
	}

	public int getActivitiesRealisedOnWeekEndThisMonth() {
		return activitiesRealisedOnWeekEndThisMonth;
	}

	public void setActivitiesRealisedOnWeekEndThisMonth(int activitiesRealisedOnWeekEndThisMonth) {
		this.activitiesRealisedOnWeekEndThisMonth = activitiesRealisedOnWeekEndThisMonth;
	}

	public int getRealisedActivitiesOnthisMonth() {
		return realisedActivitiesOnthisMonth;
	}

	public void setRealisedActivitiesOnthisMonth(int realisedActivitiesOnthisMonth) {
		this.realisedActivitiesOnthisMonth = realisedActivitiesOnthisMonth;
	}

	public int getPlanifiedActivitiesOnthisMonth() {
		return planifiedActivitiesOnthisMonth;
	}

	public void setPlanifiedActivitiesOnthisMonth(int planifiedActivitiesOnthisMonth) {
		this.planifiedActivitiesOnthisMonth = planifiedActivitiesOnthisMonth;
	}

	public int getRealisedActivities() {
		return realisedActivities;
	}

	public void setRealisedActivities(int realisedActivities) {
		this.realisedActivities = realisedActivities;
	}

	public int getPlanifiedActivities() {
		return planifiedActivities;
	}

	public void setPlanifiedActivities(int planifiedActivities) {
		this.planifiedActivities = planifiedActivities;
	}

	public double getDurtionActivitiesThisMonth() {
		return durtionActivitiesThisMonth;
	}

	public void setDurtionActivitiesThisMonth(double durtionActivitiesThisMonth) {
		this.durtionActivitiesThisMonth = durtionActivitiesThisMonth;
	}

	public double getDurtionEstimeThisMonth() {
		return durtionEstimeThisMonth;
	}

	public void setDurtionEstimeThisMonth(double durtionEstimeThisMonth) {
		this.durtionEstimeThisMonth = durtionEstimeThisMonth;
	}

	public double getProductiviteThisMonth() {
		return productiviteThisMonth;
	}

	public void setProductiviteThisMonth(double productiviteThisMonth) {
		this.productiviteThisMonth = productiviteThisMonth;
	}

	public List<ActivityRealisedParMonth> getActivityRealisedParMonth() {
		return activityRealisedParMonth;
	}

	public void setActivityRealisedParMonth(List<ActivityRealisedParMonth> activityRealisedParMonth) {
		this.activityRealisedParMonth = activityRealisedParMonth;
	}

}
