package br.com.enterprise.orders.client;

import br.com.enterprise.orders.config.FeignConfig;
import br.com.enterprise.orders.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "externalServiceB", url = "${client.external.service-b.url}", configuration = FeignConfig.class)
public interface ExternalServiceBClient {

    @RequestMapping(method = RequestMethod.POST, value = "/order")
    void postData(@RequestBody Order order);
}
