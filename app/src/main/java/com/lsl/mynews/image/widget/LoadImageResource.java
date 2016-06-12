package com.lsl.mynews.image.widget;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lsl.mynews.bean.ImageBean;
import com.lsl.mynews.common.HttpUtils;
import com.lsl.mynews.common.ResultCallBack;
import com.lsl.mynews.util.L;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by HP on 2016/6/11.
 * 读取图片资源
 */
public class LoadImageResource {

    public static void startLoading(String url){
        HttpUtils.get(url, new ResultCallBack() {
            @Override
            public void onSuccess(Response response) {
                try {
                    String imageJson = response.body().string();

                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = jsonParser.parse(imageJson).getAsJsonArray();
                    for (int i = 0; i <jsonArray.size();i++){
                        JsonElement jsonElement = jsonArray.get(i);
                        ImageBean imageBean = gson.fromJson(jsonElement.getAsJsonObject(), ImageBean.class);
                        L.i("ImageResource","success--->"+imageBean.getTitle());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageFragment.getInstance().refreshHandler.sendEmptyMessage(0x001);

            }

            @Override
            public void onFailure(Exception e) {
                L.i("ImageResource","onFailure--->"+e.getMessage());
            }
        });
    }
}
