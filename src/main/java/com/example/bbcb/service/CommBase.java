package com.example.bbcb.service;

import com.example.bbcb.util.IdProviderUtil;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public abstract class CommBase {
    @Getter
    protected String objectId;//请求应答服务唯一标识
    @Getter
    protected long beginTime;//开始时间戳，毫秒
    @Getter
    protected long endTime;//结束时间戳，毫秒


    public CommBase(){
        objectId= IdProviderUtil.createUUID();
    }

   // public abstract void init();

    //public abstract void destroy();
}
