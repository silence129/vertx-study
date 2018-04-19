import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName());

        vertx.deployVerticle(MessageProducerVerticle.class.getName());
        vertx.deployVerticle(MessageConsumerVerticle.class.getName());

        vertx.executeBlocking(future -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete("result");
        }, res -> {
            System.out.println("The result is: " + res.result());
        });
    }
}
