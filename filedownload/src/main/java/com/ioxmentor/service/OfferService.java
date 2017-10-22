package com.ioxmentor.service;

import com.ioxmentor.entity.Coupon;
import com.ioxmentor.enums.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nabeelabdullah on 22/10/17.
 */
@Component
public class OfferService {

    @Autowired
    private CouponRepo couponRepo;

    public Coupon getCoupon(String coupon) {

        if (coupon != null) {
            return couponRepo.findByCoupon(coupon);
        }
        return null;
    }
}
