package ma.munisys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class EmpCertification implements Serializable {

	@Id @GeneratedValue
	private Long id;
	
	private String niveau;
	
	private String url_image_certif;
	
	private String visa;
	
	@ManyToOne
	private Certification certification;
	
	@ManyToOne
	private AppUser employe;
	

	public EmpCertification() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmpCertification(Long id, String niveau, String url_image_certif, String visa, Certification certification,
			AppUser employe) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.url_image_certif = url_image_certif;
		this.visa = visa;
		this.certification = certification;
		this.employe = employe;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getUrl_image_certif() {
		return url_image_certif;
	}

	public void setUrl_image_certif(String url_image_certif) {
		this.url_image_certif = url_image_certif;
	}



	@JsonIgnore
	public Certification getCertification() {
		return certification;
	}


	@JsonProperty
	public void setCertification(Certification certification) {
		this.certification = certification;
	}



	public AppUser getEmploye() {
		return employe;
	}



	public void setEmploye(AppUser employe) {
		this.employe = employe;
	}


	public String getVisa() {
		return visa;
	}


	public void setVisa(String visa) {
		this.visa = visa;
	}	
	
	
	
}
