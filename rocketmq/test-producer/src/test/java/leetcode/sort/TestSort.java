package leetcode.sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSort {

    private int[] arrays = {9, 5, 6, 2, 10};

    private ISort sort;

    @Before
    public void setUp() {
        //堆排序
//        sort = new HeapSort();
        //快速排序
//        sort = new QuickSort();
        //插入排序
        sort = new InsertSort();
    }

    @Test
    public void testSort() {
        long before = System.nanoTime();
        sort.sort(arrays);
        System.out.println("cost time: " + (System.nanoTime() - before));
        print(arrays);
    }

    public void print(int[] arrays) {
        for (int i : arrays) {
            System.out.print(i + "\t");
        }
    }

    @Test
    public void testStr() {
        String title = "id|name|artNo|spuId|skuType|price|inventorys";
        String str = "1|货品1|A001|1001|ORIGIN|1|[{\"channelCode\":\"MIAO\",\"inventory\":10},{\"channelCode\":\"TMALL\",\"inventory\":5},{\"channelCode\":\"INTIME\",\"inventory\":3}]";

        String[] titles = title.split("\\|");
        String[] strs = str.split("\\|");

        JSONObject object = new JSONObject();
        for (int i = 0; i < titles.length; i++) {
            if (JSON.isValidObject(strs[i])) {
                JSONObject tmp = JSON.parseObject(strs[i]);
                object.put(titles[i], tmp);
            } else if (JSON.isValidArray(strs[i])) {
                JSONArray tmp = JSON.parseArray(strs[i]);
                object.put(titles[i], tmp);
            } else {
                object.put(titles[i], strs[i]);
            }
        }

        System.out.println(object.toJSONString());
        SkuDO product = JSON.parseObject(object.toJSONString(), SkuDO.class);

        System.out.println(product);
    }
}

class SkuDO implements Serializable {
    /**
     * sku id
     */
    private String id;

    /**
     * sku 名称
     */
    private String name;

    /**
     * 货号
     */
    private String artNo;

    /**
     * 商品id
     */
    private String spuId;

    /**
     *  sku 类型
     */
    private String skuType;

    /**
     * 价格 分为单位
     */
    private BigDecimal price;

    /**
     * 渠道库存
     */
    @JSONField(name = "inventorys")
    List<ChannelInventoryDO> inventoryList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ChannelInventoryDO> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<ChannelInventoryDO> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @Override
    public String toString() {
        return "SkuDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", artNo='" + artNo + '\'' +
                ", spuId='" + spuId + '\'' +
                ", skuType='" + skuType + '\'' +
                ", price=" + price +
                ", inventoryList=" + inventoryList +
                '}';
    }
}

class ChannelInventoryDO implements Serializable {
    /**
     * 渠道编码, 目前包含: MIAO, TMALL, INTIME 3个渠道
     */
    private String channelCode;
    /**
     * 库存数量, 保留小数点后2位
     */
    private BigDecimal inventory;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public BigDecimal getInventory() {
        return inventory;
    }

    public void setInventory(BigDecimal inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ChannelInventoryDO{" +
                "channelCode='" + channelCode + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
