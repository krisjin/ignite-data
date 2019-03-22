/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ignite.datacogn;

import com.alibaba.fastjson.JSONObject;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.ignite.datacogn.model.InventoryForecastSkuModel;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;


public class ClientPutGetExample {
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
        IgniteClient igniteClient = Ignition.startClient(cfg);
        try {

            final String CACHE_NAME = "sku";

            ClientCache cache = igniteClient.getOrCreateCache(CACHE_NAME);

            MessagePack messagePack = new MessagePack();

            Integer key = 100;

            List<InventoryForecastSkuModel> data = getData();


            String rawData = JSONObject.toJSONString(data);

            System.out.println("压缩前大小:" + rawData.getBytes().length);

            byte[] val2 = messagePack.write(rawData);
            System.out.println("压缩后大小:" + val2.length);

            //存数据
            cache.put(key, val2);

            //取数据
            byte[] retVal = (byte[]) cache.get(key);

            List<InventoryForecastSkuModel> value = messagePack.read(retVal, Templates.tList(messagePack.lookup(InventoryForecastSkuModel.class)));


//            List jsonData = new Converter(value).read(new ArrayList<InventoryForecastSkuModel>());

            System.out.format(">>> Loaded [%s] from the xxxcache.\n", value);
        } catch (ClientException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.format("Unexpected failure: %s\n", e);
        }
    }


    private static List<InventoryForecastSkuModel> getData() {
        List<InventoryForecastSkuModel> skuList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            InventoryForecastSkuModel skuModel = new InventoryForecastSkuModel();
            skuModel.setDcId("10000" + i);
            skuModel.setSkuName("0123456789abcde");
            skuModel.setDcId("10001");
            skuList.add(skuModel);
        }
        return skuList;
    }
}
