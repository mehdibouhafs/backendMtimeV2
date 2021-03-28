package ma.munisys.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import ma.munisys.entities.Certification;
import ma.munisys.entities.DateFormation;
import ma.munisys.entities.EmpCertification;
import ma.munisys.entities.EmpFormation;
import ma.munisys.entities.Formation;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

@Service
public class OutlookServiceImpl implements OutlookService {

	private ExchangeService service;

	public OutlookServiceImpl() {

		this.service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials("noreply-mtime", "123456", "munisys.net.ma");
		this.service.setCredentials(credentials);
		this.service.setTraceEnabled(true);
		try {
			this.service.setUrl(new URI("Https://pretoria/EWS/Exchange.asmx"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String addAppointementFormation(Formation formation, DateFormation dateFormation) {
		Appointment appointment = null;
		try {
			appointment = new Appointment(service);
			appointment.setStart(dateFormation.getDate());
			appointment.setEnd(dateFormation.getDate());
			appointment.getRequiredAttendees().add("lelkheyati@munisys.net.ma");
			appointment.setSubject("Formation : " +formation.getFrmName() );
			 
			  appointment.setBody(MessageBody.
			  getMessageBodyFromText(
			  		"Merci d'assister à la formation : "+formation.getFrmName()+", Technologie : "+formation.getTechnologie().getNomTechnologie()+", Editeur : "+formation.getEditeur().getNomEditeur()));
			for (EmpFormation user : formation.getCandidats()) {
				appointment.getRequiredAttendees().add(user.getEmploye().getUsername() + "@munisys.net.ma");
			}
			if (dateFormation.getIdOutlook() != null) {
				System.out.println("!=null" + dateFormation.getIdOutlook());
				ItemId a = new ItemId(dateFormation.getIdOutlook());
				appointment = Appointment.bind(service, a);
				appointment.update(ConflictResolutionMode.AutoResolve);
			} else {
				System.out.println("!=null");
				//appointment.accept(true);
				
				appointment.save();
				//appointment.acceptTentatively(true);
				//appointment.accept(true);
			}

			return appointment.getId().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	@Override
	public String addAppointementCertification(Certification certification) {
		Appointment appointment = null;
		try {
			appointment = new Appointment(service);
			appointment.setStart(certification.getDateEch());
			appointment.setEnd(certification.getDateEch());
			appointment.getRequiredAttendees().add("lelkheyati@munisys.net.ma");
			appointment.setSubject("Certification : " +certification.getCertName());
			 
			  appointment.setBody(MessageBody.
			  getMessageBodyFromText(
			  		"Merci de passer votre certification : "+certification.getCertName()+", Technologie : "+certification.getTechnologie().getNomTechnologie()+", Editeur : "+certification.getEditeur().getNomEditeur()));
			for (EmpCertification user : certification.getCandidats()) {
				appointment.getRequiredAttendees().add(user.getEmploye().getUsername() + "@munisys.net.ma");
			}
			if (certification.getIdOutlook() != null) {
				System.out.println("!=null" + certification.getIdOutlook());
				ItemId a = new ItemId(certification.getIdOutlook());
				appointment = Appointment.bind(service, a);
				appointment.update(ConflictResolutionMode.AutoResolve);
			} else {
				System.out.println("!=null");
				//appointment.accept(true);
				
				appointment.save();
				//appointment.acceptTentatively(true);
				//appointment.accept(true);
			}

			return appointment.getId().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateAppointementFormation(Formation formation, DateFormation dateFormation) {
		/*
		 * ItemId a; try { a = new ItemId(formation); Appointment appointment=
		 * Appointment.bind(service, a); SimpleDateFormat formatter = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date startDate =
		 * formatter.parse("2018-09-29 12:00:00"); Date endDate =
		 * formatter.parse("2018-09-29 16:00:00");
		 * 
		 * 
		 * 
		 * appointment.setStart(startDate); appointment.setEnd(endDate);
		 * 
		 * appointment.setSubject("Appointement UPDATE");
		 * appointment.setBody(MessageBody.
		 * getMessageBodyFromText("Appointement UPDATE done"));
		 * 
		 * appointment.accept(true);
		 * appointment.update(ConflictResolutionMode.AutoResolve); } catch (Exception e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void deleteAppoitementFormation(Formation formation) {

		try {

			for (DateFormation dateFormation : formation.getDates()) {
				Appointment appointment = Appointment.bind(service, new ItemId(dateFormation.getIdOutlook()));
				appointment.cancelMeeting();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	@Override
	public void updateAppointementCertification(Certification certification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAppoitementCertification(Certification certification) {
		try {
				Appointment appointment = Appointment.bind(service, new ItemId(certification.getIdOutlook()));
				appointment.cancelMeeting();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
