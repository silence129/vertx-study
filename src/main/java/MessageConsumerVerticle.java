import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class MessageConsumerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        EventBus eb = vertx.eventBus();

        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });

        MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
        consumer.handler(message -> {
            System.out.println("I have received a message2: " + message.body());
        });

        MessageConsumer<String> consumer2 = eb.consumer("news.uk.sport");
        consumer2.handler(message -> {
            System.out.println("I have received a message3: " + message.body());
        });




    }
}
