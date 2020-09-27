package com.example.myapplication.WXAPI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mm.opensdk.utils.ILog;

import org.greenrobot.eventbus.EventBus;

public class WxApi  extends Activity implements IWXAPIEventHandler {

    private Context context;

    public void WxApi(Context mContext){
        this.context = mContext;
    }

    IWXAPI iwxapi = WXAPIFactory.createWXAPI(this,"app_id",false);



    @Override
    public void onReq(BaseReq baseReq) {
        iwxapi.sendReq(baseReq);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if(baseResp instanceof SendAuth.Resp){
            EventBus.getDefault().post((SendAuth.Resp)baseResp);
        }
    }
}
