package uni.tartu.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * author: lkokhreidze
 * date: 3/17/16
 * time: 11:04 PM
 **/

@Controller
class IndexController {
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
}
