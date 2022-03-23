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
import com.lc.sk.inventory.entities.Blogreport;
import com.lc.sk.inventory.exception.subexception.BlogreportException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;

import com.lc.sk.inventory.repositories.BlogreportRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class BlogreportService {

	@Autowired
	BlogreportRepository blogreportRepository;



	public BlogreportService() {
		super();
	}

	/* Get all Blog report list */
	@GetMapping(path = URLMapping.BLOGREPORT_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Blogreport>> getAllBlogReportDetails() {
		List<Blogreport> blogreport = (List<Blogreport>) this.blogreportRepository.findAll();
		if (blogreport.isEmpty()) {
			throw new BlogreportException(ConstantValues.BLOG_REPORTS_NOT_FOUND);
		} else {
			return new ResponseEntity<List<Blogreport>>(blogreport, HttpStatus.ACCEPTED);
		}
	}

	/* Blogreport details by bid */
	@GetMapping(path = URLMapping.BLOGREPORT_MAPPING_PATH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Blogreport>> getByBId(@PathVariable long bid) {
		@SuppressWarnings("boxing")
		Optional<Blogreport> blogreport = this.blogreportRepository.findById(bid);
		if (!blogreport.isPresent() || blogreport.get() == null) {
			throw new BlogreportException(ConstantValues.BLOGREPORT_LIST_NOT_FOUND_GIVEN_BID);
		} else {
			return new ResponseEntity<Optional<Blogreport>>(blogreport, HttpStatus.ACCEPTED);
		}
	}

	// insertion
	@PostMapping(path = URLMapping.BLOGREPORT_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name = ConstantValues.AUTHOR_NAME, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String authorname,
			@RequestParam(name = ConstantValues.AUTHOR_IMAGE, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile authorimage,
			@RequestParam(name = ConstantValues.DESCRIPTION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String description)
			throws IOException {
		ResponseBean responseBean = new ResponseBean();
		// Blogreport blogreport=null;
		if (authorname.equals(ConstantValues.DEFAULT_STRING) || authorimage.isEmpty()
				|| description.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			// List<Blogreport> blog=new CopyOnWriteArrayList<Blogreport>();

			if (authorname != null && description != null) {

				// for(MultipartFile f:authorimage)
				// {
				// blog.add(new Blogreport(authorname,f.getBytes(),description));
				//
				// }
				this.blogreportRepository.save(new Blogreport(authorname, authorimage.getBytes(), description));
				responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}
	}

	
}
