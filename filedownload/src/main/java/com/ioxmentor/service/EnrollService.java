package com.ioxmentor.service;

import com.ioxmentor.entity.Coupon;
import com.ioxmentor.entity.Course;
import com.ioxmentor.entity.Enroll;
import com.ioxmentor.enums.AmountType;
import com.ioxmentor.enums.PaymentStatus;
import com.ioxmentor.repo.EnrollRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by nabeelabdullah on 09/10/17.
 */
@Service
public class EnrollService {

    @Autowired
    private EnrollRepo enrollRepo;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OfferService offerService;

    public Enroll enroll(Long uId, Long cId, String ap) {
        Enroll enroll = enrollRepo.findByCourseIdAndUserId(cId, uId);
        if (enroll == null) {
            enroll = new Enroll();
            Course course = courseService.getCourseById(cId);
            enroll.setUserId(uId);
            enroll.setAmmountToBePaid(TaxService.getFinalAmount(course.getBasePrice()));
            enroll.setCourseId(cId);
            enroll.setAmountPaid(0.0f);
            enroll.setActualPrice(course.getBasePrice());
            enroll.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            enroll.setPaymentStatus(PaymentStatus.UNPAID);
            enroll = enrollRepo.save(enroll);
        }
        return enroll;
    }

    public Enroll deleteCoupon(Long uId, Long cId) {
        Enroll enroll = enrollRepo.findByCourseIdAndUserId(cId, uId);
        enroll.setAmmountToBePaid(TaxService.getFinalAmount(enroll.getActualPrice()));
        enroll.setCoupon(null);
        enroll.setCouponApplied(false);
        enroll = enrollRepo.save(enroll);
        return enroll;
    }

    public Enroll applyCoupon(Long uId, Long cId, String ap) {
        Enroll enroll = enrollRepo.findByCourseIdAndUserId(cId, uId);
        Coupon coupon = offerService.getCoupon(ap);
        Course course = courseService.getCourseById(cId);
        if (coupon != null && coupon.getExpAt().getTime() > Calendar.getInstance().getTimeInMillis() && coupon.getMaxUsable() > 0) {
            AmountType type = coupon.getAmountType();
            enroll.setCouponApplied(true);
            enroll.setCoupon(coupon.getCoupon());
            enroll.setAmmountToBePaid(TaxService.getFinalAmount(getAmountAfterDiscount(coupon.getAmountOff(), course.getBasePrice(), type)));
        }
        enroll = enrollRepo.save(enroll);
        return enroll;
    }

    public Enroll enrollPayment(Long enId, Float amount) {
        Enroll enroll = enrollRepo.findOne(enId);
        enroll.setAmountPaid(amount);
        enroll.setPaidAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        enroll.setPaymentStatus(PaymentStatus.PAID);
        enroll = enrollRepo.save(enroll);
        return enroll;
    }

    private Float getAmountAfterDiscount(Float dis, Float amt, AmountType amountType) {
        if (amountType == AmountType.FIXED) {
            return amt - dis;
        }
        if (amountType == AmountType.PERCENTAGE) {
            return amt - amt * dis / 100;
        }
        return amt;
    }


}
