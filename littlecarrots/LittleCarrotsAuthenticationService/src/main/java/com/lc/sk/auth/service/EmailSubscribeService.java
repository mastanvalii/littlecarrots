package com.lc.sk.auth.service;

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

import com.lc.sk.auth.entities.EmailSubscribe;
import com.lc.sk.auth.exceptions.subexceptions.DBValueInsertException;
import com.lc.sk.auth.exceptions.subexceptions.NullRequestReceivedException;
import com.lc.sk.auth.exceptions.subexceptions.SubscribeEMailNotFoundException;
import com.lc.sk.auth.rbeans.ResponseBean;
import com.lc.sk.auth.repositories.EmailSubscribeRepository;
import com.lc.sk.auth.repositories.UserEmailsRespository;
import com.lc.sk.auth.util.ConstantVariables;
import com.lc.sk.auth.util.HeaderComponent;
import com.lc.sk.auth.util.SecurityHttpStatus;
import com.lc.sk.auth.util.URLMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = URLMapping.AUTHORIZATION_ROOT_PATH)
public class EmailSubscribeService { // NO_UCD (unused code)
	
	
	
	@Autowired
	UserEmailsRespository useremails;
	
	@Autowired 
	EmailSubscribeRepository emailsubscribeRepository;

	
	// INSERTING NEW AUTHORIZATION ROLE
		@PostMapping(path=URLMapping.EMAIL_SUBSCRIBE,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<ResponseBean> insertEmail(
				@RequestParam(name = ConstantVariables.EMAIL_ID, required = true, defaultValue = ConstantVariables.DEFAULT_STRING_VALUE) String emailid
				) {
			ResponseBean responseBean = new ResponseBean();
			if (emailid.equals(ConstantVariables.DEFAULT_STRING_VALUE)) {
				throw new NullRequestReceivedException(ConstantVariables.NULL_VALUES_RECEIVED_FROM_CLIENT);
			} else {
				Optional<EmailSubscribe> mail = emailsubscribeRepository.getAllEmailsBYID(emailid);
				if(!mail.isPresent()) {
					EmailSubscribe subscribe = emailsubscribeRepository.save(new EmailSubscribe(emailid));
					if (subscribe.getEmailid().equals(emailid)) {
						responseBean.setMessage(ConstantVariables.SUBSCRIBED_SUCCESFULLY);
						responseBean.setTiemstamp(System.currentTimeMillis());
						responseBean.setResponsecode(SecurityHttpStatus.ACCEPTED);
						return new ResponseEntity<ResponseBean>(responseBean,  HttpStatus.ACCEPTED);
					} else {
						throw new DBValueInsertException(ConstantVariables.DB_INSERTION_FAILED);
					}
				}
				else {
					throw new DBValueInsertException(ConstantVariables.YOU_HAVE_ALREADY_SUBSCRIBED);
				}
				}
				

		}
		
		@GetMapping(path=URLMapping.EMAIL_SUBSCRIBE,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<EmailSubscribe>> getEmailIDs()
		{
			List<EmailSubscribe> subscribe = emailsubscribeRepository.getAllEmails();
			if(subscribe.isEmpty())
			{
				throw new SubscribeEMailNotFoundException(ConstantVariables.SUBSCRIBER_NOT_FOUND);
			}
			else {
				return new ResponseEntity<List<EmailSubscribe>>(subscribe,  HttpStatus.ACCEPTED);
			}

		}
		
		@GetMapping(path = URLMapping.EMAIL_SUBSCRIBE_BY_EMAIL_ID,produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<Optional<EmailSubscribe>> getAuthUserByUsername(@PathVariable String emailid) {
			Optional<EmailSubscribe> subscribe = emailsubscribeRepository.getAllEmailsBYID(emailid);
			if (subscribe.isPresent()) {
				return new ResponseEntity<Optional<EmailSubscribe>>(subscribe,  HttpStatus.ACCEPTED);
			} else {
				throw new SubscribeEMailNotFoundException(ConstantVariables.SUBSCRIBER_NOT_FOUND);
			}

		}
}
