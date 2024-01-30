package org.glamgaze.library.service;

public interface InvoiceService
{
    byte[] generateInvoice(Long orderId);
}
