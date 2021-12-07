import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Mono.empty();
        Flux.empty();
        Mono<Integer> mono = Mono.just(1);
        Flux<Integer> flux = Flux.just(1, 2, 3);

        Flux<Integer> fluxFromMono = mono.flux();
        Mono<Boolean> monoFromFlux = flux.any(s -> s.equals(1));
        Mono<Integer> integerMono = flux.elementAt(1);

        System.out.println("Flux.range:");
        Flux.range(1, 5).subscribe(System.out::println);

        System.out.println("Flux.fromIterable:");
        Flux.fromIterable(Arrays.asList(1, 2, 3)).subscribe(System.out::println);

//        Flux.generate(sink -> {
//            sink.next("Hello");
//        }).subscribe(System.out::println); //бксконечный поток//

        System.out.println("\n---Flux.generate:");
        Disposable producer = Flux.generate(sink -> {
                    sink.next("Hello");
                })
                .delayElements(Duration.ofMillis(500))//делает задержки между итерациями,
                // необходимо чтобы продолжал работать главный поток,
                // и наче этот демон поток не успеет выполниться
                .take(5)//ограничение потока
                .subscribe(System.out::println);

        Thread.sleep(10000);

        System.out.println("\n---Flux.create 2:");
        Flux.create(
                sink -> System.out.println("123")

        );
    }
}
