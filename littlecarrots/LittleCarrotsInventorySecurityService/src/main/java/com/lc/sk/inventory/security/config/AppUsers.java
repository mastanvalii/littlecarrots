/**
 * 
 */
package com.lc.sk.inventory.security.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lc.sk.auth.security.SecretSecurity;
import com.lc.sk.inventory.security.beans.AppUserBean;
import com.lc.sk.inventory.security.beans.AuthenticationBean;
import com.lc.sk.inventory.security.beans.AuthorizedUsers;
import com.lc.sk.inventory.security.beans.SellerUsers;
import com.lc.sk.inventory.security.exceptions.subexceptions.AuthorizedUserNotFoundException;
import com.lc.sk.inventory.security.rest.AuthorizedUsersRestService;
import com.lc.sk.inventory.security.rest.SalesSellerRestService;
import com.lc.sk.inventory.security.util.ConstantValues;
import com.lc.sk.inventory.security.util.SecurityHttpStatus;

/**
 * @author Mastanvali Shaik LittleCarrotsInventorySecurityService 2020
 */
@Component
public class AppUsers {

	@Autowired
	private AuthorizedUsersRestService authorizedUsersRestService;

	private @Autowired
	SalesSellerRestService salesSellerRestService;

	private List<AppUserBean> users = null;
/*
	public AuthenticationBean checkUser1(String username, String password, String rolename) {
		users = new CopyOnWriteArrayList<>();
		if (users.isEmpty() || users.size() <= 0) {
			List<AuthorizedUsers> user_temp1 = authorizedUsersRestService.getAllAuthorizedUsers();
			List<SellerUsers> user_temp2 = salesSellerRestService.getAllSalesUsers();
			if (user_temp1!=null && user_temp1.size() > 0) {
				for (AuthorizedUsers user1 : user_temp1) {
					users.add(new AppUserBean(user1.getUsername(), user1.getPassword(), user1.getRolename(),
							user1.getEmail(), user1.getAuthuserstatus()));
				}
			}
			if (user_temp2!=null && user_temp2.size() > 0) {
				for (SellerUsers user2 : user_temp2) {
					users.add(new AppUserBean(user2.getUsername(), user2.getPassword(), user2.getRolename(),
							user2.getEmail(), user2.getStatus() + ""));
				}
			}
		}

		boolean flag = false;
		AppUserBean user = null;
		AuthenticationBean authenticationBean = null;
		for (AppUserBean user_dump : users) {
			if (user_dump.getUsername() != null && user_dump.getUsername().equals(username)
					&& password.equals(SecretSecurity.des(user_dump.getPassword()))
					&& rolename.equals(user_dump.getRolename())
					&& new Integer(user_dump.getStatus()).intValue() == ConstantValues.ACTIVE_USER) {
				user = user_dump;
				flag = true;
			}
		}
		if (flag) {
			authenticationBean = new AuthenticationBean(user.getUsername(), 12345, user.getEmail(), user.getRolename(),
					SecurityHttpStatus.OK, System.currentTimeMillis());
		} else {
			throw new AuthorizedUserNotFoundException(ConstantValues.USER_ID_INVALID);
		}
		return authenticationBean;
	}
	*/
	
	public AuthenticationBean checkUser(String username, String password, String rolename) {
			AuthenticationBean authBean = null;
			boolean checkflag = false;
			authBean = authorizedUsersRestService.loginCheck(username, password);
			if(authBean.getResponsecode()== SecurityHttpStatus.ACCEPTED)
			{
				checkflag = true;
	//			return authBean;
			}
			if(!checkflag)
			{
				authBean = salesSellerRestService.selesUserLogin(username, password);
				if(authBean.getResponsecode()==SecurityHttpStatus.ACCEPTED)
				{
					checkflag = true;
//					return authBean;
				}
			}
			if(!checkflag)
			{
				throw new AuthorizedUserNotFoundException(ConstantValues.USER_ID_INVALID);
			}		
			return authBean;
	}
	
}
