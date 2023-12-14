package com.apapedia.webapp.restservice;

import com.apapedia.webapp.dto.response.ChartDTO;
import com.apapedia.webapp.dto.response.ListChartDTO;
import com.apapedia.webapp.model.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderRestServiceImpl implements OrderResrService {



    @Override
    public Map<String,Long> chart(){
        Mono<Map<String,Long>> orderMono = WebClient.create()
                .get()
                .uri("http://103.41.205.41:10104/api/order/orderItem/top5-sold-this-month")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Long>>() {});

        var response = orderMono.block();
        return response;
    }



}