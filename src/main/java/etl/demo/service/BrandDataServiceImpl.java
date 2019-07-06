package etl.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import etl.demo.dto.BrandDataDto;
import etl.demo.model.BrandData;
import etl.demo.repository.BrandDataRepository;

@Service("brandDataService")
@Transactional
public class BrandDataServiceImpl implements BrandDataService{

	
	@Autowired
	private BrandDataRepository brandDataRepository;
	
	@Override
	public List<BrandDataDto> getInventoryDetailList() {
		List<BrandData> list= brandDataRepository.findAll();
		List<BrandDataDto> brandDataList=new ArrayList<>();
		for(BrandData data:list) {
			BrandDataDto brandDataDto=new BrandDataDto();
			brandDataDto.setBrandName(data.getBrand().getName());
			brandDataDto.setBrandId(data.getBrandId());
			brandDataDto.setQuantity(data.getQuantity());
			brandDataDto.setTimeReceived(data.getTimeReceived());
			brandDataDto.setId(data.getId());
			brandDataList.add(brandDataDto);
		}
		return brandDataList;
	}

}
