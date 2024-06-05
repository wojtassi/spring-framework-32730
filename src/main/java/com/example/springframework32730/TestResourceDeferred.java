package com.example.springframework32730;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController()
@RequestMapping("/noleak")
public class TestResourceDeferred {

    private final static Logger LOG = LoggerFactory.getLogger(TestResourceDeferred.class);

    @GetMapping("/async-deferredresult")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult(Model model) {
        LOG.info("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() -> {
            LOG.info("Processing in separate thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });
        LOG.info("servlet thread freed");
        return output;
    }
    @GetMapping("/marketData")
    public String getMarketData() {
        return "foobar";
    }
}