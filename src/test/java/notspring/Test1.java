package notspring;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.util.Map;

/**
 * @description:
 * @author: xh
 * @create: 2020-04-19 21:42
 */
public class Test1 {

    @Test
    public void test()throws Exception{
        //参数在es的config目录的配置文件中
        Settings settings = Settings.builder().put("cluster.name","my-application").build();
        //客户端,指定ip和端口
        TransportClient transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"),9300));
        //数据查询 第一个参数是index索引，第二个参数是type，第三个参数是id
        GetRequestBuilder prepareGet = transportClient.prepareGet("index4", "_doc", "1");
        GetResponse response = prepareGet.get();
        Map<String, Object> source = response.getSource();
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
    }
}
