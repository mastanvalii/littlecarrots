package com.lilttle.carrots.imgs.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lilttle.carrots.imgs.beans.FileData;
import com.lilttle.carrots.imgs.entity.Imagelocation;
import com.lilttle.carrots.imgs.repositories.ImagelocationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class ImageUrlCache {
	private final Logger log = LoggerFactory.getLogger(ImageUrlCache.class);
	private @Autowired
	ImagelocationRepo repo;
	
	private List<Imagelocation> dbdata = new ArrayList<>();
	private ConcurrentHashMap<Long, FileData> cachemap = new ConcurrentHashMap<>();
	
	
	
	@PostConstruct
	 @Scheduled(fixedDelay=450000)
	public void ImageCacheLoader() {
		log.info("--------IMAGE CACHE LOADING....--------------");
		List<Long> productids = repo.getInfo3();
		dbdata = repo.getAllInfo1();
		if(productids.size()>0) {
			for(Long productid:productids) {
				List<Imagelocation> list = getFileData(productid);
				if(list.size()>0) {
					String[] filenames = new String[list.size()];
					
					for(int i=0;i<list.size();i++) {
				//		System.out.println("FileName:"+list.get(i).getImage());
						filenames[i] = list.get(i).getImage();
					}
					cachemap.put(productid, new FileData(productid+"", filenames, "Image Found"));
				}else {
					cachemap.put(productid, new FileData(productid+"", "Product ID Not Found"));
				}
			
			}
		}
		log.info("SIZE OF CACHE: "+ cachemap.size());
		log.info("--------IMAGE CACHE LOADING COMPLETE--------------");
	}
	
	public List<Imagelocation> getFileData(long productid){
		List<Imagelocation> data = new ArrayList<>();
		for(Imagelocation lc:dbdata) {
			if(productid==lc.getProductid()) {
				data.add(lc);
			}
		}
		
		return data;
	}
	
	public FileData getData1(long productid) {
		FileData fd = new FileData();
		FileData temp = cachemap.get(productid);
		if(temp!=null) {
		if(temp.getImages()!=null||temp.getImages().length!=0) {
			fd = temp;
			fd.setImages(new String[] {temp.getImages()[0]});
		}
		}else {
			fd = new FileData(productid+"", "Product ID Not Found");
		}
		
		return fd;
	}
	
	public FileData getData2(long productid) {
		return cachemap.get(productid);
	}

	public List<FileData> getAllFileData(){
		return new ArrayList<>(cachemap.values());
	}
}
