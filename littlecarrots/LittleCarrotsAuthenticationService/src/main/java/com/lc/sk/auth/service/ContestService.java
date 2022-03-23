package com.lc.sk.auth.service;

import java.awt.RenderingHints;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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

import com.lc.sk.auth.bean.Contest1Bean;
import com.lc.sk.auth.entities.Contest;
import com.lc.sk.auth.exceptions.subexceptions.ContestNotFoundException;
import com.lc.sk.auth.exceptions.subexceptions.DBValueInsertException;
import com.lc.sk.auth.exceptions.subexceptions.EmailIDNotFoundException;
import com.lc.sk.auth.exceptions.subexceptions.InstaIDNotFoundException;
import com.lc.sk.auth.exceptions.subexceptions.NoAddressLinkedException;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.exceptions.subexceptions.PhoneNumberNotFoundException;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.ContestRepository;
import com.lc.sk.auth.repositories.ContestPaginationRepository;
import com.lc.sk.auth.util.Compression;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.SecurityHttpStatus;
import com.lc.sk.auth.util.URLMapping;







@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.AUTHORIZATION_ROOT_PATH)
public class ContestService { // NO_UCD (unused code)

	@Autowired
	ContestRepository contestRep;
	
	@Autowired
	ContestPaginationRepository contestPaination;
	
	@Autowired
	Compression compress;
	
//	File folder = new File("/home/little/littlecarrots/services/jar_to_deploy/CONTEST");
	File folder = new File("/home/centos/littlecarrots/services/jar_to_deploy/CONTEST");
	//File folder = new File("CONTEST");
	
