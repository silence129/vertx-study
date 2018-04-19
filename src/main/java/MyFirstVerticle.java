import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;

public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World!");
        }).listen(8080, res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening on actual port: " + server.actualPort());
                startFuture.complete();
            } else {
                System.out.println("Failed to bind!");
                startFuture.fail(res.cause());
            }
        });

        WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
        executor.executeBlocking(future -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete("verticle result");
        }, res -> {
            System.out.println("The result is: " + res.result());
        });

        long timerID = vertx.setTimer(1000, id -> {
            System.out.println("And one second later this is printed");
        });

        System.out.println("First this is printed");
    }
}
