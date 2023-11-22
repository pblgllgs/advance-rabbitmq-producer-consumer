package com.pblgllgs.entities;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
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