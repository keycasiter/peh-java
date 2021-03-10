import com.alibaba.fastjson.JSON;
import com.github.peh.ProcessorEngine;
import com.github.peh.context.ExecutorContextHolder;
import com.github.peh.context.ParamContextHolder;
import com.github.peh.handler.AbstractHandler;
import com.github.peh.handler.IHandler;
import com.github.peh.processor.BaseProcessor;

/**
 * created by guanjian on 2021/2/25 17:39
 */
public class ProcessorEngineTest {

    public static void main(String[] args) {

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
                ParamContextHolder.getResponse(ResponseObject.class).setPassword("123123123");
            }
        };

        IHandler handler3 = new AbstractHandler() {
            @Override
            public void handle() {

                System.out.println("handler - 3");
                RequestObject request = ParamContextHolder.getRequest(RequestObject.class);
                System.out.println(request.getUserName());
            }
        };

        IHandler handler4 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 4");
                ExecutorContextHolder.unable(processor1.getExecutor());
            }
        };

        //param
        RequestObject request = new RequestObject();
        request.setUserName("zhangsan");

        ResponseObject response = new ResponseObject();


        ProcessorEngine processorEngine = ProcessorEngine.Builder
                .aPehEngine()
                //param
                .request(request)
                .response(response)
                //processor - 1
                .appendProcessor(processor1)
                .appendHandler(handler3)
                .appendHandler(handler4)
                .appendHandler(handler2)
                .build();

        ResponseObject responseObject = (ResponseObject) processorEngine.process(request);

//        processorEngine.process();

        System.out.println(JSON.toJSONString(responseObject));
    }
}
