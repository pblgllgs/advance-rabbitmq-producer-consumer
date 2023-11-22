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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InvoiceCancelledMessage {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate cancelDate;
    private String invoiceNumber;
    private String reason;
}
