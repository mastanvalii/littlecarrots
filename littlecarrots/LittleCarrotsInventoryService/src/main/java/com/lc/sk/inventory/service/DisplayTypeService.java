package com.lc.sk.inventory.service;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.entities.Displaytype;
import com.lc.sk.inventory.exception.subexception.DisplayTypeException;
import com.lc.sk.inventory.repositories.DisplaytypeRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class DisplayTypeService {

	@Autowired DisplaytypeRepository repo;

	
	@GetMapping(path = URLMapping.DISPLAYTYPE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Displaytype>> getAllDetails()
	{
		List<Displaytype> display=this.repo.getAllDisplays();
		if(!display.isEmpty())
		{
			return new ResponseEntity<List<Displaytype>>(display,  HttpStatus.ACCEPTED);
		}
		else
		{
			throw new DisplayTypeException(ConstantValues.DISPLAY_DETAILS_NOT_FOUND);
		}
		
	}
	
	@GetMapping(path = URLMapping.DISPLAYTYPE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Displaytype>> getAllDetails(@PathVariable String display)
	{
		Optional<Displaytype> display1=this.repo.getAllDisplaysbyid(display);
		if(!display1.isPresent() || display1.get() == null)
		{
			throw new DisplayTypeException(ConstantValues.DISPLAY_DETAILS_NOT_FOUND_WITH_GIVEN_ID);
		}
		else
		{
		return new ResponseEntity<Optional<Displaytype>>(display1,  HttpStatus.ACCEPTED);
		}
	}
	
//	@PostMapping(path = URLMapping.DISPLAYTYPE)
//	@ResponseBody
//	public ResponseEntity<ResponseBean> detailsinsert(
//			@RequestParam(name=ConstantValues.DISPLAY,required=true,defaultValue=ConstantValues.DEFAULT_STRING) String display,
//			@RequestParam(name=ConstantValues.DESCRIPTION,required=true,defaultValue=ConstantValues.DEFAULT_STRING) String description
//			)
//	{
//	if(display.equals(ConstantValues.NULL_STRING) || description.equals(ConstantValues.NULL_STRING))
//	{
//		throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES); 
//	}
//	else
//	{
//		Displaytype dis=repo.save(new Displaytype(display,description));
//		if(dis.getDisplay() == display)
//		{
//			ResponseBean response=new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
//			return new ResponseEntity<ResponseBean>(response, header.getHeader(), HttpStatus.ACCEPTED);
//		}
//		else
//		{
//			throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
//		}
//	}
//	}
	
	
	
}
