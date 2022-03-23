package com.lc.sk.inventory.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;

import com.lc.sk.inventory.bean.GBlogBean;
import com.lc.sk.inventory.bean.GBlogBean1;
import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.GrannyBlog;
import com.lc.sk.inventory.exception.subexception.BlogreportException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.GBlogException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.GblogRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class GrannyBlogService {

	
	@Autowired
	GblogRepository gblogRepository;


	public GrannyBlogService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* Get all Granny Blog report  list */
	@GetMapping(path = URLMapping.GRANNYBLOG_MAPPING_PATH , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<GrannyBlog>> getAllGrannyBlogDetails() {
		List<GrannyBlog> grannyBlog = (List<GrannyBlog>) this.gblogRepository.getAll();
		if (grannyBlog.isEmpty()) {
			throw new GBlogException(ConstantValues.GRANNY_BLOG_NOT_FOUND);
		} else {
			return new ResponseEntity<List<GrannyBlog>>(grannyBlog, HttpStatus.ACCEPTED);
		}
	}

	
	/* GrannyBlog details by blogid */
	@GetMapping(path = URLMapping.GRANNYBLOG_MAPPING_PATH_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<GrannyBlog>> getByBId(@PathVariable long blogid) {
		Optional<GrannyBlog> grannyBlog = this.gblogRepository.getByblogid(blogid);
		if (!grannyBlog.isPresent()||grannyBlog.get() == null) {
			throw new GBlogException(ConstantValues.GRANNYBLOG_LIST_NOT_FOUND_GIVEN_BLOGID);
		} else {
			return new ResponseEntity<Optional<GrannyBlog>>(grannyBlog, HttpStatus.ACCEPTED);
		}
	}
	
	//insertion 
			@PostMapping(path = URLMapping.GRANNYBLOG_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
			@ResponseBody	
			public ResponseEntity<ResponseBean> addList(
					
					@RequestParam(name = ConstantValues.BID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long bid,
					@RequestParam(name = ConstantValues.TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String title,
					@RequestParam(name = ConstantValues.SUB_TITLE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String subtitle,
					@RequestParam(name = ConstantValues.INFORMATION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String information,					
					@RequestParam(name = ConstantValues.POSTER_IMAGE, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile thumbimage,
					@RequestParam(name = ConstantValues.MOBILE_IMAGE, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile mobileimage,
					@RequestParam(name = ConstantValues.DESKTOP_IMAGE, required = true, defaultValue = ConstantValues.DEFAULT_INT) MultipartFile desktopimage
				// @RequestParam(name = ConstantValues.STATUS, required = true, defaultValue = ConstantValues.DEFAULT_BOOLEAN+"") boolean status
													
					) throws IOException{
				ResponseBean responseBean = new ResponseBean();
				
							
				if(bid == Long.parseLong(ConstantValues.DEFAULT_INT)
						||title.equals(ConstantValues.DEFAULT_STRING) 
						||subtitle.equals(ConstantValues.DEFAULT_STRING) 
						||information.equals(ConstantValues.DEFAULT_STRING) 
						//||dateofinsertion.equals(ConstantValues.DEFAULT_STRING)
						||thumbimage.isEmpty())
						//||status.equals(ConstantValues.DEFAULT_BOOLEAN + ""))
						
						{
					throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
					
				}
				else {
					
					Date date=new Date();
					if(title!=null && subtitle!=null && information!=null && thumbimage!=null)
					{
						this.gblogRepository.save(new GrannyBlog(bid,title,subtitle,information,date,thumbimage.getBytes(),mobileimage.getBytes(),desktopimage.getBytes(),true ));
					responseBean.setMessage(ConstantValues.DATA_INSERTED_IN_DB);
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					responseBean.setTiemstamp(System.currentTimeMillis());
					return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);
					}else {
					throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
				}
				}
				}
				
				
				
				@PutMapping(path = URLMapping.GRANNYBLOG_MAPPING_PATH_STATUS, produces = MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public ResponseEntity<ResponseBean> enableBlogStatus(@PathVariable long blogid, @PathVariable boolean status) 
				{
					Optional<GrannyBlog> grannyBlog = this.gblogRepository.findById(blogid);
					if(grannyBlog.isPresent())
					{
					
						grannyBlog.get().setStatus(status);
						GrannyBlog blog = this.gblogRepository.save(grannyBlog.get());
						if (blog.isStatus() == status) {
							ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
									SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
							return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);
						}
						 else {
							throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
						}
					}
					else
					{
						throw new GBlogException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
					}
				}
				
				
				
				@GetMapping(path = "/pp7/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE )
				@ResponseBody
				public ResponseEntity<List<GrannyBlog>> getAllUsersPaging(@PathVariable int page, @PathVariable int count) {
					Pageable pageable = PageRequest.of(page, count);
					List<GrannyBlog> grannyBlog = this.gblogRepository.getAllGrannyBlogListPaging(pageable);
					if (grannyBlog.size() > 0) {
						return new ResponseEntity<List<GrannyBlog>>(grannyBlog, HttpStatus.ACCEPTED);
					} else {
						throw new BlogreportException(ConstantValues.GRANNY_BLOG_NOT_FOUND);
					}
				}
				
				@GetMapping(path = "/pp8/{page}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public ResponseEntity<Integer> getUsersPageNumbers(@PathVariable int page, @PathVariable int count){
				Pageable pageable = PageRequest.of(page, count);
				Page<GrannyBlog> p1 = this.gblogRepository.getallGrannyBlogpagingcount(pageable);
				return new ResponseEntity<Integer>(p1.getTotalPages(), HttpStatus.ACCEPTED);
				}
				
				
				@GetMapping(path = URLMapping.GRANNYBLOG_LATEST_5_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public ResponseEntity<List<GBlogBean1>> getLatest5Details() throws InterruptedException, ExecutionException {
					CompletableFuture<List<GBlogBean1>> ss = CompletableFuture.supplyAsync(()->(List<GBlogBean1>) this.gblogRepository.getLatest5());
				//	List<GBlogBean1> grannyBlog = (List<GBlogBean1>) this.gblogRepository.getLatest5();
					if (ss.get().isEmpty()) {
						throw new GBlogException(ConstantValues.GRANNY_BLOG_NOT_FOUND);
					} else {
						return new ResponseEntity<List<GBlogBean1>>(ss.get(), HttpStatus.ACCEPTED);
					}
				}
				
				@GetMapping(path = URLMapping.GRANNYBLOG__NEXT_LATEST_5_MAPPING_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
				@ResponseBody
				public ResponseEntity<List<GBlogBean>> getNextLatest5(@PathVariable long blogid) {
					List<GBlogBean> grannyBlog = (List<GBlogBean>)this.gblogRepository.getNextlatest5(blogid);
					if (grannyBlog.isEmpty()) {
						throw new GBlogException(ConstantValues.GRANNY_BLOG_NOT_FOUND);
					} else {
						return new ResponseEntity<List<GBlogBean>>(grannyBlog, HttpStatus.ACCEPTED);
					}
				}
				
				
						
				
}

			

