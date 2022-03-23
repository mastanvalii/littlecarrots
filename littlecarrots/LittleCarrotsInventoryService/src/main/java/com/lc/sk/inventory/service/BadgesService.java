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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.sk.inventory.bean.ResponseBean;
import com.lc.sk.inventory.entities.Badges;
import com.lc.sk.inventory.exception.subexception.BadgesException;
import com.lc.sk.inventory.exception.subexception.DBInsertException;
import com.lc.sk.inventory.exception.subexception.NullRequestReceivedException;
import com.lc.sk.inventory.repositories.BadgesRepository;
import com.lc.sk.inventory.util.ConstantValues;
import com.lc.sk.inventory.util.SecurityHttpStatus;
import com.lc.sk.inventory.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.INVENTORY_ROOT_PATH)
public class BadgesService {
	@Autowired
	BadgesRepository badgesrepo;


	@GetMapping(path = URLMapping.BADGE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Badges>> getAllBadges() {
		List<Badges> badges = (List<Badges>) this.badgesrepo.getAllBadges();
		if (badges.isEmpty()) {
			throw new BadgesException(ConstantValues.EMPTY_BADGE_LIST);
		} else {

			return new ResponseEntity<List<Badges>>(badges,   HttpStatus.ACCEPTED);
		}

	}

	@GetMapping(path = URLMapping.GET_BY_BADGE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Optional<Badges>> getByBadges(@PathVariable String badge) {
		Optional<Badges> badges = this.badgesrepo.getByBadge(badge);
		if (badges.isPresent() && badges.get() != null) {
			return new ResponseEntity<Optional<Badges>>(badges,   HttpStatus.ACCEPTED);

		} else {

			throw new BadgesException(ConstantValues.BADGE_NOT_FOUND);
		}
	}

	@PostMapping(path = URLMapping.BADGE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseBean> addList(
			@RequestParam(name = ConstantValues.BADGE, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String badge,
			@RequestParam(name = ConstantValues.DESCRIPTION, required = true, defaultValue = ConstantValues.DEFAULT_STRING) String description) {
		ResponseBean responseBean = new ResponseBean();
		if (badge == new String(ConstantValues.DEFAULT_STRING) || description.equals(ConstantValues.DEFAULT_STRING)) {
			throw new NullRequestReceivedException(ConstantValues.RECEIVED_NULL_VALUES);
		} else {
			Badges badges = new Badges(badge, description);
			badges = this.badgesrepo.save(badges);
			if (badges.getBadge() == (badge)) {
				responseBean.setMessage(ConstantValues.BADGE_INSERTION_SUCCESS);
				responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
				responseBean.setTiemstamp(System.currentTimeMillis());

				return new ResponseEntity<ResponseBean>(responseBean,   HttpStatus.ACCEPTED);
			}

			else {
				throw new DBInsertException(ConstantValues.BADGE_INSERTION_HAS_ISSUE);
			}
		}
	}

}
