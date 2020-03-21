package com.itcast.englis_news.common;

import java.io.Serializable;
import lombok.Data;

@Data
public class Role implements Serializable {
    private Integer rid;

    private String rname;

    private static final long serialVersionUID = 1L;
}