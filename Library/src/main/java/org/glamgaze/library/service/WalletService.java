package org.glamgaze.library.service;

import org.glamgaze.library.dto.WalletHistoryDto;
import org.glamgaze.library.model.Customer;
import org.glamgaze.library.model.Order;
import org.glamgaze.library.model.Wallet;
import org.glamgaze.library.model.WalletHistory;

import java.util.List;

public interface WalletService
{
    List<WalletHistoryDto> findAllById(long id);
    Wallet findByCustomer(Customer customer);
    WalletHistory save(double amount, Customer customer);
    WalletHistory findById(long id);
    void updateWallet(WalletHistory walletHistory, Customer customer, boolean status);
    void debit(Wallet wallet,double totalPrice);
    void returnCredit(Order order, Customer customer);
    void addWalletToReferalEarn(long id);
}
