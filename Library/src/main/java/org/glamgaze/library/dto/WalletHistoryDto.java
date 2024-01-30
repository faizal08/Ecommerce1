package org.glamgaze.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glamgaze.library.enums.WalletTransactionType;
import org.glamgaze.library.model.Wallet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletHistoryDto
{
    private Long id;
    private double amount;
    private WalletTransactionType type;
    private String transactionStatus;
    private Wallet wallet;
}
