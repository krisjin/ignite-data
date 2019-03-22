package org.ignite.datacogn.model;

import org.msgpack.annotation.Message;

/**
 * User:krisjin
 * Date:2019/3/22
 */
@Message
public class InventoryForecastSkuModel {

    private String skuId;
    private String skuName;
    private String dcId;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getDcId() {
        return dcId;
    }

    public void setDcId(String dcId) {
        this.dcId = dcId;
    }
}
