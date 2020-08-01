package info.developia.observer


import java.util.concurrent.Flow.Publisher
import java.util.concurrent.Flow.Subscriber

class App {
    static void main(String[] args) {
        getTemperatures('Toledo').subscribe(new TempSubscriber())
        getTemperaturesCelsius('Madrid').subscribe(new TempSubscriber())
    }

    static Publisher<TempInfo> getTemperatures(String town) {
        return { subscriber ->
            subscriber.onSubscribe(new TempSubscription(subscriber as Subscriber, town))
        }
    }

    static Publisher<TempInfo> getTemperaturesCelsius(String town) {
        return { subscriber ->
            TempProcessor tempProcessor = new TempProcessor()
            tempProcessor.subscribe(subscriber as Subscriber)
            subscriber.onSubscribe(new TempSubscription(tempProcessor, town))
        }
    }
}
