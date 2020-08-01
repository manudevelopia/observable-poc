package info.developia.observer


import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Flow

class TempSubscription implements Flow.Subscription {

    private final Flow.Subscriber<? super TempInfo> subscriber
    private final String town

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber
        this.town = town
    }

    @Override
    void request(long n) {
        executorService.submit({ ->
            for (int i = 0; i < n; i++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town))
                } catch (Exception e) {
                    subscriber.onError(e)
                }
            }
        })
    }

    @Override
    void cancel() {
        subscriber.onComplete()
    }
}
