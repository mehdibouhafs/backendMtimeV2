package ma.munisys.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;

import ma.munisys.entities.AppUser;
import ma.munisys.entities.CtrctCustomer;
import ma.munisys.entities.CtrctCustomerProduct;
import ma.munisys.entities.CtrctCustomerProductSeries;
import ma.munisys.entities.CtrctSupplier;
import ma.munisys.entities.CtrctSupplierProduct;
import ma.munisys.entities.CtrctSupplierProductSeries;
import ma.munisys.entities.Distributeur;
import ma.munisys.entities.Product;

public interface RenouvellementService {

	public CtrctCustomer saveContratClient(CtrctCustomer contratClient);
	
	public Page<Product> getProducts(String mc);
	
	public String getIdByContratName(String ctrt_name,String code_project);
	
	public List<CtrctCustomer> getAllContratClient();
	public List<CtrctCustomer> getAllContratClientByContrat(String cli);
	
	public List<CtrctCustomer> getAllContratsClientsByPilote(String pilote);
	
	public CtrctSupplier saveContratFournisseur(CtrctSupplier contratFournisseur);

	public List<CtrctCustomer> getContratClientByClient(String code,String user);
	public List<String> getPiloteName(String pl);

	
	public List<CtrctCustomer> getContratClientByContrat(String ctrt_name,String client);
	
	public List<CtrctCustomer> getContratClientByContratProject();
	
	public List<String> getAllNameContrat(String mc);
	
	public List<CtrctCustomer> getAllContratsClientByStatutSupplier(String statut);
	
	public List<CtrctSupplier> getAllContratsSupplierByStatutSupplierForCtrClient(String statut);
	
	public List<CtrctCustomer> getAllContratClientFromWorkbook();
	
	public List<CtrctCustomerProduct> getAllContractCustomerProduct(int contractCustomerId);
	
	public List<CtrctCustomerProductSeries> getAllCtrctCustomerProductSeries(Long idProduitContratClient);
	
	public List<CtrctSupplier> getAllContratFournisseurFromWorkbook(int contractCustomerId);
	
	public List<CtrctSupplierProduct> getAllContratFournisseurProduit(Long contratFournisseur);
	
	public List<CtrctSupplierProductSeries> getAllCtrctSupplierProductSeries(Long idProduitContratFournisseur);
	
	public int getLastRow( Workbook workbook,int page);
	
	
	public void saveContratClientFromFile();
	
	
	public Page<Distributeur> getDistributeurs(String mc);
	
	
	
	
	
	
	
}
