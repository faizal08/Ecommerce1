package org.glamgaze.library.service;

import org.glamgaze.library.dto.ProductDto;
import org.glamgaze.library.model.ShoppingCart;

public interface ShoppingCartService
{
    ShoppingCart addItemToCart(ProductDto productDto, int quantity, String username, Long size);
    ShoppingCart updateCart(ProductDto productDto, int quantity, String username,Long cart_Item_Id,long size_id);
    ShoppingCart removeItemFromCart(ProductDto productDto, String username);
    ShoppingCart updateTotalPrice(Double newTotalPrice,String username);
    void deleteCartById(long id);
}
