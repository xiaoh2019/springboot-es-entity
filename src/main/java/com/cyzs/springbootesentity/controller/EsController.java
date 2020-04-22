package com.cyzs.springbootesentity.controller;

import com.cyzs.springbootesentity.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: es增删改查
 * @author: xh
 * @create: 2020-04-19 21:13
 */
@RestController("/es")
public class EsController {

    @Autowired
    TransportClient transportClient;

    @GetMapping("/esHello")
    public String esHello(){
        ActionFuture<GetResponse> actionFuture = transportClient.get(new GetRequest().index("index4").type("_doc").id("1"));
        GetResponse response = actionFuture.actionGet();
        Map<String, Object> source = response.getSource();
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
        return "success";
    }

    /**
     * 把数据存到es
     * @return
     */
    @GetMapping("/addUser")
    public String addUser(Integer id){
        User user = new User(id, "王二", 18, "wang@qq.com", "中国上海");
        IndexRequestBuilder indexRequestBuilder = transportClient.prepareIndex("user", "_doc", id + "");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = "";
        try {
            s = objectMapper.writeValueAsString(user);
            System.out.println(s);
        }catch (Exception e){}
        indexRequestBuilder.setSource(s, XContentType.JSON);
        indexRequestBuilder.execute();
        return "success";
    }

    /**
     * 从es获取值
     * @param id id
     * @return
     */
    @GetMapping("/getUserFromEs")
    public User getUser(Integer id){
        ActionFuture<GetResponse> actionFuture = transportClient.get(new GetRequest().index("user").type("_doc").id(id + ""));
        GetResponse response = actionFuture.actionGet();
        Map<String, Object> source = response.getSource();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(source, User.class);
        return user;
    }
}
