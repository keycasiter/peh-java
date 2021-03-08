import com.github.peh.context.ExecutorContextHolder;
import com.github.peh.context.ProcessorContextHolder;
import com.github.peh.handler.AbstractHandler;
import com.github.peh.handler.IHandler;
import com.github.peh.processor.BaseProcessor;
import com.github.peh.processor.ProcessorEngine;

/**
 * created by guanjian on 2021/2/25 17:39
 */
public class ProcessorTest {

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
            }
        };

        IHandler handler3 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 3");
            }
        };

        IHandler handler4 = new AbstractHandler() {
            @Override
            public void handle() {
                System.out.println("handler - 4");
                ExecutorContextHolder.stop(processor1.getExecutor());
            }
        };


        ProcessorEngine processorEngine = ProcessorEngine.Builder
                .aBaseProcessor()
                //processor - 1
                .appendProcessor(processor1)
                .appendHandler(handler1)
                .preposeHandler(handler2)
                .preposeHandler(handler4)
                .preposeHandler(handler3)
                .parallel()
                //processor - 2
                .appendHandler(handler2)
                //processor - 3
                .appendProcessor()
                .appendHandler(handler1)
                .appendHandler(handler1)
                .appendHandler(handler1)
                .build();

        processorEngine.process();

    }
}
