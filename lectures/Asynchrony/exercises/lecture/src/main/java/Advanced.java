import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

public final class Advanced {
    /** Uses `Server.uploadBatch` to upload the given texts, or cancels if 2 seconds have elapsed */
    public static CompletableFuture<Void> upload(String[] texts) {
        // TODO: add cancellation to `Server.upload` and use it here
        var cancelFlag = new AtomicBoolean(false);
        return Server.uploadBatch(texts, cancelFlag)
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    cancelFlag.set(true);
                    return null; // void instead of Void error
                });
    }

    /** Adapts `OldServer.download` to the `CompletableFuture`-based world,
        and prints the result if successful. */
    public static CompletableFuture<Void> download() {
        // TODO (without modifying OldServer!)
        var future = new CompletableFuture<String>();
        OldServer.download(future::complete, future::completeExceptionally);  // give the callbacks of the future to the method
        return future.thenAccept(System.out::println);
    }

    /** Retries `download` until it succeeds */
    public static CompletableFuture<Void> reliableDownload() {
        return download()
                .exceptionallyCompose(
                        e-> reliableDownload()
                );
    }
}
