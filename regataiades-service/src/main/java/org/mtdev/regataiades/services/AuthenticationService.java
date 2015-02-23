package org.mtdev.regataiades.services;

import java.util.HashMap;
import java.util.Map;

import org.mtdev.regataiades.security.AccessTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/auth")
public class AuthenticationService {


	@Autowired
	protected AccessTool  mAccessTool;

	@RequestMapping(value = "/check")
	@PostAuthorize("@AccessTool.isAuthenticated()")
	public @ResponseBody Object isAuthenticated() {
		Map<Object, Object> lResponse = new HashMap<Object, Object>();
		lResponse.put("auth", "true");
		lResponse.put("user", mAccessTool.getUserDetails());

		if (mAccessTool.getUserDetails() != null) {
			try {
				lResponse.put("username", ((User) mAccessTool
						.getUserDetails()).getUsername());
			} catch (Exception lE) {

			}

		}

		return lResponse;
	}
	
	@RequestMapping(value = "/logout")
	public @ResponseBody Object logout() {
		Map<Object, Object> lResponse = new HashMap<Object, Object>();
		lResponse.put("status", "success");
		lResponse.put("details", "logout successful");

		return lResponse;
	}
	
}
