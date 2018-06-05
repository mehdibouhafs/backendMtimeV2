package ma.munisys.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.munisys.dao.DashboardRepository;
import ma.munisys.model.ActivityRealisedParMonth;
import ma.munisys.model.DateUtils;
import ma.munisys.model.DurtionActivityParMonth;
import ma.munisys.model.DurtionEstimeParMonth;
import ma.munisys.model.StatisticActivities;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardRepository dashboardRepository;

	@Override
	public StatisticActivities getStatisitics(String username) {
		StatisticActivities statistic = new StatisticActivities();

		statistic.setPlanifiedActivities(dashboardRepository.getActivitiesPlanified(username));
		statistic.setRealisedActivities(dashboardRepository.getActivitiesRealised(username));

		statistic.setActivitiesRealisedOnWeekEnd(dashboardRepository.getActivitiesRealisedOnWeekEnd(username));
		statistic.setActivitiesRealisedOnWeekEndThisMonth(
				dashboardRepository.getActivitiesRealisedOnWeekEndThisMonth(username));

		statistic.setRealisedActivitiesOnthisMonth(dashboardRepository.getActivitiesRealisedOnthisMonth(username));
		statistic.setPlanifiedActivitiesOnthisMonth(dashboardRepository.getActivitiesPlanifiedOnthisMonth(username));

		statistic.setActivityParType(dashboardRepository.getActivitiesParType(username));
		statistic.setActivityParCustomer(dashboardRepository.getNbreActivitiesParCustomer(username));

		statistic.setDurtionActivities(dashboardRepository.getDurtionActivities(username));

		statistic.setDurtionEstime(this.calculDurtionEstime(username));
		statistic.setDurtionEstimeThisMonth(this.calculDurtionEstimeForThisMonth(username));

		statistic.setDurtionActivitiesThisMonth(dashboardRepository.getDurtionActivitiesOnthisMonth(username));

		statistic.setProductivite(calculproductivite(statistic.getDurtionActivities(), statistic.getDurtionEstime()));
		statistic.setProductiviteThisMonth(calculproductiviteThisMonth(statistic.getDurtionActivitiesThisMonth(),
				statistic.getDurtionEstimeThisMonth()));

		statistic.setDurtionActivityParMonth(
				this.chargerList(dashboardRepository.getDurtionParMonth(username, new PageRequest(0, 12))));

		statistic.setDurtionEstimeParMonth(this.durtionEstimeParMonth());
		
		statistic.setActivityRealisedParMonth(this.chargerListActivityParMonth(dashboardRepository.getNbreActivitiesParMonth(username)));

		return statistic;
	}

	public List<DurtionActivityParMonth> chargerList(List<DurtionActivityParMonth> liste) {

		int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		List<DurtionActivityParMonth> liste1 = new ArrayList<DurtionActivityParMonth>();
		for (int i = thisMonth; i >= 0; i--) {
			liste1.add(new DurtionActivityParMonth(i + 1, thisYear, 0));
		}
		for (int i = 11; i > thisMonth; i--) {
			liste1.add(new DurtionActivityParMonth(i + 1, thisYear - 1, 0));
		}

		for (int i = 0; i < liste.size(); i++) {
			DurtionActivityParMonth a = liste.get(i);
			for (int j = 0; j < liste1.size(); j++) {
				DurtionActivityParMonth b = liste1.get(j);
				if (b.getYear() == a.getYear() && b.getMonth() == a.getMonth()) {
					liste1.remove(b);
					liste1.add(a);
				}

			}
		}

		liste1.sort(new Comparator<DurtionActivityParMonth>() {

			@Override
			public int compare(DurtionActivityParMonth o1, DurtionActivityParMonth o2) {
				if (o1.getYear() == o2.getYear())
					return Integer.compare(o1.getMonth(), o2.getMonth());
				else
					return Integer.compare(o1.getYear(), o2.getYear());
			}
		});

		return liste1;
	}

	public List<ActivityRealisedParMonth> chargerListActivityParMonth(List<ActivityRealisedParMonth> liste) {

		int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		List<ActivityRealisedParMonth> liste1 = new ArrayList<ActivityRealisedParMonth>();
		for (int i = thisMonth; i >= 0; i--) {
			liste1.add(new ActivityRealisedParMonth(i + 1, thisYear, 0));
		}
		for (int i = 11; i > thisMonth; i--) {
			liste1.add(new ActivityRealisedParMonth(i + 1, thisYear - 1, 0));
		}

		for (int i = 0; i < liste.size(); i++) {
			ActivityRealisedParMonth a = liste.get(i);
			for (int j = 0; j < liste1.size(); j++) {
				ActivityRealisedParMonth b = liste1.get(j);
				if (b.getYear() == a.getYear() && b.getMonth() == a.getMonth()) {
					liste1.remove(b);
					liste1.add(a);
				}

			}
		}

		liste1.sort(new Comparator<ActivityRealisedParMonth>() {

			@Override
			public int compare(ActivityRealisedParMonth o1, ActivityRealisedParMonth o2) {
				if (o1.getYear() == o2.getYear())
					return Integer.compare(o1.getMonth(), o2.getMonth());
				else
					return Integer.compare(o1.getYear(), o2.getYear());
			}
		});

		return liste1;
	}
	
	public List<DurtionEstimeParMonth> durtionEstimeParMonth() {
		int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar c = Calendar.getInstance();
		Date firstDay;
		Date lastDay;
		List<DurtionEstimeParMonth> liste = new ArrayList<DurtionEstimeParMonth>();

		for (int i = thisMonth; i >= 0; i--) {
			c.set(Calendar.YEAR, thisYear);
			c.set(Calendar.MONTH, i);
			c.set(Calendar.DAY_OF_MONTH, 1);
			firstDay = c.getTime();
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

			if (i == thisMonth)
				lastDay = new Date();
			else
				lastDay = c.getTime();

			System.out.println("Mois " + (i + 1) + firstDay + " " + lastDay);

			liste.add(new DurtionEstimeParMonth(i + 1, thisYear,
					510 * DateUtils.getWorkingDaysBetweenTwoDates(firstDay, lastDay, 0)));
		}

		for (int i = 11; i > thisMonth; i--) {
			c.set(Calendar.YEAR, thisYear - 1);
			c.set(Calendar.MONTH, i);
			c.set(Calendar.DAY_OF_MONTH, 1);
			firstDay = c.getTime();
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			lastDay = c.getTime();
			System.out.println("Mois " + (i + 1) + firstDay + " " + lastDay);
			liste.add(new DurtionEstimeParMonth(i + 1, thisYear - 1,
					510 * DateUtils.getWorkingDaysBetweenTwoDates(firstDay, lastDay, 0)));
		}

		liste.sort(new Comparator<DurtionEstimeParMonth>() {

			@Override
			public int compare(DurtionEstimeParMonth o1, DurtionEstimeParMonth o2) {
				if (o1.getYear() == o2.getYear())
					return Integer.compare(o1.getMonth(), o2.getMonth());
				else
					return Integer.compare(o1.getYear(), o2.getYear());
			}
		});

		return liste;
	}

	public double calculDurtionEstime(String username) {
		if (dashboardRepository.getMinDate(username) != null) {
			return (510
					* (DateUtils.getWorkingDaysBetweenTwoDates(dashboardRepository.getMinDate(username), new Date(), 0)
							+ 1));
		} else {
			return 1;
		}
	}

	public double calculDurtionEstimeForThisMonth(String username) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date FirstDayOfThisMonth = c.getTime();

		if (dashboardRepository.getMinDate(username) != null) {
			return (510 * (DateUtils.getWorkingDaysBetweenTwoDates(FirstDayOfThisMonth, new Date(), 0) + 1));
		} else {
			return 1;
		}
	}

	public double calculproductivite(double durtionActivity, double durtionActivityEstime) {
		return (durtionActivity * 100) / (durtionActivityEstime + 1);
	}

	public double calculproductiviteThisMonth(double durtionActivityThisMonth, double durtionActivityEstimeThisMonth) {
		return (durtionActivityThisMonth * 100) / (durtionActivityEstimeThisMonth + 1);
	}

}
