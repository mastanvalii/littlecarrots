package com.lc.sk.inventory.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Collectionsaleprod;
import com.lc.sk.inventory.exception.subexception.CollectionSaleProductidsException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.CollectionsaleprodRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class CollectionsaleprodService {
	
	@Autowired
	CollectionsaleprodRepository collectionsaleprod;
	

	
	@GetMapping(path = URLMapping.COLLECTIONSALEPROD_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Collectionsaleprod>> getAllCollectionsale() {
		List<Collectionsaleprod> collection = (List<Collectionsaleprod>) this.collectionsaleprod.getCollectionsaleprod();
		if (!collection.isEmpty()) {
			return new ResponseEntity<>(collection,  HttpStatus.ACCEPTED);

		} else {
			throw new CollectionSaleProductidsException(ConstantValues.COLLECTIONSALE_PRODUCTIDS_NOT_FOUND);
		}

	}
	
	@GetMapping(path = URLMapping.COLLECTIONSALEPROD_MAPPING_PATH_WITH_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Collectionsaleprod>> getAllCollectionproductidsByCollectionId(@PathVariable long id) {
		List<Collectionsaleprod> collection = (List<Collectionsaleprod>) this.collectionsaleprod.getCollectionsaleprodById(id);
		if (collection.isEmpty()) {
			
			throw new CollectionSaleProductidsException(ConstantValues.COLLECTIONSALE_PRODUCTIDS_NOT_FOUND);
		} else {
			return new ResponseEntity<List<Collectionsaleprod>>(collection,  HttpStatus.ACCEPTED);
		}

	}

	@GetMapping(path = URLMapping.COLLECTIONSALEPROD_MAPPING_PATH_WITH_PATH_SERIAL_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity <Collectionsaleprod> getAllCollectionproductidsBySerialId(@PathVariable long csid) {
		Collectionsaleprod collection = (Collectionsaleprod) this.collectionsaleprod.getCollectionsaleprodBySerialId(csid);
		if (collection.equals(null)) {
			
			throw new CollectionSaleProductidsException(ConstantValues.COLLECTIONSALE_PRODUCTIDS_NOT_FOUND);
		} else {
			return new ResponseEntity <Collectionsaleprod>(collection,  HttpStatus.ACCEPTED);
		}

	}
	
	@PostMapping(path = URLMapping.COLLECTIONSALEPROD_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCollection(
			@RequestParam(name = ConstantValues.ID, required=true, defaultValue=ConstantValues.DEFAULT_INT)long id,
			@RequestParam(name="productid[]") long productid[]
			
	) 
	{

		ResponseBean responseBean=null;
		List<Collectionsaleprod> entities = new CopyOnWriteArrayList<Collectionsaleprod>();
		if (id == Long.parseLong(ConstantValues.DEFAULT_INT)||
				productid.length==0) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			if(productid.length != 0) {
			for(Long a:productid)
			{
			
			entities.add(new Collectionsaleprod(id,a));
			
			}
			this.collectionsaleprod.saveAll(entities);
				responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
		}else
				{
			responseBean = new ResponseBean("No collections inserted",4000, System.currentTimeMillis()); //$NON-NLS-1$
				}
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				
		}

	}
	
}


