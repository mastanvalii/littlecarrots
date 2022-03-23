package com.lc.sk.inventory.service;

import java.util.List;
import java.util.Optional;

//import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;

import com.lc.sk.inventory.entities.Materialtypes;


import com.lc.sk.inventory.exception.subexception.DBDataNotUpdatedException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.MaterialtypesException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;

import com.lc.sk.inventory.repositories.MaterialtypesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)

public class MaterialtypesService {
	@Autowired
	MaterialtypesRepository materialtypesRepository;
	

	
	public MaterialtypesService() {
		// TODO Auto-generated constructor stub
	}

	
	@GetMapping(path = URLMapping.GET_ALL_MATERIALTYPES_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Materialtypes>> getAllMaterialtypes() {
		List<Materialtypes> materialtypes = (List<Materialtypes>) this.materialtypesRepository.getAllMaterialTypeDetails();
		if (materialtypes.isEmpty()) {
			throw new MaterialtypesException(ConstantValues.EMPTY_MATERIALTYPES_LIST);
		} else {
			return new ResponseEntity<List<Materialtypes>>(materialtypes,  HttpStatus.ACCEPTED);
		}

	}

	@GetMapping(path = URLMapping.GET_ALL_MATERIALTYPES_LIST_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Materialtypes>> getById(@PathVariable Long materialid
			) {
		Optional<Materialtypes> materialtypes = this.materialtypesRepository.getMaterialTypeById(materialid);
		if (materialtypes.isPresent()  &&materialtypes.get() != null) {
			return new ResponseEntity<Optional<Materialtypes>>(materialtypes,  HttpStatus.ACCEPTED);
		} else {
			throw new MaterialtypesException(ConstantValues.MATERIALTYPES_NOT_FOUND_WITH_GIVEN_ID);
		}
	}	
	
	
	//insertion 
		@PostMapping(path = URLMapping.MATERIALTYPES_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody	
		public ResponseEntity<ResponseBean> addList(
				@RequestParam(name = ConstantValues.MATERIAL_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String materialname,
				@RequestParam(name = ConstantValues.DESCRIPTION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String description,
				@RequestParam(name = ConstantValues.OCCASION_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) int occasionid,
				@RequestParam(name = ConstantValues.SEASON_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long seasonid,
				@RequestParam(name = ConstantValues.CATEGORY_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long catid
				
				) {
			ResponseBean responseBean = new ResponseBean();
			if (materialname.equals(ConstantValues.DEFAULT_STRING)
					|| description.equals(ConstantValues.DEFAULT_STRING)
					|| occasionid == Long.parseLong(ConstantValues.DEFAULT_INT)
					||seasonid == Long.parseLong(ConstantValues.DEFAULT_INT)
					|| catid == Long.parseLong(ConstantValues.DEFAULT_INT))
					 {
				throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
			} else {
				
				Materialtypes materialtypes = new Materialtypes(materialname,description,occasionid,seasonid,catid);
				materialtypes = this.materialtypesRepository.save(materialtypes);
				if (materialtypes.getMaterialname().equals(materialname)) {
					responseBean.setMessage(ConstantValues.MATERIALTYPE_INSERTION_SUCCESS);
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					responseBean.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				} else {
					throw new DBInsertException(ConstantValues.MATERIALTYPE_INSERTION_HAS_ISSUE);
				}

			}

		}
		
		

		 @PutMapping(path = URLMapping.MATERIALTYPES_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
		 @ResponseBody
		 public ResponseEntity<ResponseBean> update(
				 @RequestParam(name = ConstantValues. MATERIAL_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) Long materialid,
				 @RequestParam(name = ConstantValues.MATERIAL_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String materialname,
					@RequestParam(name = ConstantValues.DESCRIPTION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String description,
					@RequestParam(name = ConstantValues.OCCASION_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) int occasionid,
					@RequestParam(name = ConstantValues.SEASON_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long seasonid,
					@RequestParam(name = ConstantValues.CATEGORY_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long catid
					){
			 if (materialid== Long.parseLong(ConstantValues.DEFAULT_INT)|| materialname.equals(ConstantValues.DEFAULT_STRING)
						|| description.equals(ConstantValues.DEFAULT_STRING)
						|| occasionid == Long.parseLong(ConstantValues.DEFAULT_INT)
						||seasonid == Long.parseLong(ConstantValues.DEFAULT_INT)
						|| catid == Long.parseLong(ConstantValues.DEFAULT_INT))
						 {
					throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
				} else {
			 Optional<Materialtypes> materialtypes =  this.materialtypesRepository.getMaterialTypeById(materialid);
			  if(materialtypes.isPresent())
			  {
				  materialtypes.get().setMaterialid(materialid);
				 materialtypes.get().setMaterialname(materialname);
				  materialtypes.get().setDescription(description);
				 materialtypes.get().setOcid(occasionid);
				 materialtypes.get().setSeasonid(seasonid);
				 materialtypes.get().setCatid(catid);
				 Materialtypes material=this.materialtypesRepository.save(materialtypes.get());		
			 if (material.getMaterialid() == materialid)
			  {
					ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
				}
			  else 
			  {
					throw new DBDataNotUpdatedException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			  }  
		  }
			  else {
					throw new MaterialtypesException(ConstantValues.MATERIALTYPES_NOT_FOUND_WITH_GIVEN_ID);
				}
		 
	}}
	}
