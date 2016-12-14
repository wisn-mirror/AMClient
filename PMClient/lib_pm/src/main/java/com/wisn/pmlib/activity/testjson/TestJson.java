package com.wisn.pmlib.activity.testjson;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.activity.testjson.bean.ProductList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by wisn on 2016/12/14.
 */

public class TestJson extends BaseActivity{
    private JSONObject newJSONObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            return new JSONObject();
        }
    }
    long  startTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTime=System.currentTimeMillis();
        System.out.println("初始时间"+startTime);
        JSONObject jsonObject = newJSONObject(json);

        Object json;
        // has result
        if (null != (json = jsonObject.opt("result")) &&
            (json instanceof JSONObject || json instanceof JSONArray)) {
            // has interface error
            if (json instanceof JSONObject) {
                final String error = ((JSONObject) json).optString("error");
                if (!TextUtils.isEmpty(error)) {

                    return;
                }
            }

            // success
            String result = json.toString();
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                type = ((ParameterizedType) type).getActualTypeArguments()[0];
            }
            ProductList productList = new Gson().fromJson(result, ProductList.class);
            //qiangzeng160531 服务器数据类型出错的异常处理
            long endTime=System.currentTimeMillis();
            System.out.println("结束时间"+endTime);
            System.out.println("时间"+(endTime-startTime));

        }
    }
    public  String json="{\n" +
               "  \"jsonrpc\": \"2.0\",\n" +
               "  \"id\": 8473,\n" +
               "  \"result\": {\n" +
               "    \"records\": [\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"O泡原味350ml\",\n" +
               "        \"stack_no\": \"25\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"17\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/31/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 28,\n" +
               "        \"stock\": 19\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"旺仔牛奶苹果味245ml\",\n" +
               "        \"stack_no\": \"4\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"22\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/30/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 27,\n" +
               "        \"stock\": 9\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"旺仔牛奶果汁味245ml\",\n" +
               "        \"stack_no\": \"6\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"24\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/29/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 26,\n" +
               "        \"stock\": 11\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"310ml维他柠檬茶\",\n" +
               "        \"stack_no\": \"12\",\n" +
               "        \"price\": 400,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"03\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/4/image/300x300?unique=219300c\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 4,\n" +
               "        \"stock\": 11\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"330ml百事可乐\",\n" +
               "        \"stack_no\": \"20\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"14\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/3/image/300x300?unique=5442440\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 3,\n" +
               "        \"stock\": 17\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"310ml王老吉\",\n" +
               "        \"stack_no\": \"16\",\n" +
               "        \"price\": 400,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"01\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/2/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 2,\n" +
               "        \"stock\": 15\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"360ml阿萨姆小奶茶\",\n" +
               "        \"stack_no\": \"10\",\n" +
               "        \"price\": 600,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"28\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/23/image/300x300?unique=d7fdfd2\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 23,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"330ml百事可乐\",\n" +
               "        \"stack_no\": \"19\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"02\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/3/image/300x300?unique=5442440\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 3,\n" +
               "        \"stock\": 20\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ]\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"245ml旺仔牛奶原味\",\n" +
               "        \"stack_no\": \"1\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"06\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/18/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 18,\n" +
               "        \"stock\": 16\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"330ml可乐零度\",\n" +
               "        \"stack_no\": \"22\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"08\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/10/image/300x300?unique=1de4df7\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 10,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"330ml可口可乐\",\n" +
               "        \"stack_no\": \"17\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"13\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/12/image/300x300?unique=d534cfd\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 12,\n" +
               "        \"stock\": 7\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"550ml康师傅冰红茶\",\n" +
               "        \"stack_no\": \"29\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"04\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/5/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 5,\n" +
               "        \"stock\": 6\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"500ml三得利乌龙茶\",\n" +
               "        \"stack_no\": \"28\",\n" +
               "        \"price\": 350,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"19\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/9/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 9,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"310ml加多宝\",\n" +
               "        \"stack_no\": \"15\",\n" +
               "        \"price\": 400,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"05\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/6/image/300x300?unique=06e9260\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 6,\n" +
               "        \"stock\": 3\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"350mlO泡草莓味\",\n" +
               "        \"stack_no\": \"24\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"20\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/20/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 20,\n" +
               "        \"stock\": 6\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"330ml可口可乐\",\n" +
               "        \"stack_no\": \"18\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"10\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/12/image/300x300?unique=d534cfd\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 12,\n" +
               "        \"stock\": 4\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"500ml伊藤园乌龙茶\",\n" +
               "        \"stack_no\": \"27\",\n" +
               "        \"price\": 350,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"18\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/13/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 13,\n" +
               "        \"stock\": 2\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ]\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"245ml旺仔牛奶原味\",\n" +
               "        \"stack_no\": \"2\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"07\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/18/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 18,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"旺仔牛奶果汁味245ml\",\n" +
               "        \"stack_no\": \"5\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"23\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/29/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 26,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"旺仔牛奶苹果味245ml\",\n" +
               "        \"stack_no\": \"3\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"21\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/30/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 27,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"360g银鹭八宝粥\",\n" +
               "        \"stack_no\": \"11\",\n" +
               "        \"price\": 350,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"09\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/14/image/300x300?unique=a87a11d\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 14,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"180ml雀巢铁罐咖啡\",\n" +
               "        \"stack_no\": \"7\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"25\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/24/image/300x300?unique=c719b72\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 24,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"180ml雀巢铁罐咖啡\",\n" +
               "        \"stack_no\": \"8\",\n" +
               "        \"price\": 450,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"26\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/24/image/300x300?unique=c719b72\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 24,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"300ml雪碧（罐）\",\n" +
               "        \"stack_no\": \"13\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"11\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/16/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 16,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"250ml红牛\",\n" +
               "        \"stack_no\": \"21\",\n" +
               "        \"price\": 1,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"29\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/21/image/300x300?unique=7ce392d\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 21,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"180ml火咖\",\n" +
               "        \"stack_no\": \"9\",\n" +
               "        \"price\": 400,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"27\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/22/image/300x300?unique=5d1be14\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 22,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"330ml可乐零度\",\n" +
               "        \"stack_no\": \"23\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"15\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/10/image/300x300?unique=1de4df7\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 10,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"500ml怡泉苏打水\",\n" +
               "        \"stack_no\": \"30\",\n" +
               "        \"price\": 500,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"21\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/8/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 8,\n" +
               "        \"stock\": 0\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"550ml茉莉清茶\",\n" +
               "        \"stack_no\": \"26\",\n" +
               "        \"price\": 350,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"16\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/15/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 15,\n" +
               "        \"stock\": 16\n" +
               "      },\n" +
               "      {\n" +
               "        \"promotion_details\": {\n" +
               "          \"promotional_image_links\": \"\",\n" +
               "          \"name\": \"买赠测试\",\n" +
               "          \"end_date\": \"2016-12-14\",\n" +
               "          \"start_time\": \"00:00:00\",\n" +
               "          \"freebie\": [\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"1\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"06\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 16\n" +
               "            },\n" +
               "            {\n" +
               "              \"name\": \"旺仔牛奶原味245ml\",\n" +
               "              \"stack_no\": \"2\",\n" +
               "              \"net_weight\": \"\",\n" +
               "              \"seq_no\": \"07\",\n" +
               "              \"id\": 18,\n" +
               "              \"stock\": 0\n" +
               "            }\n" +
               "          ],\n" +
               "          \"payment_way\": \"1101\",\n" +
               "          \"promotion_type\": \"one_more\",\n" +
               "          \"promotion_id\": 8,\n" +
               "          \"start_date\": \"2016-12-13\",\n" +
               "          \"promotion_price\": 0,\n" +
               "          \"end_time\": \"23:30:00\"\n" +
               "        },\n" +
               "        \"product_type\": \"All\",\n" +
               "        \"name\": \"300ml雪碧（罐）\",\n" +
               "        \"stack_no\": \"14\",\n" +
               "        \"price\": 300,\n" +
               "        \"net_weight\": \"\",\n" +
               "        \"seq_no\": \"12\",\n" +
               "        \"image_url\": \"http://10.128.231.205:904/web/image/product.template/16/image/300x300?unique=fdc1cd0\",\n" +
               "        \"product_details_image_url\": \"\",\n" +
               "        \"id\": 16,\n" +
               "        \"stock\": 1\n" +
               "      }\n" +
               "    ],\n" +
               "    \"product_type_list\": [],\n" +
               "    \"total\": 30\n" +
               "  }\n" +
               "}";
}
