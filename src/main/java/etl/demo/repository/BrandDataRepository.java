package etl.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import etl.demo.model.BrandData;

public interface BrandDataRepository extends JpaRepository<BrandData, Long>{

	@Query("select b from BrandData b group by b.brand.id")
	List<BrandData> findInventoryGroupBy();

}
