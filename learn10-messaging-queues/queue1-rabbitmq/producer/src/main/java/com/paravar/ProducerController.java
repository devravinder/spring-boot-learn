package com.paravar;

import com.paravar.basic.BasicProducerService;
import com.paravar.direct.DirectService;
import com.paravar.fanout.FanOutService;
import com.paravar.rpc.RpcService;
import com.paravar.topic.TopicService;
import com.paravar.worker.WorkerProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final BasicProducerService basicProducerService;
    private final WorkerProducerService workerProducerService;
    private final FanOutService fanOutService;
    private final DirectService directService;
    private final TopicService topicService;
    private final RpcService rpcService;


    @GetMapping("/basic")
    public String basicMessage() {
        basicProducerService.sendMessage("Hello");
        return "Message sent to text-queue";
    }

    @GetMapping("/work")
    public String work() {
        workerProducerService.sendMessage("Work Item");
        return "Work Item sent";
    }

    @GetMapping("/fan-out")
    public String fanOut() {
        fanOutService.sendFanOutMessage("Fan out message");
        return "Fan out message";
    }

    @GetMapping("/direct/{key}")
    // key = info / error
    public String direct(@PathVariable String key) {
        directService.sendDirectMessage("Direct message", key);
        return "Sent to direct-exchange with key " + key;
    }

    @GetMapping("/topic/{key}")
    /*
      logs.info -> logs queue
      logs.debug -> logs queue
      logs.trace -> logs queue
      logs.error -> error queue
     */
    public String sendTopic(@PathVariable String key) {
        topicService.sendTopicMessage("Topic message", key);
        return "Sent to topic-exchange with key " + key;
    }

    @GetMapping("/rpc")
    public String sendRpc() {
        return "Reply from consumer: " + rpcService.sendRpcMessage("Rpc message");
    }

}