package com.lilttle.carrots.imgs.service;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
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

import com.google.gson.Gson;
import com.lilttle.carrots.imgs.beans.CompressBean;
import com.lilttle.carrots.imgs.beans.DbBean;
import com.lilttle.carrots.imgs.beans.FileData;
import com.lilttle.carrots.imgs.beans.ResponseBean;
import com.lilttle.carrots.imgs.cache.ImageUrlCache;
import com.lilttle.carrots.imgs.entity.Imagelocation;
import com.lilttle.carrots.imgs.repositories.ImagelocationRepo;
import com.lilttle.carrots.imgs.repositories.Partition;
import com.lilttle.carrots.imgs.util.Compression;
import com.lilttle.carrots.imgs.util.URLMapping;

import net.coobird.thumbnailator.Thumbnails;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(value = URLMapping.ROOT+URLMapping.VERSION)
public class ImageService {
	@Autowired
    private ServletContext servletContext;
	
	@Autowired
	ImagelocationRepo repo;
	
	@Autowired
	Compression compress;
	
	
	@Autowired
	ImageUrlCache cache;
	
	Logger logger = LoggerFactory.getLogger(ImageService.class);

//	File folder = new File("D:/LITTLECARROTS/DEV/Services/GIT/LittleCarrotsImagesService2/CONTENT");	
//	File folder = new File("CONTENT");
	File folder = new File("/home/centos/littlecarrots/CONTENT");
	
	
	

