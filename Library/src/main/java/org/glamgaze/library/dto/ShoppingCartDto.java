package org.glamgaze.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glamgaze.library.model.Customer;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto
{
    private Long id;
    private double totalPrice;
    private int totalItems;
    private Customer customer;
    private Set<CartItemDto> cartItemDto;
}
