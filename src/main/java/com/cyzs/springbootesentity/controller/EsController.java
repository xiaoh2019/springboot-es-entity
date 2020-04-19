package com.cyzs.springbootesentity.controller;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: xh
 * @create: 2020-04-19 21:13
 */
@RestController
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
}
