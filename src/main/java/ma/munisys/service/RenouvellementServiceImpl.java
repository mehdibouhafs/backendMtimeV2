package ma.munisys.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ma.munisys.dao.CtrctCustomerDAO;
import ma.munisys.dao.CtrctCustomerProductDAO;
import ma.munisys.dao.CtrctSupplierDAO;
import ma.munisys.dao.DistributeurDao;
import ma.munisys.dao.ProductDAO;
import ma.munisys.entities.AppUser;
import ma.munisys.entities.CtrctCustomer;
import ma.munisys.entities.CtrctCustomerProduct;
import ma.munisys.entities.CtrctCustomerProductSeries;
import ma.munisys.entities.CtrctSupplier;
import ma.munisys.entities.CtrctSupplierProduct;
import ma.munisys.entities.CtrctSupplierProductSeries;
import ma.munisys.entities.Customer;
import ma.munisys.entities.Distributeur;
import ma.munisys.entities.Product;

@Service
@Transactional
public class RenouvellementServiceImpl implements RenouvellementService {

	@Autowired
	CtrctCustomerDAO contratClientDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	CtrctSupplierDAO contratFournisseurDAO;
	
	
	@Autowired
	CtrctCustomerProductDAO ctrctCustomerProductDAO;
	
	
	@Autowired
	DistributeurDao distributeurDAO;
	
	private final Path rootLocation = Paths.get("upload-dir");
	

	
	public String getIdByContratName(String ctrt_name,String code_project) {
		
		
		return contratClientDAO.getIdByContratName(ctrt_name,code_project);
	}

	public List<CtrctCustomer> getAllContratClientByContrat(String cli) {
		System.out.println("HA LCUSTOMER \t :"+cli);
		
		return contratClientDAO.getAllContratClientByContrat(cli);
	}
	
	public List<String> getPiloteName(String pl){
		
		return contratClientDAO.getPiloteName(pl);
		
	}
	
	
	
	
	
	public int getNumberofContracts() {
		
		
		return contratClientDAO.getNumberOfContracts();
	}
	
	
	@Override
	public CtrctCustomer saveContratClient(CtrctCustomer contratClient) {
	    
		System.out.println("ID CONTRAT /" +getIdByContratName(contratClient.getCtrt_name(),contratClient.getProject_name()));
		
		if(getIdByContratName(contratClient.getCtrt_name(),contratClient.getProject_name()) != null) {
			
			String id = getIdByContratName(contratClient.getCtrt_name(),contratClient.getProject_name());
			int result = Integer.parseInt(id);
			contratClient.setId(result);
		}
		
		if(getIdByContratName(contratClient.getCtrt_name(),contratClient.getProject_name()) == null) {
			
			int x = getNumberofContracts() + 2;
			contratClient.setId(x);

			
		}
		
		for (CtrctCustomerProduct ctrctCustomerProduct : contratClient.getCtrctCustomerProducts()) {
			ctrctCustomerProduct.setCtrctCustomer(contratClient);
			
		
			productDAO.save(ctrctCustomerProduct.getProduct());
			for (CtrctCustomerProductSeries ctrctCustomerProductSeries : ctrctCustomerProduct
					.getCtrctCustomerProductSeries()) {
				
				System.out.println("SHOW ME : "+ctrctCustomerProductSeries);
				ctrctCustomerProductSeries.setCtrctCustomerProduct(ctrctCustomerProduct);
			}

		}

		return contratClientDAO.save(contratClient);
	}

	@Override
	public Page<Product> getProducts(String mc) {

		return productDAO.findByNameStartsWith("%" + mc + "%", new PageRequest(0, 10));
	}

	@Override
	public List<CtrctCustomer> getAllContratClient() {
		System.out.println(" All contract client  " + contratClientDAO.getAllContratsClients());
		return contratClientDAO.getAllContratsClients();
	}
	
