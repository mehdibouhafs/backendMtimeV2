package ma.munisys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ma.munisys.entities.AppUser;
import ma.munisys.entities.CtrctCustomer;
import ma.munisys.entities.CtrctSupplier;
import ma.munisys.entities.Distributeur;
import ma.munisys.entities.Product;
import ma.munisys.service.RenouvellementService;

@RestController
public class RenouvellementController {
	
	@Autowired
	RenouvellementService renouvellementService;

	@RequestMapping(value="/save-contrat-client", method=RequestMethod.POST, produces = "application/json")
	public CtrctCustomer saveContratClient(@RequestBody CtrctCustomer ctrctCustomer) {
		System.out.println("start saving contract client");
		return renouvellementService.saveContratClient(ctrctCustomer);
		

	}
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public Page<Product> getProducts(String mc) {
		return renouvellementService.getProducts(mc);
	}
	
	@RequestMapping(value="/distributeurs", method=RequestMethod.GET)
	public Page<Distributeur> getDistributeurs(String mc) {
		return renouvellementService.getDistributeurs(mc);
	}
	
	@RequestMapping(value="/getPiloteName", method=RequestMethod.GET)
	public List<String> getPiloteName(String pl) {
		return renouvellementService.getPiloteName(pl);
	}
	
	
	
	@RequestMapping(value="/getAllContratClient", method=RequestMethod.GET)
	public List<CtrctCustomer> getAllContratClient() {
		System.out.println("get All ctrct client");
		return renouvellementService.getAllContratClient();
	}

	
	@RequestMapping(value="/getAllContratsClientsByPilote", method=RequestMethod.GET)
	public List<CtrctCustomer> getAllContratClientByPilote(String pilote) {
		System.out.println("\n\n PROJECT"+pilote);

		return renouvellementService.getAllContratsClientsByPilote(pilote);
	}
	
	@RequestMapping(value="/save-contrat-fournisseur", method=RequestMethod.POST, produces = "application/json")
	public CtrctSupplier saveContratFournisseur(@RequestBody CtrctSupplier ctrctSupplier) {
		return renouvellementService.saveContratFournisseur(ctrctSupplier);
	}
	
	@RequestMapping(value="/getContratClientByClient", method=RequestMethod.GET)
	public List<CtrctCustomer> getContratClientByClient(String cl,String user) {
		System.out.println("HELLO BY CP "+ renouvellementService.getContratClientByClient(cl,user));
		return renouvellementService.getContratClientByClient(cl,user);
	}
	
	@RequestMapping(value="/getContratClientByContrat", method=RequestMethod.GET)
	public List<CtrctCustomer> getContratClientByContrat(String ctrt_name,String client) {
		return renouvellementService.getContratClientByContrat(ctrt_name,client);
	}
	
	@RequestMapping(value="/getContratClientByContratProject", method=RequestMethod.GET)
	public List<CtrctCustomer> getContratClientByContratProject() {
		return renouvellementService.getContratClientByContratProject();
	}
	
	@RequestMapping(value="/getAllContratClientByContrat", method=RequestMethod.GET)
	public List<CtrctCustomer> getAllContratClientByContrat(String cli) {
		System.out.println("PLEASE"+cli);
		return renouvellementService.getAllContratClientByContrat(cli);
	}
	
	

	@RequestMapping(value="/getAllCrtCustomerByStatut", method=RequestMethod.GET)
	public List<CtrctCustomer> getAllContratClientByStatutsupplier(String statut) {
		return renouvellementService.getAllContratsClientByStatutSupplier(statut);
	}
	
	@RequestMapping(value="/saveContratClientFromFile", method=RequestMethod.GET)
	public int saveContratClientFromFile() {
		renouvellementService.saveContratClientFromFile();
		return 1;
	}
	
	@RequestMapping(value="/getAllNameContrat", method=RequestMethod.GET)
	public int getAllNameContrat(String code) {
		renouvellementService.getAllNameContrat(code);
		return 1;
	}
	
	
}
