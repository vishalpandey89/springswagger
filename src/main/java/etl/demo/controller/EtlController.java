package etl.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import etl.demo.service.FileDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value="Job for uploading data into database")
public class EtlController {
	@Autowired
	private FileDataService fileDataService;

	
	@ApiOperation(value = "Load Brand Master Data from TSV Files")
	@PostMapping("/loadMasterTsv")
	public Map<String, Boolean> loadMasterData() {
		
		fileDataService.saveMasterData();
		Map<String, Boolean> response = new HashMap<>();
		response.put("INSERTED", Boolean.TRUE);
		return response;
		
	}
	
	@ApiOperation(value = "Load Brand Detail Data from TSV Files")
	@PostMapping("/loadDetailTsv")
	public Map<String, Boolean> loadDetailData() {
		
		fileDataService.saveDetailData();
		Map<String, Boolean> response = new HashMap<>();
		response.put("INSERTED", Boolean.TRUE);
		return response;
		
	}
}
