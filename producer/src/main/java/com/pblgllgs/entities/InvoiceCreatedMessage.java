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
public class InvoiceCreatedMessage {

    private double amount;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;
    private String currency;
    private String invoiceNumber;
}
