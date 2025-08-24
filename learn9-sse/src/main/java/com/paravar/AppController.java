package com.paravar;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class AppController {


    private final Sinks.Many<String> sink;
    private final ApplicationEventPublisher eventPublisher;

    public AppController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }


    @GetMapping
    public String greet(){
        return "Hello";
    }

    @GetMapping("/home")
    public String home(){
        return "home.html";
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<String> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "SSE Event - " + LocalDateTime.now());
    }

    // without flux
    @GetMapping(value = "/events/emitter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public SseEmitter streamSseEvents() {
        SseEmitter emitter = new SseEmitter();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            try {
                emitter.send("SSE Event - " + LocalDateTime.now());
            } catch (IOException e) {
                emitter.completeWithError(e);
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);

        emitter.onCompletion(executor::shutdown);
        emitter.onTimeout(() -> {
            emitter.complete();
            executor.shutdown();
        });
        emitter.onError((e) -> executor.shutdown());

        return emitter;
    }


    //===== Notifications

    @GetMapping(value = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> notifications() {
        return sink.asFlux();
    }


    @GetMapping("/trigger-event")
    @ResponseBody
    public String triggerNotification() {
        eventPublisher.publishEvent(new NotificationEvent(this, "Message: "+ LocalDateTime.now()));

        return "OK";
    }

    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        sink.tryEmitNext( event.getMessage());
    }

}
