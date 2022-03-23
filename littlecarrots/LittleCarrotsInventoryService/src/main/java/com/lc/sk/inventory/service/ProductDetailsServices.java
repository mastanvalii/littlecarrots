package com.lc.sk.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ProductFullDetails;
import com.lc.sk.inventory.cache.ProductFullCache;
import com.lc.sk.inventory.exception.subexception.ProductException;
import com.lc.sk.inventory.repositories.ProductsRepository;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class ProductDetailsServices {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	ProductFullCache cache;


	@GetMapping(path = URLMapping.PROUDCT_DETAILS_GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductFullDetails>> getAllProductDetails() {
		List<ProductFullDetails> pfd = this.cache.getAllDetails();
		if (!pfd.isEmpty()) {
			return new ResponseEntity<List<ProductFullDetails>>(pfd,  HttpStatus.ACCEPTED);
		} else {
			throw new ProductException("No Products Found..."); //$NON-NLS-1$
		}
	}

	@GetMapping(path = URLMapping.PRODUCT_DETAILS_GET_WITH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductFullDetails> getProductById(@PathVariable long productid) {
		ProductFullDetails pfd = this.cache.getProductDetails(productid); //$NON-NLS-1$
		if (pfd != null) {
			return new ResponseEntity<ProductFullDetails>(pfd,  HttpStatus.ACCEPTED);
		} else {
			throw new ProductException("No Products Found..."); //$NON-NLS-1$
		}

	}

	@GetMapping(path = URLMapping.PRODUCT_DETAILS_GET_WITH_PATH1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductFullDetails> getProductById(@PathVariable long productid,
			@PathVariable boolean status) {
		ProductFullDetails product = this.productsRepository.getProductDetails(productid + "", status); //$NON-NLS-1$
		if (product != null) {
			return new ResponseEntity<ProductFullDetails>(product,  HttpStatus.ACCEPTED);
		} else {
			throw new ProductException("No Products Found..."); //$NON-NLS-1$
		}
	}

}
