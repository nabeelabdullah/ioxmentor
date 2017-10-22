package com.ioxmentor.enums;

import com.ioxmentor.entity.Coupon;
import com.ioxmentor.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nabeelabdullah on 22/10/17.
 */
public interface CouponRepo extends JpaRepository<Coupon, Long> {

    Coupon findByCoupon(String coupon);
}