	@Override
	public List<CtrctCustomer> getAllContratsClientsByPilote(String pilote) {
		System.out.println(" All contract client  " + contratClientDAO.getAllContratsClientsByPilote(pilote));
		System.out.println("\n Pilote "+pilote);
		return contratClientDAO.getAllContratsClientsByPilote(pilote);
	}

	
	
	
	@Override			 
	public CtrctSupplier saveContratFournisseur(CtrctSupplier contratFournisseur) {
		
		
		System.out.println(contratFournisseur);

		for (CtrctSupplierProduct ctrctSupplierProduct : contratFournisseur.getCtrctSupplierProducts()) {
			ctrctSupplierProduct.setCtrctSupplier(contratFournisseur);
			System.out.println(ctrctSupplierProduct);
			CtrctCustomerProduct c = ctrctSupplierProduct.getCtrctCustomerProduct();
			c.setQteDisponible(c.getQteDisponible()-ctrctSupplierProduct.getQte());
			CtrctCustomerProduct ctrctCustomerProduct = ctrctSupplierProduct.getCtrctCustomerProduct();
			ctrctCustomerProduct.setQteDisponible(ctrctCustomerProduct.getQteDisponible() - ctrctSupplierProduct.getQte());
			
			
			if(ctrctSupplierProduct.getCtrctSupplierProductSeries() != null) {
				for (CtrctSupplierProductSeries ctrctSupplierProductSeries : ctrctSupplierProduct
						.getCtrctSupplierProductSeries()) {
					ctrctSupplierProductSeries.setCtrctSupplierProduct(ctrctSupplierProduct);
				}
			}
		}
	

		return contratFournisseurDAO.save(contratFournisseur);
	}

	@Override
	public List<CtrctCustomer> getContratClientByClient(String cl,String user) {
		return contratClientDAO.getContratClientByClient(cl,user);
	}
	
	@Override
	public List<CtrctCustomer> getContratClientByContrat(String ctrt_name,String client) {


		return contratClientDAO.getContratClientByContrat(ctrt_name,client);
	}
	
	@Override
	public List<CtrctCustomer> getContratClientByContratProject() {
		
		//System.out.println("\n\n PROJECT"+project);
		return contratClientDAO.getContratClientByContratProject();
	}
	

	@Override
	public List<CtrctSupplier> getAllContratsSupplierByStatutSupplierForCtrClient(String statut) {
		// TODO Auto-generated method stub
		List<CtrctSupplier> ctrs = contratFournisseurDAO.getAllContratsSupplierByStatutSupplierForCtrClient(statut);
		System.out.println(ctrs.toString());
		return contratFournisseurDAO.getAllContratsSupplierByStatutSupplierForCtrClient(statut);
	}

	@Override
	public List<CtrctCustomer> getAllContratsClientByStatutSupplier(String statut) {
		// TODO Auto-generated method stub

		List<CtrctSupplier> ctrctSuppliers = getAllContratsSupplierByStatutSupplierForCtrClient(statut);

		List<CtrctCustomer> allCtrtCustomer = contratClientDAO.getAllContratsClients();

		for (int i = 0; i < allCtrtCustomer.size(); i++) {

			allCtrtCustomer.get(i).setContrats(null );

			for (int j = 0; j < ctrctSuppliers.size(); j++) {
				System.out.println("allCtrtCustomer.get(i).getId() "+allCtrtCustomer.get(i).getId());
				System.out.println("ctrctSuppliers.get(j).getCtrctCustomer().getId() "  +ctrctSuppliers.get(j).getCtrctCustomer().getId());
				if (ctrctSuppliers.get(j).getCtrctCustomer().getId() == allCtrtCustomer.get(i).getId()) {
					 if(allCtrtCustomer.get(i).getContrats() == null) {
						 Set<CtrctSupplier> contrats = new HashSet<>();
						 allCtrtCustomer.get(i).setContrats(contrats);
					 }
					allCtrtCustomer.get(i).getContrats().add(ctrctSuppliers.get(j));
				}

			}

		}
		
		for (int i = 0; i < allCtrtCustomer.size(); i++) {
			if (allCtrtCustomer.get(i).getContrats() == null) {
				allCtrtCustomer.remove(allCtrtCustomer.get(i));
			}
		}
		

		return allCtrtCustomer;
	}

