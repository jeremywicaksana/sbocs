package com.example.springbocs.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.UUID;

// Getters and Setters explicitly initialized because using lombok @Data, @Getter, and @Setter do not work
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LostItemDto {

    private String itemName;

    private Integer itemAmount;

    private String itemLocation;

    private UUID itemId;

    //getters
    public UUID getItemId() {
        return itemId;
    }
    public Integer getItemAmount() {
        return itemAmount;
    }
    public String getItemName() {
        return itemName;
    }
    public String getItemLocation() {
        return itemLocation;
    }

    //setters
    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }
    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }
}
