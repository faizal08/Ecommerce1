package org.glamgaze.library.repository;

import org.glamgaze.library.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findCouponByCode(String code);
    Coupon findById(long id);
    boolean existsByCode(String code);
}