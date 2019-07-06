package etl.demo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import etl.demo.constants.Constants;
import etl.demo.model.BrandData;
import etl.demo.model.Brands;
import etl.demo.repository.BrandDataRepository;
import etl.demo.repository.BrandRepository;

@Service("fileDataService")
@PropertySource("classpath:config.properties")
@Transactional
public class FileDataServiceImpl implements FileDataService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private BrandDataRepository brandDataRepository;
	
	@Autowired
    private Environment env;

	@Override
	public void saveMasterData() {

		String tableName = "Brands";
		if (tableName.equalsIgnoreCase("Brands")) {
			String csvFile = env.getProperty("masterDataFilePath");
			String line = "";
			String cvsSplitBy = "\t";

			try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
				String column1 = "";
				int i = 0;
				while ((line = br.readLine()) != null) {
					String[] brands = line.split(cvsSplitBy);
					if (i == 0) {
						column1 = brands[0];
						i = i + 1;
					} else {
						Brands brand = new Brands();
						column1 = column1.replaceAll("\"", "");
						if (column1.equalsIgnoreCase(Constants.BRAND_ID)) {

							brand.setBrandId(Long.parseLong(brands[0]));
							brand.setName(brands[1].replaceAll("\"", ""));
						} else {
							brand.setBrandId(Long.parseLong(brands[1]));
							brand.setName(brands[0].replaceAll("\"", ""));
						}
						brand = brandRepository.save(brand);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void saveDetailData() {

		String tableName = "BrandData";
		if (tableName.equalsIgnoreCase("BrandData")) {
			String csvFile = env.getProperty("dataDetailFilePath");
			String line = "";
			String cvsSplitBy = "\t";

			try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
				String column1 = "";
				String column2 = "";
				DateTimeFormatter inputFormatter = DateTimeFormatter
						.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.ENGLISH);
				int i = 0;
				while ((line = br.readLine()) != null) {
					String[] brandDetails = line.split(cvsSplitBy);
					if (i == 0) {
						column1 = brandDetails[0];
						column2 = brandDetails[1];
						i = i + 1;
					} else {
						BrandData brandData = new BrandData();
						column1 = column1.replaceAll("\"", "");

						if (column1.equalsIgnoreCase(Constants.BRAND_ID)) {
							brandData.setBrandId(Long.parseLong(brandDetails[0]));
							if (column2.equalsIgnoreCase(Constants.QUANTITY)) {
								brandData.setQuantity(Double.parseDouble(brandDetails[1]));
								LocalDateTime ldt = LocalDateTime.parse(brandDetails[2], inputFormatter);
								Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
								brandData.setTimeReceived(date);
							} else {
								brandData.setQuantity(Double.parseDouble(brandDetails[2]));
								LocalDateTime ldt = LocalDateTime.parse(brandDetails[1], inputFormatter);
								Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
								brandData.setTimeReceived(date);
							}

						} else if (column1.equalsIgnoreCase(Constants.QUANTITY)) {
							brandData.setQuantity(Double.parseDouble(brandDetails[0]));
							if (column2.equalsIgnoreCase(Constants.BRAND_ID)) {
								
								brandData.setBrandId(Long.parseLong(brandDetails[1]));
								LocalDateTime ldt = LocalDateTime.parse(brandDetails[2], inputFormatter);
								Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
								brandData.setTimeReceived(date);
							} else {
								Brands bd = new Brands();
								bd.setBrandId(Long.parseLong(brandDetails[2]));
								brandData.setBrand(bd);
								LocalDateTime ldt = LocalDateTime.parse(brandDetails[1], inputFormatter);
								Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
								brandData.setTimeReceived(date);
							}
						} else {	
							
							LocalDateTime ldt = LocalDateTime.parse(brandDetails[0], inputFormatter);
							Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
							brandData.setTimeReceived(date);
							if (column2.equalsIgnoreCase(Constants.BRAND_ID)) {
								brandData.setBrandId(Long.parseLong(brandDetails[1]));
								brandData.setQuantity(Double.parseDouble(brandDetails[2]));
							} else {
								brandData.setBrandId(Long.parseLong(brandDetails[2]));
								brandData.setQuantity(Double.parseDouble(brandDetails[1]));
							}
						}
						brandDataRepository.save(brandData);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
