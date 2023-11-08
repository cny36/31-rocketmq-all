package org.example.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : chennengyuan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStep {
    private long id;
    private String desc;

}
