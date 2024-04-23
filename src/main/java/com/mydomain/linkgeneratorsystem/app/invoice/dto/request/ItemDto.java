package com.mydomain.linkgeneratorsystem.app.invoice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
    private String description;
    private Double cost;
    private Integer quantity;
}
