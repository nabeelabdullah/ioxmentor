package com.ioxmentor.service;

import org.springframework.stereotype.Service;

/**
 * Created by nabeelabdullah on 22/10/17.
 */
public class TaxService {

    public static Float getFinalAmount(Float amount) {
        return amount * (1.18f);
    }
}
