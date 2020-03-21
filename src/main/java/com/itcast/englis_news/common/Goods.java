package com.itcast.englis_news.common;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class Goods implements Serializable {
    private Integer id;

    private String name;

    private Integer cateId;

    private Integer brandId;

    private BigDecimal price;

    private Boolean isShow;

    private Boolean isSaleoff;

    private static final long serialVersionUID = 1L;
}