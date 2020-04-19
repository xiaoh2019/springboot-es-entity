package com.cyzs.springbootesentity;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @description:
 * @author: xh
 * @create: 2020-04-19 22:17
 */
@SpringBootTest
public class TransportClientTest {

    @Autowired
    TransportClient transportClient;

    @Test
    public void test(){
        ActionFuture<GetResponse> actionFuture = transportClient.get(new GetRequest().index("index4").type("_doc").id("1"));
        GetResponse response = actionFuture.actionGet();
        Map<String, Object> source = response.getSource();
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }

    }
}
