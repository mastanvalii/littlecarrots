/**
 * 
 */
package com.lc.sk.inventory.exception;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.exception.subexception.ApprovalException;
import com.lc.sk.inventory.exception.subexception.BadgesException;
import com.lc.sk.inventory.exception.subexception.BatchException;
import com.lc.sk.inventory.exception.subexception.BlogreportException;
import com.lc.sk.inventory.exception.subexception.CategoriesException;
import com.lc.sk.inventory.exception.subexception.CollectionSaleException;
import com.lc.sk.inventory.exception.subexception.CollectionSaleImageException;
import com.lc.sk.inventory.exception.subexception.CollectionSaleProductidsException;
import com.lc.sk.inventory.exception.subexception.ColorException;
import com.lc.sk.inventory.exception.subexception.ComboProductDetailsNotFoundException;
import com.lc.sk.inventory.exception.subexception.CombosNotFoundException;
import com.lc.sk.inventory.exception.subexception.CountriesNotFoundException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.DisplayTypeException;
import com.lc.sk.inventory.exception.subexception.GBlogException;
import com.lc.sk.inventory.exception.subexception.GendersException;
import com.lc.sk.inventory.exception.subexception.MaterialcompositionException;
import com.lc.sk.inventory.exception.subexception.MaterialtypesException;
import com.lc.sk.inventory.exception.subexception.NoOfPiecesNotFoundException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.exception.subexception.OccasionWearNotFoundException;
import com.lc.sk.inventory.exception.subexception.OccasionidNotFoundException;
import com.lc.sk.inventory.exception.subexception.PatternsNotFoundException;
import com.lc.sk.inventory.exception.subexception.PricesTableException;
import com.lc.sk.inventory.exception.subexception.ProductAgeNotFoundException;
import com.lc.sk.inventory.exception.subexception.ProductDescriptionsNotFoundException;
import com.lc.sk.inventory.exception.subexception.ProductException;
import com.lc.sk.inventory.exception.subexception.ProductKeywordsException;
import com.lc.sk.inventory.exception.subexception.ProductQuantityException;
import com.lc.sk.inventory.exception.subexception.ProductTypeNotFoundException;
import com.lc.sk.inventory.exception.subexception.SeasonWearNotFoundException;
import com.lc.sk.inventory.exception.subexception.SelectedproductsException;
import com.lc.sk.inventory.exception.subexception.SellerToWarehouseException;
import com.lc.sk.inventory.exception.subexception.SizeNotFoundException;
import com.lc.sk.inventory.exception.subexception.SubCategoriesException;
import com.lc.sk.inventory.exception.subexception.WarehouseNotFoundExcpetion;
import com.lc.sk.inventory.exception.subexception.WishlistNotFoundException;
import com.lc.sk.inventory.util.HeaderComponent;
import com.lc.sk.inventory.util.SecurityHttpStatus;

/**
 * @author Mastanvali Shaik LittleCarrotsInventoryService 2020
 */

@ControllerAdvice
public class GlobalException {

	static Logger log = Logger.getLogger(GlobalException.class);

	@Autowired
	HeaderComponent headers;

	public GlobalException() {
		BasicConfigurator.configure();
	}

