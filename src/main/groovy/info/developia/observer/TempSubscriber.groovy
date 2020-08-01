package info.developia.observer

import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription

class TempSubscriber implements Subscriber<TempInfo> {
    private Subscription subscription


    @Override
    void onSubscribe(Subscription subscription) {
        this.subscription = subscription
        subscription.request(1)
    }

    @Override
    void onNext(TempInfo tempInfo) {
        println(tempInfo)
        subscription.request(1)
    }

    @Override
    void onError(Throwable t) {
        println(t.getMessage())
    }

    @Override
    void onComplete() {
        println('Job done!!')
    }
}
