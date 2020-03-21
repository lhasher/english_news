package com.itcast.englis_news.common;

public enum ResponseStatus {
    CODE_200("OK"),CODE_302("Not Found"),CODE_404("资源不存在");
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseStatus(String message) {
        this.message = message;
    }
}
