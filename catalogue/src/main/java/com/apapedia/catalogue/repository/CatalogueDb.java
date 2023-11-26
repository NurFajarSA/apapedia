package com.apapedia.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apapedia.catalogue.model.Catalogue;
import org.springframework.data.jpa.repository.Query;

import com.apapedia.catalogue.model.Catalogue;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogueDb extends JpaRepository<Catalogue, UUID> {

    //List<Catalogue> findByCategoryId(UUID categoryId);
    List<Catalogue> findByProductName(String productName);
    List<Catalogue> findByPrice(int price);
    List<Catalogue> findAllByOrderByPriceAsc();
    List<Catalogue> findAllByOrderByPriceDesc();
    List<Catalogue> findAllByOrderByProductNameAsc();
    List<Catalogue> findAllByOrderByProductNameDesc();

    List<Catalogue> findByIdSellerOrderByProductNameAsc(UUID idSeller);

    @Query("SELECT c FROM Catalogue c ORDER BY LOWER (c.productName)")
    List<Catalogue> findAllByOrderByProductNameByAsc();
}