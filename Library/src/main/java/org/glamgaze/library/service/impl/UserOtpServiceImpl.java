package org.glamgaze.library.service.impl;

import org.glamgaze.library.model.UserOtp;
import org.glamgaze.library.repository.UserOtpRepository;
import org.glamgaze.library.service.UserOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOtpServiceImpl implements UserOtpService
{
    @Autowired
    private UserOtpRepository userOTPRepository;
    @Override
    public void saveOrUpdate(UserOtp userOTP) {
        userOTPRepository.save(userOTP);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userOTPRepository.existsByEmail(email);
    }

    @Override
    public UserOtp findByEmail(String email) {
        return userOTPRepository.findByEmail(email);
    }
}
