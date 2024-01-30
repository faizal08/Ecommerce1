package org.glamgaze.library.service.impl;

import org.glamgaze.library.model.Customer;
import org.glamgaze.library.model.Product;
import org.glamgaze.library.model.Wishlist;
import org.glamgaze.library.repository.WishlistRepository;
import org.glamgaze.library.service.ProductService;
import org.glamgaze.library.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService
{
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ProductService productService;
    @Override
    public List<Wishlist> findAllByCustomer(Customer customer) {
        List<Wishlist>Wishlists=wishlistRepository.findAllByCustomerId(customer.getId());
        return Wishlists;
    }

    @Override
    public boolean findByProductId(long productId, Customer customer) {
        boolean exists= wishlistRepository.findByProductIdAndCustomerId(productId,customer.getId());
        return exists;
    }

    @Override
    public Wishlist save(long productId, Customer customer) {
        Product product=productService.findBYId(productId);
        Wishlist wishlist=new Wishlist();
        wishlist.setProduct(product);
        wishlist.setCustomer(customer);
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    @Override
    public void deleteWishlist(long id) {
        Wishlist wishlist=wishlistRepository.findById(id);
        wishlistRepository.delete(wishlist);
    }
}
