package com.lsl.mynews.image.widget;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lsl.mynews.bean.ImageBean;
import com.lsl.mynews.common.HttpUtils;
import com.lsl.mynews.common.ResultCallBack;
import com.lsl.mynews.util.L;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2016/6/11.
 * 读取图片资源
 */
public class LoadImageResource {

    private static List<ImageBean> imageBeanList = new ArrayList<>();

    public static void startLoading(String url){
        HttpUtils.get(url, new ResultCallBack() {
            @Override
            public List<ImageBean> onSuccess(Response response) {
                try {
                    String imageJson = response.body().string();

                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = jsonParser.parse(imageJson).getAsJsonArray();
                    ImageBean imageBean = null;
                    for (int i = 0; i <jsonArray.size();i++){
                        JsonElement jsonElement = jsonArray.get(i);
                        imageBean = gson.fromJson(jsonElement.getAsJsonObject(), ImageBean.class);
                        imageBeanList.add(imageBean);
                        ImageFragment.getInstance().setImageData(imageBean);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageFragment.getInstance().refreshHandler.sendEmptyMessage(0x001);
                return imageBeanList;
            }

            @Override
            public void onFailure(Exception e) {
                L.i("ImageResource","onFailure--->"+e.getMessage());
            }
        });
    }
}
