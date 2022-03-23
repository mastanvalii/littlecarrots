package com.lc.sk.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.NoOfPieces;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NoOfPiecesNotFoundException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.NoOfPiecesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class NoOfPiecesService {

	@Autowired
	NoOfPiecesRepository noOfPiecesRepository;



	@GetMapping(path = URLMapping.NO_OF_PIECES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoOfPieces>> getAllNoOfPieces() {
		List<NoOfPieces> noofpieces = (List<NoOfPieces>) this.noOfPiecesRepository.getallpieces();
		if (noofpieces.isEmpty()) {
			throw new NoOfPiecesNotFoundException(ConstantValues.NO_OF_SET_NOT_FOUND);
		} else {
			return new ResponseEntity<List<NoOfPieces>>(noofpieces,  HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = URLMapping.NO_OF_PIECES_PATH_WITH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<NoOfPieces>> getNoOfPiecesId(@PathVariable long pieceid) {
		Optional<NoOfPieces> noofpieces1 = this.noOfPiecesRepository.findid(pieceid);
		if (noofpieces1.isPresent()) {
			return new ResponseEntity<Optional<NoOfPieces>>(noofpieces1,  HttpStatus.ACCEPTED);
		} else {
			throw new NoOfPiecesNotFoundException(ConstantValues.NO_OF_PIECES_NOT_FOUND);
		}

	}

	@PostMapping(path = URLMapping.NO_OF_PIECES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> insertNoOfPiecesList(
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.NOOFSET, required = true, defaultValue = ConstantValues.DEFAULT_INT) long noofset) {
		if (productid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| noofset == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {

			NoOfPieces noofpieces = this.noOfPiecesRepository.save(new NoOfPieces(productid, noofset));

			if (noofpieces.getProductid() == productid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_INSERTED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_INSERTED_IN_DB);
			}
		}
	}

	@PutMapping(path = URLMapping.NO_OF_PIECES_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> enableNoOfPiecesStatus(
			@RequestParam(name = ConstantValues.PIECE_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long pieceid,
			@RequestParam(name = ConstantValues.PRODUCT_ID, required = true, defaultValue = ConstantValues.DEFAULT_INT) long productid,
			@RequestParam(name = ConstantValues.NOOFSET, required = true, defaultValue = ConstantValues.DEFAULT_INT) long noofset) {
		if (pieceid == Long.parseLong(ConstantValues.DEFAULT_INT) || productid == Long.parseLong(ConstantValues.DEFAULT_INT)
				|| noofset == Long.parseLong(ConstantValues.DEFAULT_INT)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {

		Optional<NoOfPieces> pieces = this.noOfPiecesRepository.findById(pieceid);
		if (pieces.isPresent()) {
			pieces.get().setNoofset(noofset);
			NoOfPieces pieces1 = this.noOfPiecesRepository.save(pieces.get());

			if (pieces1.getProductid() == productid) {
				ResponseBean responseBean = new ResponseBean(ConstantValues.DATA_UPDATED_IN_DB,
						SecurityHttpStatus.ACCEPTED, System.currentTimeMillis());
				return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
			} else {
				throw new DBInsertException(ConstantValues.DATA_NOT_UPDATED_IN_DB);
			}
		} else {
			throw new NoOfPiecesNotFoundException(ConstantValues.NO_OF_SET_NOT_FOUND);

		}

	}}
}
