package com.lc.sk.inventory.entities;

import java.io.Serializable;

public class ProductDetailsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3433475529394468603L;
	private Products products;   //-done
	private Batch batch;	//-done
	private ProductDescriptions productdescription; //x
	private Genders genders;	 //x
	private Categories categories;	//x
	private SubCategories subcategories; //x
	private SeasonWear seasonwear; //x
	private OccasionalWear Occasionalwear; //x
	private ProductAge productage;  //x
	private Materialtypes materialtypes;	//x
	private Color color;	//x
	private ProductQuantities productquantities; //x
	
	private Sizes sizes; //X
//	private Materialcomposition materialcomposition; 	
	private NoOfPieces noofpieces;  	//x
	private Patterns patterns;  //x
	private PricesTable pricestable;	 //x
//	private ProductType producttype; 	
	public ProductDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDetailsBean(Products products, Batch batch, ProductDescriptions productdescription, Genders genders,
			Categories categories, SubCategories subcategories, SeasonWear seasonwear, OccasionalWear occasionalwear,
			ProductAge productage, Materialtypes materialtypes, Color color, ProductQuantities productquantities,
			Sizes sizes, NoOfPieces noofpieces, Patterns patterns, PricesTable pricestable) {
		super();
		this.products = products;
		this.batch = batch;
		this.productdescription = productdescription;
		this.genders = genders;
		this.categories = categories;
		this.subcategories = subcategories;
		this.seasonwear = seasonwear;
		Occasionalwear = occasionalwear;
		this.productage = productage;
		this.materialtypes = materialtypes;
		this.color = color;
		this.productquantities = productquantities;
		this.sizes = sizes;
		this.noofpieces = noofpieces;
		this.patterns = patterns;
		this.pricestable = pricestable;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public ProductDescriptions getProductdescription() {
		return productdescription;
	}
	public void setProductdescription(ProductDescriptions productdescription) {
		this.productdescription = productdescription;
	}
	public Genders getGenders() {
		return genders;
	}
	public void setGenders(Genders genders) {
		this.genders = genders;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public SubCategories getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(SubCategories subcategories) {
		this.subcategories = subcategories;
	}
	public SeasonWear getSeasonwear() {
		return seasonwear;
	}
	public void setSeasonwear(SeasonWear seasonwear) {
		this.seasonwear = seasonwear;
	}
	public OccasionalWear getOccasionalwear() {
		return Occasionalwear;
	}
	public void setOccasionalwear(OccasionalWear occasionalwear) {
		Occasionalwear = occasionalwear;
	}
	public ProductAge getProductage() {
		return productage;
	}
	public void setProductage(ProductAge productage) {
		this.productage = productage;
	}
	public Materialtypes getMaterialtypes() {
		return materialtypes;
	}
	public void setMaterialtypes(Materialtypes materialtypes) {
		this.materialtypes = materialtypes;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public ProductQuantities getProductquantities() {
		return productquantities;
	}
	public void setProductquantities(ProductQuantities productquantities) {
		this.productquantities = productquantities;
	}
	public Sizes getSizes() {
		return sizes;
	}
	public void setSizes(Sizes sizes) {
		this.sizes = sizes;
	}
	public NoOfPieces getNoofpieces() {
		return noofpieces;
	}
	public void setNoofpieces(NoOfPieces noofpieces) {
		this.noofpieces = noofpieces;
	}
	public Patterns getPatterns() {
		return patterns;
	}
	public void setPatterns(Patterns patterns) {
		this.patterns = patterns;
	}
	public PricesTable getPricestable() {
		return pricestable;
	}
	public void setPricestable(PricesTable pricestable) {
		this.pricestable = pricestable;
	}
	@Override
	public String toString() {
		return "ProductDetailsBean [products=" + products + ", batch=" + batch + ", productdescription="
				+ productdescription + ", genders=" + genders + ", categories=" + categories + ", subcategories="
				+ subcategories + ", seasonwear=" + seasonwear + ", Occasionalwear=" + Occasionalwear + ", productage="
				+ productage + ", materialtypes=" + materialtypes + ", color=" + color + ", productquantities="
				+ productquantities + ", sizes=" + sizes + ", noofpieces=" + noofpieces + ", patterns=" + patterns
				+ ", pricestable=" + pricestable + "]";
	}
	

	
	
}
