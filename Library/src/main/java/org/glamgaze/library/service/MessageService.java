package org.glamgaze.library.service;

public interface MessageService
{
    String generateOtp();
    void sendOtp(String otp);
}
