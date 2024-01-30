package org.glamgaze.library.service;

import org.glamgaze.library.Exception.CustomerNotFoundException;
import org.glamgaze.library.dto.CustomerDto;
import org.glamgaze.library.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService
{
    Customer findByEmail(String email);
    Customer save(CustomerDto customerDto);
    List<Customer> findAll();
    Customer findById(long id);
    void disable(long id);
    void enable(long id);
    Customer update(CustomerDto customerDto);
    CustomerDto findByEmailCustomerDto(String email);
    CustomerDto updateAccount(CustomerDto customerDto,String email);
    void changePass(CustomerDto customerDto);
    void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException;
    Customer getByResetPasswordToken(String token);
    void updatePassword(Customer customer, String newPassword);
    Optional<List<Customer>> getByReferalToken(String token);
    void updateReferalCodeToken(String token,String email);
}
