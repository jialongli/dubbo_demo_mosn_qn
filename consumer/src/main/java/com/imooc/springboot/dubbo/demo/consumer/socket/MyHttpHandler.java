package com.imooc.springboot.dubbo.demo.consumer.socket;

import com.imooc.springboot.dubbo.demo.DemoService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressTask;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 处理/myserver路径请求的处理器类
 */
public class MyHttpHandler implements HttpHandler {
    private DemoService testService;

    public MyHttpHandler(DemoService d) {
        this.testService = d;
    }

    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            List<String> concurrencyLevel = httpExchange.getRequestHeaders().get("concurrencyLevel");
            List<String> totalRequests = httpExchange.getRequestHeaders().get("totalRequests");
            List<String> bytes = httpExchange.getRequestHeaders().get("byte");
            if (CollectionUtils.isEmpty(concurrencyLevel) || CollectionUtils.isEmpty(totalRequests) || CollectionUtils.isEmpty(bytes)) {
                return;
            } else {
                String cl = concurrencyLevel.get(0);
                String tr = totalRequests.get(0);
                String b = bytes.get(0);
                int bb = Integer.parseInt(b);
                byte[] baa = new byte[bb];
                for (int i = 0; i < bb; i++) {
                    baa[i] = 0;
                }
                System.out.println("并发数:"+cl+",请求总数"+tr+"数据大小(字节):"+baa.length);
                System.out.println("=======开始压测=======");

                StressTestUtils.testAndPrint(Integer.parseInt(cl), Integer.parseInt(tr), new StressTask() {
                    public Object doTask() throws Exception {
                        String result = testService.sayHello("123123");
                        return null;
                    }
                });
                System.out.println("=======结束压测=======");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取请求头
     *
     * @param httpExchange
     * @return
     */
    private String getRequestHeader(HttpExchange httpExchange) {
        Headers headers = httpExchange.getRequestHeaders();
        return headers.entrySet().stream()
                .map((Map.Entry<String, List<String>> entry) -> entry.getKey() + ":" + entry.getValue().toString())
                .collect(Collectors.joining("<br/>"));
    }

    /**
     * 获取请求参数
     *
     * @param httpExchange
     * @return
     * @throws Exception
     */
    private String getRequestParam(HttpExchange httpExchange) throws Exception {
        String paramStr = "";

        if (httpExchange.getRequestMethod().equals("GET")) {
            //GET请求读queryString
            paramStr = httpExchange.getRequestURI().getQuery();
        } else {
            //非GET请求读请求体
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "utf-8"));
            StringBuilder requestBodyContent = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                requestBodyContent.append(line);
            }
            paramStr = requestBodyContent.toString();
        }

        return paramStr;
    }

    /**
     * 处理响应
     *
     * @param httpExchange
     * @param responsetext
     * @throws Exception
     */
    private void handleResponse(HttpExchange httpExchange, String responsetext) throws Exception {
        //生成html
        StringBuilder responseContent = new StringBuilder();
        responseContent.append("<html>")
                .append("<body>")
                .append(responsetext)
                .append("</body>")
                .append("</html>");
        String responseContentStr = responseContent.toString();
        byte[] responseContentByte = responseContentStr.getBytes("utf-8");

        //设置响应头，必须在sendResponseHeaders方法之前设置！
        httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");

        //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
        httpExchange.sendResponseHeaders(200, responseContentByte.length);

        OutputStream out = httpExchange.getResponseBody();
        out.write(responseContentByte);
        out.flush();
        out.close();
    }
}
