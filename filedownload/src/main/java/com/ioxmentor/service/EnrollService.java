package com.ioxmentor.service;

import com.ioxmentor.entity.Enroll;
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

    public Enroll enroll(Long uId, Long cId) {
        Enroll enroll = enrollRepo.findByCourseIdAndUserId(cId, uId);
        if (enroll == null) {
            enroll = new Enroll();
            enroll.setUserId(uId);
            enroll.setCourseId(cId);
            enroll.setAmountPaid(0.0f);
            enroll.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            enroll.setPaymentStatus(PaymentStatus.UNPAID);
            enroll = enrollRepo.save(enroll);
        }
        return enroll;
    }

}
