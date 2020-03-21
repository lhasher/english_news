package com.itcast.englis_news.common;

import java.io.Serializable;
import lombok.Data;

@Data
public class Privilege implements Serializable {
    private Integer pid;

    private String pname;

    private static final long serialVersionUID = 1L;
}