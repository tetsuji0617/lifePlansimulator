package com.pedalclecle.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
	@RequestMapping(value="/api/getSample", method = {RequestMethod.GET})
	@ResponseBody
	public String receiveJsonSample() {
		StringBuilder returnString = new StringBuilder("[");
		for(int i = 0; i < 100; i++) {
			returnString.append("{\"userId\": 1000, \"id\": 1000, \"title\": \"test" + i + "\",\"body\": \"react request sample\"}");
			if(i < 99 ) {
				returnString.append(",\r\n");
			}
		}

				returnString.append("]");;
		return returnString.toString();
	}
}
