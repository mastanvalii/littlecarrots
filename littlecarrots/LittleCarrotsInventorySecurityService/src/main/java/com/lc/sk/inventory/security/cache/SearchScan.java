package com.lc.sk.inventory.security.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.sk.inventory.security.beans.ProductAge;
import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;
import com.lc.sk.inventory.security.rest.PricesTableRestService;
import com.lc.sk.inventory.security.rest.ProductAgeRestService;

@Component
public class SearchScan {
	
	@Autowired
	ProductAgeRestService kidsagelist;
	

	
	public <T> Set<T> mergeSet(Set<T> obj1, Set<T> obj2){
		return new CopyOnWriteArraySet<T>() {{
			addAll(obj1);
			addAll(obj2);
		}};
	}
	
	public Set<ProductFullDetailsWithImage> getExactCategory(String[] category, Set<ProductFullDetailsWithImage> data1){
		Set<ProductFullDetailsWithImage> tmp = new HashSet<>();
		System.err.println("Received Size of data:"+data1.size());
		for(String temp: category) {
			System.out.println("Received Query:"+temp);
			for(ProductFullDetailsWithImage prd: data1) {
				if(prd.getProductsubtitle().toLowerCase().contains(temp.toLowerCase())) {
					tmp.add(prd);
				}
				/*
				if(prd.getCategory().toLowerCase().contains(temp.toLowerCase())
						|| prd.getSubcategory().toLowerCase().contains(temp.toLowerCase())
						||prd.getProductsubtitle().toLowerCase().contains(temp.toLowerCase())) 
					{
					tmp.add(prd);
				}
				
				*/
			}
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getExactCategory(), Return list Size:"+tmp.size());
		System.out.println("##########################################################################");
		
		return tmp;
	}

	public Set<ProductFullDetailsWithImage> getSearchCategory(String[] category, Set<ProductFullDetailsWithImage> data){
		Set<ProductFullDetailsWithImage> categories = new CopyOnWriteArraySet<>();
		for(String temp: category) {
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getProductsubtitle().toLowerCase().contains(temp.toLowerCase())) {
					categories.add(prd);
				}
			}
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getProducttitle().toLowerCase().contains(temp.toLowerCase())) {
					categories.add(prd);
				}
			}
			
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getOccasion().toLowerCase().contains(temp.toLowerCase())) {
					categories.add(prd);
				}
			}
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getSeason().toLowerCase().contains(temp.toLowerCase())) {
					categories.add(prd);
				}
			}
			
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getProducttitle().toLowerCase().contains(temp.toLowerCase())) {
					categories.add(prd);
				}
			}
			
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getProductsubtitle().toLowerCase().contains(temp.toLowerCase())) {
					categories.add(prd);
				}
			}
			
			
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getSearchCategory(), Return list Size:"+categories.size());
		System.out.println("##########################################################################");
		return categories;
	}
	
	public Set<ProductFullDetailsWithImage> getSearchSubCategory(String[] subcategory, List<ProductFullDetailsWithImage> data){
		Set<ProductFullDetailsWithImage> subcategories = new CopyOnWriteArraySet<>();
		for(String temp: subcategory) {
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getSubcategory().toLowerCase().contains(temp.toLowerCase())) {
					subcategories.add(prd);
				}
			}
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getProductsubtitle().toLowerCase().contains(temp.toLowerCase())) {
					subcategories.add(prd);
				}
			}
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getProducttitle().toLowerCase().contains(temp.toLowerCase())) {
					subcategories.add(prd);
				}
			}
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getSearchSubCategory(), Return list Size:"+subcategories.size());
		System.out.println("##########################################################################");
		return subcategories;
	}
	
	public Set<ProductFullDetailsWithImage> getSearchByGender(String[] gender, Set<ProductFullDetailsWithImage> data){
		
		Set<ProductFullDetailsWithImage> genderdata = new CopyOnWriteArraySet<>();
		for(String temp: gender) {
			System.out.println("@@@@@@@@@@@ LOOKING FOR :"+temp);
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getGenderid().toLowerCase().contains(temp.toLowerCase())) {
					genderdata.add(prd);
				}
			}
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getSearchByGender(), Return list Size:"+genderdata.size());
		System.out.println("##########################################################################");
		return genderdata;
	}

	public Set<ProductFullDetailsWithImage> getSearchByAge(String[] age, Set<ProductFullDetailsWithImage> data){
		List<ProductAge> pa = kidsagelist.getAll();
		Set<ProductFullDetailsWithImage> genderdata = new CopyOnWriteArraySet<>();
		List<String> newAge = new ArrayList<>();
		for(int i=0 ;i<age.length;i++) {
			switch(age[i]) {
			case "NewBorn":
				newAge.add("0-3M");
				newAge.add("3-6M");
				newAge.add("6-9M");	
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "newborn":
				newAge.add("0-3M");
				newAge.add("3-6M");
				newAge.add("6-9M");	
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "0 - 1":
				newAge.add("0-3M");
				newAge.add("3-6M");
				newAge.add("6-9M");	
				newAge.add("0-6M");	
				newAge.add("6-12M");
				newAge.add("9-12M");	
				newAge.add("0-1Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "1 - 2":
				newAge.add("12-18M");	
				newAge.add("18-24M");	
				newAge.add("1-2Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "2 - 3":
				newAge.add("2-3Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "3 - 4":
				newAge.add("3-4Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "4 - 5":
				newAge.add("4-5Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "5 - 6":
				newAge.add("5-6Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "6 - 7":
				newAge.add("6-7Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "7 - 8":
				newAge.add("7-8Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "8+":
				newAge.add("8-9Y");
				newAge.add("9-10Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
				
			case "0-1":
				newAge.add("0-3M");
				newAge.add("3-6M");
				newAge.add("6-9M");	
				newAge.add("0-6M");	
				newAge.add("6-12M");
				newAge.add("9-12M");	
				newAge.add("0-1Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "1-2":
				newAge.add("12-18M");	
				newAge.add("18-24M");	
				newAge.add("1-2Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "2-3":
				newAge.add("2-3Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "3-4":
				newAge.add("3-4Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "4-5":
				newAge.add("4-5Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "5-6":
				newAge.add("5-6Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "6-7":
				newAge.add("6-7Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;
			case "7-8":
				newAge.add("7-8Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;	
			case "6":
				newAge.add("6-7Y");
				newAge.add("7-8Y");
				newAge.add("8-9Y");
				newAge.add("8-10Y");
				newAge.add("10-11Y");
				newAge.add("10-12Y");
				newAge.add("12-14Y");
				newAge.add("14-15Y");
				newAge.add("14-16Y");
				newAge.add("16-17Y");
				newAge.add("Free Size");
				newAge.add("All Ages");
				break;	
				default:
					newAge.add("0-3M");
					newAge.add("3-6M");
					newAge.add("6-9M");	
					newAge.add("0-1Y");
					newAge.add("1-2Y");
					newAge.add("2-3Y");
					newAge.add("3-4Y");
					newAge.add("4-5Y");
					newAge.add("Free Size");
					newAge.add("All Ages");
					break;
			
			}
			
		}
		age = new String[newAge.size()];
		for(int x = 0 ;x<age.length;x++) {
			age[x] = newAge.get(x);
		}
		for(String temp: age) {			
			for(ProductFullDetailsWithImage prd: data) {
				if(prd.getAgeid().toLowerCase().contains(temp.toLowerCase())) {
					genderdata.add(prd);
				}
			}
		}
		
		if(genderdata.size()==0) {
			genderdata = data;
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getSearchByAge(), Return list Size:"+genderdata.size());
		System.out.println("##########################################################################");
		return genderdata;
	}
	
	public Set<ProductFullDetailsWithImage> getSearchByPrice(double[] price, Set<ProductFullDetailsWithImage> data) {
		
		Set<ProductFullDetailsWithImage> pricedata = new CopyOnWriteArraySet<>();
		if(price.length>0) {
			for(double prices:price) {
				for(ProductFullDetailsWithImage pfd:data) {
					if(Double.parseDouble(pfd.getSellingprice())<=prices) {
						pricedata.add(pfd);
					}
				}
			}
		}
		if(pricedata.size()==0) {
			pricedata=data;
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getSearchByPrice(), Return list Size:"+pricedata.size());
		System.out.println("##########################################################################");
		return pricedata;
	}
	
	
	public Set<ProductFullDetailsWithImage> getSearchBySubTitle(String[] title, Set<ProductFullDetailsWithImage> data) {
		
		Set<ProductFullDetailsWithImage> pricedata = new CopyOnWriteArraySet<>();
		if(title.length>0) {
			for(String subtitle:title) {
				for(ProductFullDetailsWithImage pfd:data) {
					if(pfd.getProductsubtitle().contains(subtitle)) {
						pricedata.add(pfd);
					}
				}
			}
		}
		if(pricedata.size()==0) {
			pricedata=data;
		}
		System.out.println("##########################################################################");
		System.err.println("Class: SearchScan.java, Method: getSearchBySubTitle(), Return list Size:"+pricedata.size());
		System.out.println("##########################################################################");
		return pricedata;
	}

}
