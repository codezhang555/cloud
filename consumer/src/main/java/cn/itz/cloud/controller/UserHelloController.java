package cn.itz.cloud.controller;

import cn.itz.cloud.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * @author CodeZhang
 * @ProjectName cloud
 * @Package cn.itz.cloud.controller
 * @Version 1.0
 * @date 2021/1/3 18:57
 */
@RestController
public class UserHelloController {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @GetMapping("/hello1")
    public String hello1() {
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://localhost:8081/hello");
            con = ((HttpURLConnection) url.openConnection());
            if (con.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    int count  = 0;

    @GetMapping("/hello2")
    public String hello2() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance instance = provider.get((count++) % provider.size());
        String host = instance.getHost();
        int port = instance.getPort();
        HttpURLConnection con = null;
        StringBuffer sb = new StringBuffer();
        sb.append("http://").append(host).append(":").append(port).append("/hello");
        try {
            URL url = new URL(sb.toString());
            con = ((HttpURLConnection) url.openConnection());
            if (con.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @GetMapping("/hello3")
    public String hello3(){
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        ServiceInstance instance = provider.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer sb = new StringBuffer();
        sb.append("http://").append(host).append(":").append(port).append("/hello");
        String s = restTemplate.getForObject(sb.toString(), String.class);
        return s;
    }

    /**
     * Eureka负载均衡调用服务
     * @return
     */
    @GetMapping("/hello4")
    public String hello4(){
        return restTemplate.getForObject("http://provider/hello",String.class);
    }

    /**
     * getForObject返回的是一个对象，这个对象就是服务端返回的具体值。
     * getForEntity返回的是一个ResponseEntity,这个ResponseEntity中除了服务端返回的具体数据外，还保留了Http响应头的数据。
     */
    @GetMapping("/hello5")
    public void hello5(){
        String s = restTemplate.getForObject("http://provider/hello2?name={1}", String.class, "zhang");
        System.out.println(s);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://provider/hello2?name={1}", String.class, "zhang");
        String body = responseEntity.getBody();
        System.out.println("body:"+body);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("HttpStatus:"+statusCode);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        System.out.println("statusCodeValue:"+ statusCodeValue);
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        System.out.println("=======header=======");
        for (String s1 : keySet) {
            System.out.println(s1+":"+headers.get(s1));
        }
    }

    /**
     * post参数到底是key-value形式还是json形式，主要看第二个参数，如果第二个参数是multiValueMap,
     * 则参数是以key-value形式来传递的。如果是一个普通对象，则参数是以json形式来传递的。
     */
    @GetMapping("/hello6")
    public void hello6(){
        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        map.add("id",99);
        map.add("name","zhang");
        map.add("password","123");
        User user = restTemplate.postForObject("http://provider/user1", map, User.class);
        System.out.println(user);
        user.setId(98);
        user= restTemplate.postForObject("http://provider/user2", user, User.class);
        System.out.println(user);
    }
}
