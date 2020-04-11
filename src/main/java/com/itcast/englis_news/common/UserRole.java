package com.itcast.englis_news.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRole {
    private Integer id;

    /**
     * UDI
     */
    private Integer uid;

    /**
     * RID
     */
    private Integer rid;
}