package info.developia.observer

import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

class TempProcessor implements Flow.Processor<TempInfo, TempInfo> {

    private Subscriber<? super TempInfo> subscriber

    @Override
    void subscribe(Subscriber<? super TempInfo> subscriber) {
        this.subscriber = subscriber
    }

    @Override
    void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription)
    }

    @Override
    void onNext(TempInfo tempInfo) {
        subscriber.onNext(new TempInfo(tempInfo.getTown(), toCelsius(tempInfo.getTemp())),)
    }

    @Override
    void onError(Throwable throwable) {
        subscriber.onError(throwable)
    }

    @Override
    void onComplete() {
        subscriber.onComplete()
    }

    static int toCelsius(int temp) {
        return (temp - 32) * 5 / 9
    }
}