	@PostMapping(path=URLMapping.CONTEST,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertUser(
			@RequestParam(name = ConstantVariables.EMAILID, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String emailid,
			@RequestParam(name = ConstantVariables.PHONE, required = true, defaultValue = ConstantVariables.DEFAULT_INT_VALUE) long phone,
			@RequestParam(name = ConstantVariables.INSTA_ID, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String instaid,
			@RequestParam(name = ConstantVariables.CHILD_NAME, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String childname,
			@RequestParam(name = ConstantVariables.Age, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String age,
			@RequestParam(name = ConstantVariables.TC, required = true, defaultValue= ConstantVariables.DEFAULT_BOOLEAN+"") boolean tc,
			@RequestParam("images") MultipartFile[] images,
			@RequestParam(name = ConstantVariables.CONTESTMONTHYEAR, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String contestmonthyear)
			
			
			
		 {
		ResponseBean responseBean = new ResponseBean();
		System.out.println(emailid+"  "+phone+"  "+"  "+instaid+" "+childname+" "+contestmonthyear+" "+tc);
		if (emailid .equals(ConstantVariables.DEFAULT_STRING_VALUE)
				 || phone==Long.parseLong(ConstantVariables.DEFAULT_INT_VALUE)
			|| instaid.equals(ConstantVariables.DEFAULT_STRING_VALUE)
			|| childname.equals(ConstantVariables.DEFAULT_STRING_VALUE)
			|| contestmonthyear.equals(ConstantVariables.DEFAULT_STRING_VALUE)
		    || images.length==0
		
		 || tc==ConstantVariables.DEFAULT_BOOLEAN

		) {
			throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
		} else {
			
			Optional<Contest> emailids = contestRep.getByEmailId(emailid);
			if (!emailids.isPresent()) {
				Optional<Contest> instaids = contestRep.getByInstaId(instaid);
				if(!instaids.isPresent()) {
					Optional<Contest> phone1 = contestRep.getByPhone(phone);
					if(!phone1.isPresent()) {	
				if(!this.folder.exists()) {
					this.folder.mkdir();
				}else {
					System.err.println("CONTENT FOLDER EXIST");
					System.err.println("Path:"+this.folder.getAbsolutePath());
				}
				File subfolder = new File(this.folder.getAbsoluteFile()+"/"+emailid+"");
				if(!subfolder.exists()) {
					subfolder.mkdir();
					System.err.println("Sub Folder["+subfolder.getName()+"] CREATED..");
					System.err.println("Path:"+subfolder.getAbsolutePath());
				}else {
					System.err.println("Sub Folder["+subfolder.getName()+"] FOLDER EXIST");
				}
				
				try {
					for(MultipartFile image:images) {
				InputStream in = image.getInputStream();
				System.err.println("FileName: "+image.getOriginalFilename());
				File imagefilename = new File(subfolder+"/"+image.getOriginalFilename());
				OutputStream out = new FileOutputStream(imagefilename);
				FileCopyUtils.copy(in, out);
				out.close();
				in.close();
					}
				java.sql.Timestamp dateinsertion = new java.sql.Timestamp(new java.util.Date().getTime());
				System.out.println(dateinsertion);
				
				Contest con=contestRep.save(new Contest(emailid,phone,instaid,childname,age,tc,emailid,dateinsertion,contestmonthyear));
				
			//	compress.method1(new File(this.folder+"/"+subfolder.getName()+"/"+images.getOriginalFilename()),new File(this.folder+"/"+subfolder.getName()+"/"+images.getOriginalFilename()), RenderingHints.VALUE_INTERPOLATION_BILINEAR, 600,800);
				if (con.getEmailid().contentEquals(emailid)) {
					responseBean.setMessage("Successfully Registered");
					responseBean.setTiemstamp(System.currentTimeMillis());
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					
				} else {
					throw new DBValueInsertException("Failed to register");
				}
				}
				catch(Exception e) {
					System.err.println(e);
				}
				}
					else {
						responseBean.setMessage("Phone Number already registered");
						responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
						responseBean.setTiemstamp(System.currentTimeMillis());
						
					}
				
				}
				
				else {
					responseBean.setMessage("Insta ID already registered");
					responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
					responseBean.setTiemstamp(System.currentTimeMillis());
					
				}
			
			
			} else {
				responseBean.setMessage("Email ID already registered");
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());
				
			}
				
		}
		return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = URLMapping.CONTESTPAGE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Contest>> getAllDetails(HttpServletResponse response,@PathVariable int page, @PathVariable int count){
		
		Pageable pageable = PageRequest.of(page, count);
		List<Contest> pf = this.contestPaination.getAllDetails(pageable);

		return new ResponseEntity<List<Contest>>(pf,HttpStatus.ACCEPTED);
	}

	
	@GetMapping(path = URLMapping.CONTESTPAGE1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getPageCount(@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);
		Page<Contest> pf = this.contestPaination.getPageCount(pageable);
		return new ResponseEntity<Integer>(pf.getTotalPages(),   HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=URLMapping.CONTESTWITHEMAILID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Contest>> getEmailid(@PathVariable String emailid) {
		Optional<Contest> customer = contestRep.getByEmailId(emailid);
		if (!customer.isPresent()) {
			throw new EmailIDNotFoundException(ConstantVariables.EMAIL_ID_NOT_FOUND);
			
		} else {
			return new ResponseEntity<Optional<Contest>>(customer,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path=URLMapping.CONTESTWITHEINSTAID,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Contest>> getnIstaid(@PathVariable String instaid) {
		Optional<Contest> customer = contestRep.getByInstaId(instaid);
		if (!customer.isPresent()) {
			throw new InstaIDNotFoundException(ConstantVariables.INSTA_ID_NOT_FOUND_EXCEPTION);
			
		} else {
			return new ResponseEntity<Optional<Contest>>(customer,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path=URLMapping.CONTESTWITHEPHONENUMBER,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Contest>> getPhoneNumber(@PathVariable long phone) {
		Optional<Contest> customer = contestRep.getByPhone(phone);
		if (!customer.isPresent()) {
			throw new PhoneNumberNotFoundException(ConstantVariables.PHONE_NUMBER_NOT_FOUND);
			
		} else {
			return new ResponseEntity<Optional<Contest>>(customer,  HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping(path = URLMapping.CONTESTPAGEWITHMONTH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Contest1Bean>> getAllDetailsofParticularContest(HttpServletResponse response,@PathVariable String contestmonthyear,@PathVariable int page, @PathVariable int count){
		
		Pageable pageable = PageRequest.of(page, count);
		List<Contest> pf = this.contestPaination.getAllDetailsOfContest(pageable, contestmonthyear);
		List<Contest1Bean> con1=new ArrayList<>();
		if(pf.isEmpty())
		{
			throw new ContestNotFoundException(ConstantVariables.CONTEST_NOT_FOUND);
		}
		for(Contest cf:pf)
		{
			Contest1Bean con2=new Contest1Bean();
			con2.setAge(cf.getAge());
			con2.setChildname(cf.getChildname());
			con2.setContestmonthyear(cf.getContestmonthyear());
			con2.setDateinsertion(cf.getDateinsertion());
			con2.setEmailid(cf.getEmailid());
			con2.setInstaid(cf.getInstaid());
			con2.setPhone(cf.getPhone());
			con2.setTc(cf.isTc());
			List<String> loc =new ArrayList<>();
			File subfolder = new File(this.folder+"/"+cf.getEmailid());
			
			if(subfolder.isDirectory()) {
				File files[] = subfolder.listFiles();
				int i=0;
				String[] filenames = new String[files.length];
				for(File file:files) {
					filenames[i] = file.getName();
					i++;
				}
				con2.setImages(filenames);
			
			}
			
			con1.add(con2);
		}
		return new ResponseEntity<List<Contest1Bean>>(con1,HttpStatus.ACCEPTED);
	}

	
	@GetMapping(path = URLMapping.CONTESTPAGE1WITHMONTH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> getPageCountofParticularContest(@PathVariable String contestmonthyear,@PathVariable int page, @PathVariable int count){
		Pageable pageable = PageRequest.of(page, count);
		Page<Contest> pf = this.contestPaination.getPageCount2(pageable, contestmonthyear);
		return new ResponseEntity<Integer>(pf.getTotalPages(),   HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path= URLMapping.CONTESTALL, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> getALLContests(){

		List<Contest> contest = contestRep.getAll();
		
		
		Set<String> x=new HashSet<>();
		for (Contest s:contest) {
			x.add(s.getContestmonthyear());
		}
		List<String> list = new ArrayList<String>(x);
		return new ResponseEntity<List<String>>(list,   HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path=URLMapping.IMGLOCATION,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getLocation(@PathVariable String emailid,@PathVariable String filename) {
		Optional<Contest> customer = contestRep.getByEmailId(emailid);
		if (!customer.isPresent()) {
			throw new EmailIDNotFoundException(ConstantVariables.EMAIL_ID_NOT_FOUND);
			
		} else {
			
			String subfolder1 = this.folder+"/"+emailid+"/"+filename;
			

			return new ResponseEntity<String>(subfolder1,  HttpStatus.ACCEPTED);
		}
	}
	
	
	
	
}
