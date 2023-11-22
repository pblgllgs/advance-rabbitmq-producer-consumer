package com.pblgllgs.entities;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InvoicePaidMessage {
    
    private String invoiceNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate paidDate;
    private String paymentNumber;
}
