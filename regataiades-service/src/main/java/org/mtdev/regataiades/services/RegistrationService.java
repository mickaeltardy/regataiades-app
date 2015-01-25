package org.mtdev.regataiades.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/registration")
public class RegistrationService {

	@RequestMapping("/registrate")
	public @ResponseBody Object registrate(@RequestBody Object pRequest) {
		return null;
	}

	@RequestMapping("/verify")
	public @ResponseBody Object verify(@RequestBody Object pRequest) {
		return null;
	}

	@RequestMapping("/validate")
	public @ResponseBody Object validate(@RequestBody Object pRequest) {
		return null;
	}

}
