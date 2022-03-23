package com.lc.sk.inventory.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.CollectionsaleImages;
import com.lc.sk.inventory.exception.subexception.CollectionSaleException;
import com.lc.sk.inventory.exception.subexception.CollectionSaleImageException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.CollectionsaleImagesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class CollectionsaleImagesService {

	@Autowired
	CollectionsaleImagesRepository collectionsaleimgrepo;
	
	
	
	@GetMapping(path = URLMapping.COLLECTIONSALE_IMAGE_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<CollectionsaleImages>> getAllCollectionsale() {
		List<CollectionsaleImages> collection = (List<CollectionsaleImages>) this.collectionsaleimgrepo.getCollectionsaleImages();
		if (!collection.isEmpty()) {
			return new ResponseEntity<>(collection,  HttpStatus.ACCEPTED);

		} else {
			throw new CollectionSaleImageException(ConstantValues.COLLECTIONSALE_IMAGES_NOT_FOUND);
		}

	}
	
	@PostMapping(path=URLMapping.COLLECTIONSALE_IMAGE_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insert(
			@RequestParam(name=ConstantValues.ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long id,
			@RequestParam(name = ConstantValues.ICON, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile icon,
			@RequestParam(name = ConstantValues.MOBILEVIEW1, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile mobileview1,
			@RequestParam(name = ConstantValues.MOBILEVIEW2, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile mobileview2,
			@RequestParam(name = ConstantValues.DESKTOPVIEW1, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile desktopview1,
			@RequestParam(name = ConstantValues.DESKTOPVIEW2, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile desktopview2
			)
	throws IOException{
		ResponseBean responseBean = new ResponseBean();
		
		if(id== Long.parseLong(ConstantValues.DEFAULT_INT)||
				icon.isEmpty()
				||mobileview1.isEmpty()
				||mobileview2.isEmpty()
				||desktopview1.isEmpty()
				||desktopview2.isEmpty())		
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			
		}
		else {
			this.collectionsaleimgrepo.save(new CollectionsaleImages(id,icon.getBytes(),mobileview1.getBytes(),mobileview2.getBytes(),desktopview1.getBytes(),desktopview2.getBytes()));
			responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
			responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
			responseBean.setTiemstamp(System.currentTimeMillis());
			return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			}
		
		}
	
	@GetMapping(path = URLMapping.COLLECTIONSALE_IMAGES_MAPPING_PATH_WITH_PATH_VARIABLE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<CollectionsaleImages>> getAllCollectionByImageIdList(@PathVariable long imageid) {
		@SuppressWarnings("boxing")
		Optional<CollectionsaleImages> collection = this.collectionsaleimgrepo.getCollectionsaleImagebyid(imageid);
		if (!collection.isPresent()||collection.get()==null) {
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<CollectionsaleImages>>(collection,  HttpStatus.ACCEPTED);
		}

	}
	
	@GetMapping(path = URLMapping.COLLECTIONSALE_IMAGES_MAPPING_PATH_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CollectionsaleImages> getAllCollectionByImageIdList1(@PathVariable Long id) {
		CollectionsaleImages collection = this.collectionsaleimgrepo.getCollectionsaleImagesByIDS(id);
		if (collection== null) {
			
			throw new CollectionSaleException(ConstantValues.COLLECTIONSALE_NOT_FOUND);
		} else {
			return new ResponseEntity<CollectionsaleImages>(collection,  HttpStatus.ACCEPTED);
		}

	}
	
	
}
