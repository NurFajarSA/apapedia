package com.apapedia.webapp.model;

import com.fasterxml.jackson.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue {
    private UUID id;
    private UUID idSeller;
    private long price;
    private String productName;
    private String productDescription;
    private long stock;
    private String image;
    private Category category;
    private boolean deleted;

    // @JsonProperty("id")
    // public UUID getID() { return id; }
    // @JsonProperty("id")
    // public void setID(UUID value) { this.id = value; }

    // @JsonProperty("idSeller")
    // public UUID getIDSeller() { return idSeller; }
    // @JsonProperty("idSeller")
    // public void setIDSeller(UUID value) { this.idSeller = value; }

    // @JsonProperty("price")
    // public long getPrice() { return price; }
    // @JsonProperty("price")
    // public void setPrice(long value) { this.price = value; }

    // @JsonProperty("productName")
    // public String getProductName() { return productName; }
    // @JsonProperty("productName")
    // public void setProductName(String value) { this.productName = value; }

    // @JsonProperty("productDescription")
    // public String getProductDescription() { return productDescription; }
    // @JsonProperty("productDescription")
    // public void setProductDescription(String value) { this.productDescription = value; }

    // @JsonProperty("stock")
    // public long getStock() { return stock; }
    // @JsonProperty("stock")
    // public void setStock(long value) { this.stock = value; }

    // @JsonProperty("image")
    // public String getImage() { return image; }
    // @JsonProperty("image")
    // public void setImage(String value) { this.image = value; }

    // @JsonProperty("category")
    // public Object getCategory() { return category; }
    // @JsonProperty("category")
    // public void setCategory(Object value) { this.category = value; }

    // @JsonProperty("deleted")
    // public boolean getDeleted() { return deleted; }
    // @JsonProperty("deleted")
    // public void setDeleted(boolean value) { this.deleted = value; }
}
