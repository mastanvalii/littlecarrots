package com.little.carrots.order.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.little.carrots.order.bean.ResponseBean;
import com.little.carrots.order.entity.Coupons;
import com.little.carrots.order.entity.Couponused;
import com.little.carrots.order.exception.subexceptions.CouponException;
import com.little.carrots.order.exception.subexceptions.DBInsertException;
import com.little.carrots.order.exception.subexceptions.NullRequestReceivedException;

import com.little.carrots.order.repositories.CouponsPaginationRepository;
import com.little.carrots.order.repositories.CouponsRepository;
import com.little.carrots.order.repositories.CouponusedRepository;
import com.little.carrots.order.util.ConstantValues;
import com.little.carrots.order.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.ORDER_ROOT_PATH)
public class CouponsService {

	@Autowired
	CouponsRepository couponRep;

	@Autowired
	CouponusedRepository couponUserRepo;

	@Autowired
	CouponsPaginationRepository couponPaginationRep;

	@PostMapping(path=URLMapping.COUPONS,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertCoupons(
			@RequestParam(name=ConstantValues.COUPON,required = true, defaultValue = ConstantValues.DEFAULT_STRING) String coupon,
			@RequestParam(name=ConstantValues.STARTDATE,required = true, defaultValue = ConstantValues.DEFAULT_STRING)String startdate,
			@RequestParam(name=ConstantValues.ENDDATE,required = true, defaultValue = ConstantValues.DEFAULT_STRING)String enddate,
			@RequestParam(name=ConstantValues.DISCOUNT,required = true, defaultValue = ConstantValues.DEFAULT_INT) Long discount,
			@RequestParam(name=ConstantValues.MINBILL,required = true, defaultValue = ConstantValues.DEFAULT_INT)Long minbill,
			@RequestParam(name=ConstantValues.TITLE,required = true, defaultValue = ConstantValues.DEFAULT_STRING)String title
			)
	{
		if( coupon.equals(ConstantValues.DEFAULT_STRING)|| 
			startdate.equals(ConstantValues.DEFAULT_STRING)|| 
			enddate.equals(ConstantValues.DEFAULT_STRING)|| 
			discount == Integer.parseInt(ConstantValues.DEFAULT_INT)||
			minbill == Integer.parseInt(ConstantValues.DEFAULT_INT)||
			title.equals(ConstantValues.DEFAULT_STRING)	
			)
		{
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		}
		else
		{
			if(coupon.length()==8) {
			ResponseBean response=new ResponseBean();
			Date startdate1=null;
			Date enddate1=null;
			try {
				startdate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startdate);
				enddate1 = new SimpleDateFormat("dd/MM/yyyy").parse(enddate);
			} catch (ParseException e) {
			//	e.printStackTrace();
				startdate1 = Date.from(Instant.parse(startdate));
				enddate1 = Date.from(Instant.parse(enddate));
			} 
			java.sql.Timestamp startdate2 = new java.sql.Timestamp(startdate1.getTime());
			java.sql.Timestamp enddate2 = new java.sql.Timestamp(enddate1.getTime());
			
			Optional<Coupons> con= couponRep.getByCoupons(coupon);
			if(!con.isPresent()) {
				Coupons coupons=new Coupons(coupon,startdate2,enddate2,discount,minbill,title);
				couponRep.save(coupons);
				if(coupons.getCoupon()==coupon)
				{
					response.setMessage("Data inserted in DB");
					response.setResponsecode(2000);
					response.setTiemstamp(System.currentTimeMillis());
					
				}
				else
				{
					throw new DBInsertException("DB insert exception");
				}
			}
			else {
				response.setMessage("Coupon already exists");
				response.setResponsecode(2000);
				response.setTiemstamp(System.currentTimeMillis());
			}
			return new ResponseEntity<ResponseBean>(response,  HttpStatus.ACCEPTED);
			
		}else {
			throw new CouponException("Coupon code length must be 8");
		}
		}
	}

	@GetMapping(path = URLMapping.COUPONS, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Coupons>> getAllCoupons() {
		return new ResponseEntity<List<Coupons>>(couponRep.getAllCouponsData(), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = URLMapping.COUPONS1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Coupons>> getAll(@PathVariable int page, @PathVariable int count) {
		Pageable pageable = PageRequest.of(page, count);

		List<Coupons> all = couponPaginationRep.getAllDetails(pageable);
		return new ResponseEntity<List<Coupons>>(all, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = URLMapping.COUPONS_VERIFY, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Coupons> verify(@PathVariable String couponcode, @PathVariable Long customerid,
			@PathVariable Long totalamount) {
		Optional<Coupons> coupon = null;
		coupon = couponRep.getByCoupons(couponcode);
		if (coupon.isPresent()) {
			List<Couponused> customerUsedCoupons = couponUserRepo.getCouponusedByCustomerID(customerid);
			if (customerUsedCoupons.size() > 0 || customerUsedCoupons != null) {
				for (Couponused tempusedcoupons : customerUsedCoupons) {
					if (tempusedcoupons.getCouponid() == coupon.get().getCouponid()) {
						throw new CouponException("Coupon Already Used");
					}
				}
			}
			if (checkExpiry(coupon.get().getStartdate(), coupon.get().getEnddate())) {
				if (coupon.get().getMinbill() <= totalamount) {
					return new ResponseEntity<Coupons>(coupon.get(), HttpStatus.ACCEPTED);
				} else {
					throw new CouponException("Sorry, This coupon work only for " + coupon.get().getMinbill()
							+ "/- and above worth purchases");
				}
			} else {
				throw new CouponException("Coupon Expired");
			}
		} else {
			throw new CouponException("Invalid Coupon");
		}

	}

	@PutMapping(path = URLMapping.APPLIED_COUPON, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateAppliedCoupon(@PathVariable Long couponid, @PathVariable Long customerid) {
		Couponused appliedcoupon = new Couponused();
		appliedcoupon.setCouponid(couponid);
		appliedcoupon.setCustomerid(customerid);
		couponUserRepo.save(appliedcoupon);
	}

	public boolean checkExpiry(Timestamp startDate, Timestamp endDate) {
		boolean flag = false;
		Date currentDate = new Date();
		int checkStartDate = startDate.compareTo(currentDate);
		int checkEndDate = endDate.compareTo(currentDate);
		System.err.println("Check Start Date:" + checkStartDate);
		System.err.println("Check End Date:" + checkEndDate);
		if (checkStartDate < 0 && checkEndDate > 0) {
			flag = true;
		} else if (checkStartDate < 0 && checkEndDate < 0) {
			flag = false;
		} else if (checkStartDate > 0 && checkEndDate > 0) {
			flag = false;
		}
		return flag;
	}

}
