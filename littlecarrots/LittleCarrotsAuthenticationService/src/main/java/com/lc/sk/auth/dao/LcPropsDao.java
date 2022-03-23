/**
 * 
 */
package com.lc.sk.auth.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.sk.auth.entities.Lcprops;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.LcpropsRepository;
import com.lc.sk.auth.util.SecurityHttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mastanvali Shaik
 * LittleCarrotsAuthenticationService
 * 2020
 */
@Component
public class LcPropsDao {

	private final Logger log = LoggerFactory.getLogger(LcPropsDao.class);
	
	@Autowired
	private LcpropsRepository lcprops;
	
	public List<Lcprops> getAll(){
		List<Lcprops> data = new ArrayList<>();
		data = lcprops.getAll();
		if(data.isEmpty()) {
			log.warn("lc props data is empty at LcPropsDao.getAll()..");
		}
		return (List<Lcprops>) lcprops.getAll();
	}
	
	public Optional<Lcprops> getById(long id){
		Optional<Lcprops> data = lcprops.getById(id);
		if(data==null||!data.isPresent()) {
			log.warn("lc props data is empty at LcPropsDao.getById("+id+")..");
		}
		return lcprops.getById(id);
	}
	
	public ResponseBean updateStatus(long id, boolean status) {
		ResponseBean responseBean = null;
		Optional<Lcprops> lcprop = lcprops.findById(id);
		if(lcprop.isPresent()) {
			if(lcprop.get().getPropid() == id) {
				lcprop.get().setStatus(status);
				Lcprops temp = lcprops.save(lcprop.get());
				if(temp.getPropid()== id && temp.isStatus()== status) {
					responseBean = new ResponseBean("Property Status Update Sucess id:"+id+", status:"+status, SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
					log.info(responseBean.toString());
				}else
				{
					responseBean = new ResponseBean("Property Status Update Fail id:"+id+", status:"+status, SecurityHttpStatus.NOT_FOUND, System.currentTimeMillis());
					log.warn(responseBean.toString());
				}
			}else {
				responseBean = new ResponseBean("Invalid Property Id  id:"+id+", status:"+status,  SecurityHttpStatus.NOT_FOUND, System.currentTimeMillis());
				log.warn(responseBean.toString());
			}
		}else {
			responseBean = new ResponseBean("Invalid Property Id id:"+id+", status:"+status,  SecurityHttpStatus.NOT_FOUND, System.currentTimeMillis());
			log.warn(responseBean.toString());
		}
		return responseBean;
	}
	
	private Lcprops getByProp(String prop) {
		Lcprops lc = null;
		List<Lcprops> lcs = getAll();
		if(!lcs.isEmpty()) {
			for(Lcprops temp: lcs) {
				if(temp.getProp()!=null&&!temp.getProp().equals("")&&temp.getProp().equals(prop)) {
					lc = temp;
				}
			}
		}
		return lc;
	}
	
	public boolean getPropStatus(String prop) {
		return getByProp(prop).isStatus();
	}
	
	public ResponseBean insertNewProp(String prop, boolean status) {
		ResponseBean rb = null;
		Lcprops obj= lcprops.save(new Lcprops(prop, status));
		if(obj.getProp().equals(prop)) {
			rb = new ResponseBean("New Property added", SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
		}else {
			rb = new ResponseBean("New Property adding Failed", SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
		}
		return rb;
	}
	
	
}
