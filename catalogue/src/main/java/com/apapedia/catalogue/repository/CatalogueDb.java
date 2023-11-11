package com.apapedia.catalogue.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apapedia.catalogue.model.Catalogue;

@Repository
public interface CatalogueDb extends JpaRepository<Catalogue, UUID>{
    List<Catalogue> findAllOrderProductNameByAsc();
    List<Catalogue> findByIdSellerOrderByProductNameAsc(UUID idSeller);


    @Query("SELECT c FROM Catalogue c ORDER BY LOWER (c.productName)")
    List<Catalogue> findAllByOrderByProductNameByAsc();
}
