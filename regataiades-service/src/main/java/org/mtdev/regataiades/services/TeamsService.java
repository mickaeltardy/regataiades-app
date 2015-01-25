package org.mtdev.regataiades.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teams")
public class TeamsService {

	@RequestMapping("/list")
	public @ResponseBody Object getTeamsList() {
		return null;
	}

	@RequestMapping("/{id}")
	public @ResponseBody Object getTeamDescription(@PathVariable("id") int pId) {
		return null;
	}
	
	
}
