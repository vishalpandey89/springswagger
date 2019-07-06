package etl.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etl.demo.model.Brands;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long> {

}