	@Override
	public List<CtrctCustomer> getAllContratClientFromWorkbook() {
		// TODO Auto-generated method stub
		System.out.println("rootLocation.toString() " + rootLocation.toString());
		List<CtrctCustomer> ctrctCustomers = new ArrayList<>();
		try {
			Workbook workbook = WorkbookFactory.create(new File(rootLocation.toString()+"/templateimport.xlsx"));
			int lastRow = getLastRow(workbook, 0);
			for(int i=1; i<=lastRow;i++) {
				CtrctCustomer ctrctCustomer = new CtrctCustomer();
				System.out.println("id " + (long)workbook.getSheetAt(0).getRow(i).getCell(0).getNumericCellValue());
				ctrctCustomer.setId((int)workbook.getSheetAt(0).getRow(i).getCell(0).getNumericCellValue());
				ctrctCustomer.setDteStrt(workbook.getSheetAt(0).getRow(i).getCell(3).getDateCellValue());
				ctrctCustomer.setDteEnd(workbook.getSheetAt(0).getRow(i).getCell(4).getDateCellValue());
				ctrctCustomer.setCustomer(new Customer(workbook.getSheetAt(0).getRow(i).getCell(5).getStringCellValue()));
				ctrctCustomer.setPilote(workbook.getSheetAt(0).getRow(i).getCell(2).getStringCellValue());
				ctrctCustomer.setCtrt_name(workbook.getSheetAt(0).getRow(i).getCell(1).getStringCellValue());
				ctrctCustomer.setProject_name(workbook.getSheetAt(0).getRow(i).getCell(1).getStringCellValue());

				
				ctrctCustomers.add(ctrctCustomer);
			}
			
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctrctCustomers;
	}

	@Override
	public List<CtrctCustomerProduct> getAllContractCustomerProduct(int contractCustomerId) {
		
		List<CtrctCustomerProduct> ctrctCustomerProducts = new ArrayList<>();
		
		try {
				Workbook workbook = WorkbookFactory.create(new File(rootLocation.toString()+"/templateimport.xlsx"));
				int lastRow = getLastRow(workbook, 1);
				for(int i=1; i<=lastRow;i++) {
					Long id =  (long)workbook.getSheetAt(1).getRow(i).getCell(1).getNumericCellValue();
					if( id == contractCustomerId) {
						CtrctCustomerProduct ctrctCustomerProduct = new CtrctCustomerProduct();
						
						//System.out.println("  "  + );
						
						if(productDAO.findOne(workbook.getSheetAt(1).getRow(i).getCell(2).getStringCellValue()) == null) {
							System.out.println("PRODUCT NOT FIND");
							Product p = new Product();
							p.setRef(workbook.getSheetAt(1).getRow(i).getCell(2).getStringCellValue());
							p.setDesig(workbook.getSheetAt(1).getRow(i).getCell(3).getStringCellValue());
							p.setDistributeur(workbook.getSheetAt(1).getRow(i).getCell(4).getStringCellValue());
							p.setEditeur(workbook.getSheetAt(1).getRow(i).getCell(5).getStringCellValue());
							productDAO.save(p);
							
						}
						
						ctrctCustomerProduct.setId((long)workbook.getSheetAt(1).getRow(i).getCell(0).getNumericCellValue());
						ctrctCustomerProduct.setProduct(new Product(workbook.getSheetAt(1).getRow(i).getCell(2).getStringCellValue(), null, null, null));
						ctrctCustomerProduct.setQte((int)(workbook.getSheetAt(1).getRow(i).getCell(6).getNumericCellValue()));
						ctrctCustomerProducts.add(ctrctCustomerProduct);
					}
					
				}
				
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return ctrctCustomerProducts;
	}

	@Override
	public List<CtrctCustomerProductSeries> getAllCtrctCustomerProductSeries(Long idProduitContratClient) {
		// TODO Auto-generated method stub
		List<CtrctCustomerProductSeries> ctrctCustomerProductSeries = new ArrayList<>();
		
		try {
			Workbook workbook = WorkbookFactory.create(new File(rootLocation.toString()+"/templateimport.xlsx"));
			int lastRow = getLastRow(workbook, 2);
			for(int i=1; i<=lastRow;i++) {
				Long idProduitContratClientFile =  (long)workbook.getSheetAt(2).getRow(i).getCell(0).getNumericCellValue();
				if( idProduitContratClientFile == idProduitContratClient) {
					CtrctCustomerProductSeries ctrctCustomerProductSerie = new CtrctCustomerProductSeries();
					ctrctCustomerProductSerie.setSn(workbook.getSheetAt(2).getRow(i).getCell(1).getStringCellValue());
					ctrctCustomerProductSeries.add(ctrctCustomerProductSerie);
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ctrctCustomerProductSeries;
	}

	@Override
	public List<CtrctSupplier> getAllContratFournisseurFromWorkbook(int contractCustomerId) {
		
		
		List<CtrctCustomer> ctrctCustomers = getAllContratClientFromWorkbook();
		List<CtrctSupplier> ctrctSuppliers = new ArrayList<>();
		
		try {
			Workbook workbook = WorkbookFactory.create(new File(rootLocation.toString()+"/templateimport.xlsx"));
			int lastRow = getLastRow(workbook, 3);
			for(int i=1; i<=lastRow;i++) {
				Long id =  (long)workbook.getSheetAt(3).getRow(i).getCell(1).getNumericCellValue();
				if( id == contractCustomerId) {
					CtrctSupplier ctrctSupplier = new CtrctSupplier();
					ctrctSupplier.setId((long)workbook.getSheetAt(3).getRow(i).getCell(0).getNumericCellValue());
					ctrctSupplier.setCtrtName(workbook.getSheetAt(3).getRow(i).getCell(2).getStringCellValue());
					ctrctSupplier.setDteStrt(workbook.getSheetAt(3).getRow(i).getCell(3).getDateCellValue());
					ctrctSupplier.setDteEnd(workbook.getSheetAt(3).getRow(i).getCell(4).getDateCellValue());
					ctrctSupplier.setContact(workbook.getSheetAt(3).getRow(i).getCell(5).getStringCellValue());
					ctrctSupplier.setPrice(workbook.getSheetAt(3).getRow(i).getCell(6).getNumericCellValue());
					ctrctSupplier.setStatut(workbook.getSheetAt(3).getRow(i).getCell(7).getStringCellValue());
					ctrctSupplier.setCommentaire(workbook.getSheetAt(3).getRow(i).getCell(8).getStringCellValue());
					
					ctrctSuppliers.add(ctrctSupplier);
				}
				
			}
			
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctrctSuppliers;
		
	}

	@Override
	public List<CtrctSupplierProduct> getAllContratFournisseurProduit(Long contratFournisseur) {
		
		List<CtrctSupplierProduct> ctrctSupplierProducts = new ArrayList<>();
		
		try {
				Workbook workbook = WorkbookFactory.create(new File(rootLocation.toString()+"/templateimport.xlsx"));
				int lastRow = getLastRow(workbook, 4);
				for(int i=1; i<=lastRow;i++) {
					Long id =  (long)workbook.getSheetAt(4).getRow(i).getCell(1).getNumericCellValue();
					if(  id == contratFournisseur) {
						CtrctSupplierProduct ctrctSupplierProduct = new CtrctSupplierProduct();
						
						ctrctSupplierProduct.setId((long)(workbook.getSheetAt(4).getRow(i).getCell(0).getNumericCellValue()));
						ctrctSupplierProduct.setProduct(new Product(workbook.getSheetAt(4).getRow(i).getCell(2).getStringCellValue(), null, null, null));
						ctrctSupplierProduct.setQte( (int)(workbook.getSheetAt(4).getRow(i).getCell(3).getNumericCellValue()));
						
						ctrctSupplierProducts.add(ctrctSupplierProduct);
					}
					
				}
				
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return ctrctSupplierProducts;
		
	}

	@Override
	public List<CtrctSupplierProductSeries> getAllCtrctSupplierProductSeries(Long idProduitContratFournisseur) {
		// TODO Auto-generated method stub
		List<CtrctSupplierProductSeries> ctrctSupplierProductSeries = new ArrayList<>();
		
		try {
			Workbook workbook = WorkbookFactory.create(new File(rootLocation.toString()+"/templateimport.xlsx"));
			int lastRow = getLastRow(workbook, 5);
			for(int i=1; i<=lastRow;i++) {
				long idProduitContratFournisseurFile = (long)workbook.getSheetAt(5).getRow(i).getCell(0).getNumericCellValue();
				if( idProduitContratFournisseurFile == idProduitContratFournisseur) {
					CtrctSupplierProductSeries ctrctSupplierProductSerie = new CtrctSupplierProductSeries();
					//ctrctSupplierProductSerie.setSn(workbook.getSheetAt(5).getRow(i).getCell(1).getStringCellValue());
					ctrctSupplierProductSeries.add(ctrctSupplierProductSerie);
				}
				
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ctrctSupplierProductSeries;
	}

	@Override
	public int getLastRow(Workbook workbook, int page) {
		
		Sheet sheet = workbook.getSheetAt(page);

        int lastrow = sheet.getLastRowNum();
        
        return lastrow;
	}

	@Override
	public void saveContratClientFromFile() {
		
		List<CtrctCustomer> ctrctCustomers = getAllContratClientFromWorkbook();
		System.out.println("ctrctCustomers " + ctrctCustomers.toString());
		for(CtrctCustomer ctrctCustomer : ctrctCustomers) {
			ctrctCustomer.setCtrctCustomerProducts(new HashSet<>());
			ctrctCustomer.setContrats(new HashSet<>());
			
			List<CtrctCustomerProduct> ctrctCustomerProducts =  getAllContractCustomerProduct(ctrctCustomer.getId());
			System.out.println("ctrctCustomerProducts for ctrcCustomer  "+ ctrctCustomer.getId() +"  = "+ ctrctCustomerProducts.toString());
			
			for(CtrctCustomerProduct ctrctCustomerProduct : ctrctCustomerProducts ) {
				List<CtrctCustomerProductSeries> ctrctCustomerProductSeries = getAllCtrctCustomerProductSeries(ctrctCustomerProduct.getId());
					
				if(ctrctCustomerProductSeries != null && ctrctCustomerProductSeries.size() > 0) {
					ctrctCustomerProduct.setCtrctCustomerProductSeries(new HashSet<CtrctCustomerProductSeries>());
					System.out.println(" " + " CtrctCustomerProductSerie for  CtrctCustomerProduct " + ctrctCustomerProduct.getId() + " = " + ctrctCustomerProductSeries.toString());
					for(CtrctCustomerProductSeries ctCustomerProductSeries : ctrctCustomerProductSeries) {
						ctCustomerProductSeries.setCtrctCustomerProduct(ctrctCustomerProduct);
					}
					ctrctCustomerProduct.getCtrctCustomerProductSeries().addAll(ctrctCustomerProductSeries);
				}
				
				ctrctCustomerProduct.setId(null);
				ctrctCustomerProduct.setCtrctCustomer(ctrctCustomer);
				
			}
			
			ctrctCustomer.getCtrctCustomerProducts().addAll(ctrctCustomerProducts);
			
			List<CtrctSupplier> ctrctSuppliers = getAllContratFournisseurFromWorkbook(ctrctCustomer.getId());
				System.out.println("CtrctSupplier " + ctrctSuppliers.toString());
			for(CtrctSupplier ctrctSupplier :  ctrctSuppliers) {
				List<CtrctSupplierProduct> ctrctSupplierProducts =  getAllContratFournisseurProduit(ctrctSupplier.getId());
				
				if(ctrctSuppliers != null && ctrctSuppliers.size()>0) {
					ctrctSupplier.setCtrctSupplierProducts(new HashSet<>());
					System.out.println("ctrctSupplierProduct for CtrctSupplier  "+ ctrctSupplier.getId() +"  = "+ ctrctSupplierProducts.toString());
					
					for(CtrctSupplierProduct crCtrctSupplierProduct : ctrctSupplierProducts) {
						List<CtrctSupplierProductSeries> ctrctSupplierProductSeries = getAllCtrctSupplierProductSeries(crCtrctSupplierProduct.getId());
						System.out.println("ctrctSupplierProductSeries  crCtrctSupplierProduct = " + ctrctSupplierProductSeries.toString());
						if(ctrctSupplierProductSeries != null && ctrctSupplierProductSeries.size() > 0 ) {
							
							crCtrctSupplierProduct.setCtrctSupplierProductSeries(new HashSet<>());
							for(CtrctSupplierProductSeries ctrctSupplierProductSerie : ctrctSupplierProductSeries) {
								ctrctSupplierProductSerie.setCtrctSupplierProduct(crCtrctSupplierProduct);
							}
							
							crCtrctSupplierProduct.getCtrctSupplierProductSeries().addAll(ctrctSupplierProductSeries);
						}
						crCtrctSupplierProduct.setId(null);
						crCtrctSupplierProduct.setCtrctSupplier(ctrctSupplier);
						
					}
					ctrctSupplier.getCtrctSupplierProducts().addAll(ctrctSupplierProducts);
					ctrctSupplier.setId(null);
					
				}

			}
			
			ctrctCustomer.getContrats().addAll(ctrctSuppliers);
			
			System.out.println(ctrctCustomer.toString());
			
			
			
			
	//		ctrctCustomer.setId(null);
			
			CtrctCustomer ctrctCustomerSaved = contratClientDAO.save(ctrctCustomer);
			
			for(CtrctSupplier ctrctSupplier : ctrctCustomer.getContrats()) {
				ctrctSupplier.setCtrctCustomer(ctrctCustomerSaved);
				saveContratFournisseur(ctrctSupplier);
			}
			
			
		
		}

	
	}
	
	
	@Override
	public Page<Distributeur> getDistributeurs(String mc) {
		// TODO Auto-generated method stub
		return this.distributeurDAO.findDistributeurByNameStartsWith("%"+mc+"%", new PageRequest(0, 10));
	}

	@Override
	public List<String> getAllNameContrat(String code) {
		System.out.println(" All contract client  " + contratClientDAO.getAllNameContrat(code));
		return contratClientDAO.getAllNameContrat(code);	}


}
