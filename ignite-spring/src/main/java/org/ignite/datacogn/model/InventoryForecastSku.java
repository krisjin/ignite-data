package org.ignite.datacogn.model;

import org.msgpack.annotation.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * User:krisjin
 * Date:2019/3/22
 */
@Message
public class InventoryForecastSku {

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


    public static List<InventoryForecastSku> getTestData() {
        List<InventoryForecastSku> skuList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            InventoryForecastSku skuModel = new InventoryForecastSku();
            skuModel.setDcId("1000" + i);
            skuModel.setSkuName("0123456789abcde");
            skuModel.setDcId("10001");
            skuList.add(skuModel);
        }
        return skuList;

    }

}
