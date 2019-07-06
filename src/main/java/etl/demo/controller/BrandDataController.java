package etl.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import etl.demo.dto.BrandDataDto;
import etl.demo.exception.ResourceNotFoundException;
import etl.demo.model.BrandData;
import etl.demo.repository.BrandDataRepository;
import etl.demo.service.BrandDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Brand detais")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BrandDataController {

	@Autowired
	private BrandDataRepository brandDataRepository;
	
	@Autowired
	private BrandDataService brandDataService;

	@ApiOperation(value = "View a list of available brand data", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@GetMapping(path = "/brandDetails", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<BrandDataDto> getAllBrandDetails() {
		return brandDataService.getInventoryDetailList();
		
	}

	@ApiOperation(value = "Get a inventory detail by Id")
	@GetMapping(path = "/brandsDetails/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BrandData> getEmployeeById(
			@ApiParam(value = "Brand detail id from which Brand object will retrieve", required = true) @PathVariable(value = "id") Long brandDetailId)
			throws ResourceNotFoundException {
		BrandData brandData = brandDataRepository.findById(brandDetailId)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandDetailId));
		return ResponseEntity.ok().body(brandData);
	}

	@ApiOperation(value = "Add an Inventory Detail")
	@PostMapping(path = "/brandDetails", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public BrandData createEmployee(
			@ApiParam(value = "Inventory object store in database table", required = true) @Valid @RequestBody BrandData brandData) {
		return brandDataRepository.save(brandData);
	}

	@ApiOperation(value = "Update a brand detail")
	@PutMapping(path = "/brandDetails/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<BrandData> updateEmployee(
			@ApiParam(value = "Brand Detail Id to update BrandDetail object", required = true) @PathVariable(value = "id") Long brandDetailId,
			@ApiParam(value = "Update Brand detail object", required = true) @Valid @RequestBody BrandData brandDataDetail)
			throws ResourceNotFoundException {
		BrandData brandData = brandDataRepository.findById(brandDetailId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + brandDetailId));

		brandData.setTimeReceived(brandDataDetail.getTimeReceived());
		brandData.setQuantity(brandDataDetail.getQuantity());
		final BrandData updatedDetail = brandDataRepository.save(brandData);
		return ResponseEntity.ok(updatedDetail);
	}

	@ApiOperation(value = "Delete a Brand detail")
	@DeleteMapping("/brandDetails/{id}")
	public Map<String, Boolean> deleteBrandDetail(
			@ApiParam(value = "BrandDetail Id from which BrandData object will delete from database table", required = true) @PathVariable(value = "id") Long brandDetailId)
			throws ResourceNotFoundException {
		BrandData brandData = brandDataRepository.findById(brandDetailId).orElseThrow(
				() -> new ResourceNotFoundException("Brand Data not found for this id :: " + brandDetailId));

		brandDataRepository.delete(brandData);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@ApiOperation(value = "View a list of available brand data", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list")})
	@GetMapping(path = "/sumOfInventory")
	public List<BrandData> getAllBrandGroupBy() {
		return brandDataRepository.findInventoryGroupBy();
	}

}
