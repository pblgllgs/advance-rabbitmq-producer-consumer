package com.pblgllgs.producer.entities;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DummyMessage {

    private String content;
    private int publishOrder;

}
