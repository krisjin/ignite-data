package org.ignite.datacogn.compress;

import com.alibaba.fastjson.JSONObject;
import org.ignite.datacogn.model.InventoryForecastSku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩比高的慢速算法
 * User:krisjin
 * Date:2019/3/20
 */
public class GzipHelper {


    public static byte[] gzip(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.finish();
        gzip.close();
        byte[] ret = bos.toByteArray();
        bos.close();
        return ret;
    }

    public static byte[] ungzip(byte[] data) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        byte[] buf = new byte[1024];
        int num = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((num = gzip.read(buf, 0, buf.length)) != -1) {
            bos.write(buf, 0, num);
        }
        gzip.close();
        bis.close();
        byte[] ret = bos.toByteArray();
        bos.flush();
        bos.close();
        return ret;
    }


    public static void main(String[] args) throws Exception {

        String str = JSONObject.toJSONString(InventoryForecastSku.getTestData());

        System.out.println("before size:" + str.getBytes().length);

        long st = System.currentTimeMillis();
        byte[] gzipData = gzip(str.getBytes());

        System.out.println("压缩耗时:" + (System.currentTimeMillis() - st) + "ms");
        st = System.currentTimeMillis();
        System.out.println("after size:" + gzipData.length);


        byte[] retData = ungzip(gzipData);
        System.out.println("解压缩耗时:" + (System.currentTimeMillis() - st) + "ms");
        String newData = new String(retData);
        System.out.println("--------uncompress data:---------\n" + newData);

    }
}
