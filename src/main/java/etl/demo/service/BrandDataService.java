package etl.demo.service;

import java.util.List;

import etl.demo.dto.BrandDataDto;

public interface BrandDataService {

	List<BrandDataDto> getInventoryDetailList();

}