	/* CountriesNotFoundException */
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(CountriesNotFoundException e) {
		return loggerMessage("URL:/countries---- handleException(CountriesNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.NOT_FOUND);
	}

	/* DBInsertException */
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(DBInsertException e) {
		return loggerMessage("URL:/countries---- handleException(DBInsertException)-", e.getMessage(),
				SecurityHttpStatus.INTERNAL_SERVER_ERROR);

	}

	/* CountriesNotFoundException */
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(WarehouseNotFoundExcpetion e) {
		return loggerMessage("URL:/warehouses---- handleException(WarehouseNotFoundExcpetion)-", e.getMessage(),
				SecurityHttpStatus.NOT_FOUND);

	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(NullRequestReceivedException e) {
		return loggerMessage("URL:/warehouses---- handleException(NullRequestReceivedException))-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(SellerToWarehouseException e) {
		return loggerMessage("URL:/SellerToWarehouseException---- handleException(SellerToWarehouseException)-",
				e.getMessage(), SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(BatchException e) {
		return loggerMessage("URL:/Batches---- handleException(BatchException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(GendersException e) {
		return loggerMessage("URL:/genders---- handleException(GendersException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ProductAgeNotFoundException e) {
		return loggerMessage("URL:/ProductAge---- handleException(ProductAgeNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(SizeNotFoundException e) {
		return loggerMessage("URL:/sizes---- handleException(SizeNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ColorException e) {
		return loggerMessage("URL:/colors---- handleException(ColorException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(CategoriesException e) {
		return loggerMessage("URL:/Categories---- handleException(CategoriesException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(SeasonWearNotFoundException e) {
		return loggerMessage("URL:/seasonwear---- handleException(SeasonWearNotFoundException.java) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ProductTypeNotFoundException e) {
		return loggerMessage("URL:/producttype---- handleException(ProductTypeNotFoundException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(MaterialtypesException e) {
		return loggerMessage("URL:/material---- handleException(MaterialtypesException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(SubCategoriesException e) {
		return loggerMessage("URL:/subcategories---- handleException(SubCategoriesException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);

	}

	// OccasionWearNotFoundException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(OccasionWearNotFoundException e) {
		return loggerMessage("URL:/OccasionalWear---- handleException(OccasionWearNotFoundException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	// OccasionidNotFoundException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(OccasionidNotFoundException e) {
		return loggerMessage("URL:/OccasionalWear---- handleException(OccasionWearNotFoundException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	// NoOfPieces
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(NoOfPiecesNotFoundException e) {
		return loggerMessage("URL:/NoOfPieces---- handleException(NoOfPiecesNotFoundException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	// PricesTableException
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(PricesTableException e) {
		return loggerMessage("URL:/PricesTable---- handleException(OccasionWearNotFoundException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ProductException e) {
		return loggerMessage("URL:/products---- handleException(ProductException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(MaterialcompositionException e) {
		return loggerMessage("URL:/materialcomposition---- handleException(MaterialtypesException) -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ProductQuantityException e) {
		return loggerMessage("URL:/productquantities---- handleException(ProductQuantityException)  -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(PatternsNotFoundException e) {
		return loggerMessage("URL:/patterns---- handleException(PatternsNotFoundException)  -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ProductDescriptionsNotFoundException e) {
		return loggerMessage("URL:/productdescription---- handleException(ProductDescriptionsNotFoundException)  -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}		
		
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(ApprovalException e) {
		return loggerMessage("URL:/ApprovalException---- handleException(ApprovalExceptionNotFoundException)  -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}		
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(DisplayTypeException e) {
		return loggerMessage("URL:/DisplayTypeException---- handleException(ApprovalExceptionNotFoundException)  -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(BadgesException e) {
		return loggerMessage("URL:/BadgesException---- handleException(BadgeNotFoundException)  -", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}	
	public ResponseEntity<ResponseBean> loggerMessage(String url, String message, int status)
	{
		ResponseBean seb = new ResponseBean();
		seb.setMessage(message);
		seb.setResponsecode(status);
		seb.setTiemstamp(System.currentTimeMillis());
		log.error(url+ seb.toString());
		return new ResponseEntity<ResponseBean>(seb, headers.getHeader(), HttpStatus.OK);
	}
	
	//Collection Sale
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(CollectionSaleException e) {
		return loggerMessage("URL:/collection---- handleException(CollectionSaleException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	
	//Selected products
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(SelectedproductsException e) {
		return loggerMessage("URL:/selprod---- handleException(SelectedproductsException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	
	//Wishlist
	@ExceptionHandler
	public ResponseEntity<ResponseBean> handleException(WishlistNotFoundException e) {
		return loggerMessage("URL:/Wishlist---- handleException(WishlistNotFoundException)-", e.getMessage(),
				SecurityHttpStatus.BAD_REQUEST);
	}
	
	
	// BlogReport
		@ExceptionHandler
		public ResponseEntity<ResponseBean> handleException(BlogreportException e) {
			return loggerMessage("URL:/Blogreport---- handleException(BlogreportException)-", e.getMessage(),
					SecurityHttpStatus.BAD_REQUEST);
		}

		// GrannyBlog
		@ExceptionHandler
		public ResponseEntity<ResponseBean> handleException(GBlogException e) {
			return loggerMessage("URL:/GrannyBlog---- handleException(GBlogException)-", e.getMessage(),
					SecurityHttpStatus.BAD_REQUEST);
		}
		
		//CollectionSaleProductidsException
		@ExceptionHandler
		public ResponseEntity<ResponseBean> handleException(CollectionSaleProductidsException e) {
			return loggerMessage("URL:/collectionprod---- handleException(CollectionSaleProductidsException)-", e.getMessage(),
					SecurityHttpStatus.BAD_REQUEST);
		}
		
		//CollectionSaleImageException
				@ExceptionHandler
				public ResponseEntity<ResponseBean> handleException(CollectionSaleImageException e) {
					return loggerMessage("URL:/collectionimage---- handleException(CollectionSaleImageException)-", e.getMessage(),
							SecurityHttpStatus.BAD_REQUEST);
				}
				
		//ProductKeywordsException
		@ExceptionHandler
		public ResponseEntity<ResponseBean> handleException(ProductKeywordsException e) {
			return loggerMessage("URL:/ProductKeywords---- handleException(ProductKeywordsException)-", e.getMessage(),
			SecurityHttpStatus.BAD_REQUEST);
				}	
				
		//CombosNotFoundException
				@ExceptionHandler
				public ResponseEntity<ResponseBean> handleException(CombosNotFoundException e) {
					return loggerMessage("URL:/combos---- handleException(CombosNotFoundException)-", e.getMessage(),
					SecurityHttpStatus.BAD_REQUEST);
						}	
	
		//ComboProductDetailsNotFoundException
				@ExceptionHandler
				public ResponseEntity<ResponseBean> handleException(ComboProductDetailsNotFoundException e) {
					return loggerMessage("URL:/comboprod---- handleException(ComboProductDetailsNotFoundException)-", e.getMessage(),
					SecurityHttpStatus.BAD_REQUEST);
						}				
}	
		
	

