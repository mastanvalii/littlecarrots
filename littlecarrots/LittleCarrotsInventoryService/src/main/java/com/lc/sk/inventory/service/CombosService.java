package com.lc.sk.inventory.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ComboFullDetailsBean;
import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Combos;
import com.lc.sk.inventory.entities.CombosProductdetails;
import com.lc.sk.inventory.exception.subexception.ComboProductDetailsNotFoundException;
import com.lc.sk.inventory.exception.subexception.CombosNotFoundException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.CombosProductdetailsRepository;
import com.lc.sk.inventory.repositories.CombosRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class CombosService {
	
	@Autowired
	CombosRepository combosrepo;
	
	@Autowired
	CombosProductdetailsRepository combosprodrepo;
	
	@GetMapping(path = URLMapping.COMBOS_GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Combos>> getAllCombos() {
		List<Combos> combos = (List<Combos>) this.combosrepo.getCombos();
		if (!combos.isEmpty()) {
			return new ResponseEntity<>(combos,  HttpStatus.ACCEPTED);

		} else {
			throw new CombosNotFoundException(ConstantValues.COMBOS_NOT_FOUND);
		}

	}
	
	@GetMapping(path = URLMapping.COMBOS_PROD_GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CombosProductdetails>> getAllCombosProddetails() {
		List<CombosProductdetails> combos = (List<CombosProductdetails>) this.combosprodrepo.getComboProductDetails();
		if (!combos.isEmpty()) {
			return new ResponseEntity<>(combos,  HttpStatus.ACCEPTED);

		} else {
			throw new ComboProductDetailsNotFoundException(ConstantValues.COMBOS_PRODUCT_DETAILS_NOT_FOUND);
		}

	}

	//Post method 
		@PostMapping(path = URLMapping.COMBOS_GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<ResponseBean> insertCombos(
				@RequestParam(name = ConstantValues.TOTAL_AMOUNT, required=true, defaultValue=ConstantValues.DEFAULT_FLOAT)float total_amount,
				@RequestParam(name = ConstantValues.DISCOUNT, required=true, defaultValue=ConstantValues.DEFAULT_FLOAT) float discount,
				@RequestParam(name = ConstantValues.TOTAL_AMOUNT_AFTER_DISCOUNT, required = true, defaultValue = ConstantValues.DEFAULT_FLOAT) float total_amt_after_discount,
				@RequestParam(name = ConstantValues.START_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String startdate,
				@RequestParam(name = ConstantValues.END_DATE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String enddate,
				@RequestParam(name = ConstantValues.STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN+ "") boolean status,
				@RequestParam(name = ConstantValues.TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String title,
				@RequestParam(name = ConstantValues.DESCRIPTION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String description,
				@RequestParam(name = ConstantValues.AGE_ID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String ageid,
				@RequestParam(name = ConstantValues.GENDERID, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String genderid
				
				) 
		{

			ResponseBean responseBean=null;
			
			if (total_amount == Float.parseFloat(ConstantValues.DEFAULT_FLOAT)||
					discount == Float.parseFloat(ConstantValues.DEFAULT_FLOAT)||
					total_amt_after_discount == Float.parseFloat(ConstantValues.DEFAULT_FLOAT)||
					startdate.equals(ConstantValues.DEFAULT_STRING) ||
					enddate.equals(ConstantValues.DEFAULT_STRING)||
					title.equals(ConstantValues.DEFAULT_STRING)||
					genderid.equals(ConstantValues.DEFAULT_STRING) ||
					ageid.equals(ConstantValues.DEFAULT_STRING)) {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			} else {
			
				Date date1 = null;
				Date date2= null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(startdate);
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(enddate);

				} catch (ParseException e) {
					
					date1 = Date.from(Instant.parse(startdate));
					date2 = Date.from(Instant.parse(enddate));
				}
				java.sql.Timestamp startdate_1 = new java.sql.Timestamp(date1.getTime());
				java.sql.Timestamp enddate_1 = new java.sql.Timestamp(date2.getTime());
				Combos col= this.combosrepo.save(new Combos(total_amount,discount,total_amt_after_discount,startdate_1,enddate_1,status,title,description,ageid,genderid));
				
			if(col.getTitle().equals(title)) {
					responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
							SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			}else
					{
				responseBean = new ResponseBean("No COMBO details inserted",4000, System.currentTimeMillis());
					}
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
					
			}

		}
		
		
		@PostMapping(path = URLMapping.COMBOS_PROD_GET, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<ResponseBean> insertCollection(
				@RequestParam(name = ConstantValues.COMBOID, required=true, defaultValue=ConstantValues.DEFAULT_INT)long comboid,
				@RequestParam(name="productid[]") long productid[],
				@RequestParam(name="finalprice[]") float finalprice[]
		) 
		{

			ResponseBean responseBean=null;
			List<CombosProductdetails> entities = new CopyOnWriteArrayList<CombosProductdetails>();
			if (comboid == Long.parseLong(ConstantValues.DEFAULT_INT)||
					productid.length==0||finalprice.length==0) {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			} 
			else if (productid.length!=finalprice.length) {
				throw new NullRequestReceivedException(ConstantValues.PLEASE_FILL_ALL_DETAILS);
			}
			else {
				if(productid.length != 0) {
					
					
						int x= productid.length;
						for(int i=0;i<x;i++)
						{
						
						entities.add(new CombosProductdetails(comboid,productid[i],finalprice[i]));
						
						}
					
						combosprodrepo.saveAll(entities);
					
				this.combosprodrepo.saveAll(entities);
					responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
							SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
			}else
					{
				responseBean = new ResponseBean("No combos product details inserted",4000, System.currentTimeMillis()); //$NON-NLS-1$
					}
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
					
			}

		}

		@GetMapping(path = URLMapping.COMBOS_FULL_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<ComboFullDetailsBean>> getFULLCombosDETAILS() {
			List<Combos> combos = (List<Combos>) this.combosrepo.getCombos();
			if (!combos.isEmpty()) {
				
				List<CombosProductdetails> combos1 = (List<CombosProductdetails>) this.combosprodrepo.getComboProductDetails();
				if (!combos1.isEmpty()) {
					List<ComboFullDetailsBean> cfd = new ArrayList<>();
					for(Combos cb: combos) {
						ComboFullDetailsBean cf= new ComboFullDetailsBean();
						cf.setComboid(cb.getComboid());
						cf.setTotal_amount(cb.getTotal_amount());
						cf.setDiscount(cb.getDiscount());
						cf.setTotal_amt_after_discount(cb.getTotal_amt_after_discount());
						cf.setStartdate(cb.getStartdate());
						cf.setEnddate(cb.getEnddate());
						cf.setStatus(cb.isStatus());
						cf.setTitle(cb.getTitle());
						cf.setDescription(cb.getDescription());
						cf.setAgeid(cb.getAgeid());
						cf.setGenderid(cb.getGenderid());
						List<CombosProductdetails> cpds = new ArrayList<>();
						for(CombosProductdetails cfb:combos1) 
						{
							if(cb.getComboid()==cfb.getComboid()) {
								CombosProductdetails cpd= new CombosProductdetails();
								cpd.setComboid(cfb.getComboid());
								cpd.setCombo_subid(cfb.getCombo_subid());
								cpd.setProductid(cfb.getProductid());
								cpd.setFinalPrice(cfb.getFinalPrice());
								cpds.add(cpd);
							}
							
						}
						cf.setComboproddetails(cpds);
						cfd.add(cf);
						
					}
					return new ResponseEntity<List<ComboFullDetailsBean>>(cfd,  HttpStatus.ACCEPTED);

				} else {
					throw new ComboProductDetailsNotFoundException(ConstantValues.COMBOS_PRODUCT_DETAILS_NOT_FOUND);
				}
				
				
				
			} else {
				throw new CombosNotFoundException(ConstantValues.COMBOS_NOT_FOUND);
			}

		}
		
		//comboid without product details
		@GetMapping(path = URLMapping.NO_COMBO_PRODUCTDETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<Combos>> getAllComboidwithoutproductdetails() {
			List<Combos> combos = (List<Combos>) this.combosrepo.getComboidwithoutproductdetails();
			if (!combos.isEmpty()) {
				return new ResponseEntity<>(combos,  HttpStatus.ACCEPTED);

			} else {
				throw new CombosNotFoundException(ConstantValues.COMBOS_NOT_FOUND);
			}

		}

}
