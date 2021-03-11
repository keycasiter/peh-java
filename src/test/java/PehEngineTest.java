import com.alibaba.fastjson.JSON;
import com.github.peh.PehEngine;
import com.github.peh.PehEngineBuilder;
import com.github.peh.context.ExecutorContextHolder;
import com.github.peh.context.ParamContextHolder;
import com.github.peh.handler.AbstractHandler;
import com.github.peh.handler.IHandler;
import com.github.peh.processor.BaseProcessor;

import java.util.Optional;

/**
 * created by guanjian on 2021/2/25 17:39
 */
public class PehEngineTest {

    public static void main(String[] args) {

        String certNoKey = "certNo";

        BaseProcessor processor1 = new BaseProcessor("processor - 1");

        BaseProcessor processor2 = new BaseProcessor("processor - 2");

        IHandler handler1 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 1");
            }
        };

        IHandler handler2 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 2");
                Optional.ofNullable(ParamContextHolder.getResponse(ResponseObject.class)).ifPresent(x->{
                    x.setPassword("123123123123123");
                });
                System.out.println(getVariable(certNoKey));;
                Optional.ofNullable(ParamContextHolder.getResponse(ResponseObject.class)).ifPresent(x->{
                    x.setCertNo(getVariable(certNoKey,String.class));
                });
            }
        };

        IHandler handler3 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 3");
                RequestObject request = ParamContextHolder.getRequest(RequestObject.class);
                System.out.println(Optional.ofNullable(request).map(x->x.getUserName()));

                setVariable(certNoKey,"13020202020200");
            }
        };

        IHandler handler4 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 4");
                System.out.println(getVariable(certNoKey));

                //停止Executor
//                unableExecutor(processor1.getExecutor());
                //停止Processor
//                unableProcessor();
            }
        };

        //param
        RequestObject request = new RequestObject();
        request.setUserName("zhangsan");

        ResponseObject response = new ResponseObject();


        PehEngine processorEngine = PehEngineBuilder.builder()
                //param
//                .request(request)
//                .response(response)
                //processor - 1
                .appendProcessor(processor1)
                .appendHandler(handler3)
                .appendHandler(handler4)
                .appendHandler(handler1)
                .appendProcessor()
                .appendHandler(handler2)
                .build();

//        ResponseObject responseObject = (ResponseObject) processorEngine.process(request);
//        System.out.println(JSON.toJSONString(responseObject));
        processorEngine.process();


    }
}
