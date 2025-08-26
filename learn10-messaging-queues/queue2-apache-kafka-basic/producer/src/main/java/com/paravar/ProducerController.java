package com.paravar;

import com.paravar.basic.BasicService;
import com.paravar.errorHandling.ErrorService;
import com.paravar.key.KeyService;
import com.paravar.partition.PartitionService;
import com.paravar.streams.StreamsService;
import com.paravar.transactional.TransactionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final BasicService basicService;
    private final PartitionService partitionService;
    private final KeyService keyService;
    private final ErrorService errorService;
    private final StreamsService streamsService;
    private final TransactionalService transactionalService;

    @GetMapping("/basic")
    public String sendSimple() {
        basicService.sendBasicMessage("Hello");
        return "Message sent";
    }
    @GetMapping("/partition/{partition}")
    // partition: 0, 1 , 2, 3 ( starts from: 0 ) if partition number shouldn't be greater than partions
    public String sendPartitioned( @PathVariable Integer partition) {
        partitionService.sendPartitionedMessage("How are you?", partition);
        return "Message sent to partitioned-topic, partition " + partition;
    }


    @GetMapping("/key/{key}")
    // key: user1, user2, user3... keys can be dynamic & any number of keys are allowed ( more than partitions )
    public String sendKeyed(@PathVariable String key) {
        keyService.sendKeyedMessage(key, "I'm Good");
        return "Message sent to keyed-topic with key " + key;
    }

    @GetMapping("/error-message")
    public String sendError() {
        String[] arr = {"fail", "success"};
        int index = (int)(Math.round(Math.random()));
        errorService.sendErrorMessage("I'm on the way "+arr[index]);
        return "Message sent to error topic "+arr[index];
    }

    @GetMapping("/streams")
    public String streams() {
        streamsService.sendStream("Hi, hello, How are you?");
        return "Message sent streams ";
    }

    @GetMapping("/transactional")
    public String transactional() {
        transactionalService.sendTransactional("Hi, hello, How are you?");
        transactionalService.sendTransactional("group-key","Hi, hello, How are you?");
        return "Transactions sent streams ";
    }
}