package ma.munisys.service;

import ma.munisys.entities.Certification;
import ma.munisys.entities.DateFormation;
import ma.munisys.entities.Formation;

public interface OutlookService {
	
	public String addAppointementFormation(Formation formation,DateFormation dateFormation);
	
	public void updateAppointementFormation(Formation formation,DateFormation dateFormation);
	
	public void deleteAppoitementFormation(Formation formation);

	public String addAppointementCertification(Certification certification);
	
	public void updateAppointementCertification(Certification certification);
	
	public void deleteAppoitementCertification(Certification certification);

}