	@CrossOrigin(origins = "*")
	@PostMapping(path=URLMapping.UPLOAD)	
	public ResponseEntity<ResponseBean> PostMethod1(@RequestParam("items[]") MultipartFile[] img,
			@PathVariable long productid) throws Exception{
		ResponseBean response = null;
		try{
		//folder = new File("CONTENT");
		if(!this.folder.exists()) {
			this.folder.mkdir();
		}else {
			System.err.println("CONTENT FOLDER EXIST");
			System.err.println("Path:"+this.folder.getAbsolutePath());
		}
		File subfolder = new File(this.folder.getAbsoluteFile()+"/"+productid+"");
		if(!subfolder.exists()) {
			subfolder.mkdir();
			System.err.println("Sub Folder["+subfolder.getName()+"] CREATED..");
			System.err.println("Path:"+subfolder.getAbsolutePath());
		}else {
			System.err.println("Sub Folder["+subfolder.getName()+"] FOLDER EXIST");
		}
		
		for(MultipartFile file:img) {
			InputStream in = file.getInputStream();
			System.err.println("FileName: "+file.getOriginalFilename());
			File imagefilename = new File(subfolder+"/"+file.getOriginalFilename());
			OutputStream out = new FileOutputStream(imagefilename);
			FileCopyUtils.copy(in, out);
			out.close();
			in.close();
			repo.save(new Imagelocation(productid, file.getOriginalFilename()));
			compress.method1(new File(this.folder+"/"+subfolder.getName()+"/"+file.getOriginalFilename()),new File(this.folder+"/"+subfolder.getName()+"/"+file.getOriginalFilename()), RenderingHints.VALUE_INTERPOLATION_BILINEAR, 600,800);
			Thumbnails.of(new File(this.folder+"/"+subfolder.getName()+"/"+file.getOriginalFilename())).size(285, 300).toFile(new File(this.folder+"/"+subfolder.getName()+"/thumbnail.jpg"));
		}		
		
		response = new ResponseBean("Success", 2000, System.currentTimeMillis());
		}catch(IOException ee) {
			response = new ResponseBean(ee.getMessage(), 4000, System.currentTimeMillis());
			ee.printStackTrace();
		}
		/*
		System.err.println("Folder Location:"+folder.getAbsolutePath());
		System.err.println("--------------------------List of file in folder--------------------------");
		File[] files = this.folder.listFiles();
		for(File file:files) {
		System.err.println(file.getName());
		}
		System.err.println("--------------------------------------------------------------------------");
		*/
		//InputStream in = getClass().getResourceAsStream(message);
		//IOUtils.copy(in, response.getOutputStream());
		
		return new ResponseEntity<ResponseBean>(response, HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path=URLMapping.GET)
	public void getImage(HttpServletResponse response, @PathVariable String productid, @PathVariable String name)
	{
		try {
			
		logger.info("User Requested the object of: " + productid+" FILE:"+name);	
		InputStream in = new FileInputStream(this.folder+"/"+productid+"/"+name);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);	
	//	IOUtils.copy(in, response.getOutputStream());
		
		StreamUtils.copy(in, response.getOutputStream());
		logger.info("Server Respond the object of: " + productid+" FILE:"+name);
		
		}catch(Exception e) {
			ResponseBean rb = new ResponseBean(e.getMessage(),4000, System.currentTimeMillis());
			
			try {
				String reply = new Gson().toJson(rb);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(reply);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(path=URLMapping.GETLIST)
	@ResponseBody
	public ResponseEntity<FileData> getProductList(@PathVariable String productid)
	{		
		FileData fd =  cache.getData1(Long.parseLong(productid));
		return  new ResponseEntity<FileData>(fd, HttpStatus.ACCEPTED);
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(path=URLMapping.GETLIST1)
	@ResponseBody
	public ResponseEntity<FileData> getProductList1(@PathVariable String productid)
	{
		FileData fd = new FileData();
					List<Imagelocation> list = repo.getInfo1(Long.parseLong(productid));
					if(list.size()>0) {
						
							String[] filenames = new String[list.size()];
							for(int i=0;i<list.size();i++) {
								filenames[i] = list.get(i).getImage();
							}
							fd.setProductid(productid);
							fd.setImages(filenames);
							fd.setMessage("Images Found");
					
						
					}else {
						fd.setProductid(productid);
						fd.setMessage("Product ID Not Found");
					}
				
				return  new ResponseEntity<FileData>(fd, HttpStatus.ACCEPTED);
		//return  new ResponseEntity<FileData>(cache.getData2(Long.parseLong(productid)), HttpStatus.ACCEPTED);
	}	


	@CrossOrigin(origins = "*")
	@GetMapping(path=URLMapping.GETPRODUCTIDS)
	@ResponseBody
	public ResponseEntity<List<Long>> getProductIds()
	{		
		List<Long> il = repo.getInfo3();		
		return  new ResponseEntity<List<Long>>(il, HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value=URLMapping.IMG10)
	@ResponseBody
	public ResponseEntity<Integer> getPageCount(@PathVariable int page, @PathVariable int count){
		List<Long> il = repo.getInfo3();
		List<List<Long>> pf = Partition.ofSize(il, count);
		return new ResponseEntity<Integer>(pf.size(), HttpStatus.ACCEPTED);
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value=URLMapping.IMG11)
	@ResponseBody
	public ResponseEntity<List<FileData>> getImageByPage(@PathVariable int page, @PathVariable int count){
		List<Long> il = repo.getInfo3();
		List<FileData> data = new CopyOnWriteArrayList<>();
		for(Long num: il) {
			data.add(getProductInfo(num+""));
		}
		List<List<FileData>> finalData = Partition.ofSize(data, count);
		
		return new ResponseEntity<List<FileData>>(finalData.get(page), HttpStatus.ACCEPTED);
				
		
	}
	
	public FileData getProductInfo(String productid) {
		FileData fd = null;
		Optional<Imagelocation> il = repo.getInfo2(Long.parseLong(productid));
	//	System.err.println(il.get());
		if(il.isPresent()) {
			 return new FileData(il.get().getProductid()+"", new String[] {il.get().getImage()}, "Images Found");
		//	fd.setProductid();
		//	String[] ab = new String[1];
		//	ab[0] = il.get().getImage();
		//	fd.setImages();
		//	fd.setMessage();
		}else {
			 return new FileData(il.get().getProductid()+"", new String[] {il.get().getImage()}, "Product ID Not Found");
		//	fd.setProductid(productid);
		//	fd.setMessage();
		}
		//return fd;
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping(value=URLMapping.IMG12)
	@ResponseBody
	public ResponseEntity<List<CompressBean>> compresstall(HttpServletResponse response){
		List<CompressBean> temp = new CopyOnWriteArrayList<>();
		try {
			File[] subfolders = this.folder.listFiles();
			for(File subfolder:subfolders) {
				if(subfolder.isDirectory()) {
					File [] imgs = subfolder.listFiles();
					
					for(File img :imgs) {
						System.err.println("File:"+this.folder+"/"+subfolder.getName()+"/"+img.getName());
						CompressBean cb = new CompressBean();
						cb.setProductid(subfolder.getName());
						cb.setFilename(img.getName());
						cb.setBeforesize(img.length()+"");						
						compress.method1(new File(this.folder+"/"+subfolder.getName()+"/"+img.getName()),new File(this.folder+"/"+subfolder.getName()+"/"+img.getName()), RenderingHints.VALUE_INTERPOLATION_BILINEAR, 600,800);
						cb.setAftersize(img.length()+"");
						temp.add(cb);
						

					}
					
					Thumbnails.of(new File(this.folder+"/"+subfolder.getName()+"/"+imgs[0].getName())).size(285, 300).toFile(new File(this.folder+"/"+subfolder.getName()+"/thumbnail.jpg"));
					
				}
			}
		
		}catch(Exception e) {
			
			e.printStackTrace();
ResponseBean rb = new ResponseBean(e.getMessage(),4000, System.currentTimeMillis());
			
			try {
				String reply = new Gson().toJson(rb);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(reply);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return new ResponseEntity<List<CompressBean>>(temp, HttpStatus.ACCEPTED);
	}
	
	

	@CrossOrigin(origins = "*")
	@GetMapping(path=URLMapping.IMG13)
	@ResponseBody
	public ResponseEntity<List<FileData>>  getAllImageList()
	{
		return new ResponseEntity<List<FileData>>(cache.getAllFileData(), HttpStatus.ACCEPTED);
		
	}
	

	
}
