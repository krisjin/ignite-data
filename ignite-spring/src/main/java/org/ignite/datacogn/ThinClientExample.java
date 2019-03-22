
package org.ignite.datacogn;

import com.alibaba.fastjson.JSONObject;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.ignite.datacogn.model.InventoryForecastSku;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.List;


public class ThinClientExample {

    public static void main(String[] args) {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
        IgniteClient igniteClient = Ignition.startClient(cfg);
        try {

            final String CACHE_NAME = "sku";

            ClientCache cache = igniteClient.getOrCreateCache(CACHE_NAME);

            MessagePack messagePack = new MessagePack();

            Integer key = 100;

            List<InventoryForecastSku> data = InventoryForecastSku.getTestData();


            String rawData = JSONObject.toJSONString(data);

            System.out.println("压缩前大小:" + rawData.getBytes().length);

            byte[] val2 = messagePack.write(data);
            System.out.println("压缩后大小:" + val2.length);

            //存数据
            cache.put(key, val2);

            //取数据
            byte[] retVal = (byte[]) cache.get(key);

            List<InventoryForecastSku> value = messagePack.read(retVal, Templates.tList(messagePack.lookup(InventoryForecastSku.class)));


            System.out.println("load data: \n" + JSONObject.toJSONString(value));
        } catch (ClientException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.format("Unexpected failure: %s\n", e);
        }
    }













}
