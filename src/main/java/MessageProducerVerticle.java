import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class MessageProducerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        EventBus eventBus = vertx.eventBus();

        // Peer to peer
        DeliveryOptions options = new DeliveryOptions();
        options.addHeader("some-header", "some-value");
        eventBus.send("news.uk.sport", "[This message use send method.]", options);

        //
        eventBus.publish("news.uk.sport", "[This message use publish method.]");
    }
}
