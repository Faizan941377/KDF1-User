package com.nextsuntech.kdf1.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextsuntech.kdf1.Model.CheckOutDataModel;

public class CheckOutResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("autoId")
    @Expose
    private Integer autoId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

}

