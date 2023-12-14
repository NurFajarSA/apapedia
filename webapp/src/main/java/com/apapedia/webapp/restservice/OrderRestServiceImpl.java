package com.apapedia.webapp.restservice;

import com.apapedia.webapp.dto.response.ChartDTO;
import com.apapedia.webapp.dto.response.ListChartDTO;
import com.apapedia.webapp.model.Order;
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
        Mono<ListChartDTO> orderMono = WebClient.create()
                .get()
                .uri("http://103.41.205.41:10104/api/order/orderItem/top5-sold-this-month")
                //.header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                .retrieve()
                .bodyToMono(ListChartDTO.class);
        var response = orderMono.block();
        var list = response.getChartDTOList();
//        var response = orderMono.collectList().block();
        //var response = orderMono.collectList().block();
        //var lis

        Map<String, Long> result = new HashMap<>();
        list.forEach(a ->{
            var nama = a.getProductName();
            var jumlah = a.getQuantity();
            result.put(nama, jumlah);
        });


        return result;
    }
//    @Override
//    public Map<String, Long> chart() {
//
//        ListChartDTO listChartDTO = webClient.get()
//                .uri("http://103.41.205.41:10104/api/order/orderItem/top5-sold-this-month")
//                .retrieve()
//                .bodyToMono(ListChartDTO.class)
//                .block();
//
//        if (listChartDTO != null) {
//        // Ambil Map<String, Long> dari respons
//            Map<String, Long> chartData = listChartDTO.getListChart();
//
//        // Lakukan pengolahan data untuk mendapatkan top 5
//            Map<String, Long> top5Sales = chartData.entrySet().stream()
//                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                    .limit(5)
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//            return top5Sales;
//        } else {
//            // Handle jika respons null atau kosong
//            return Collections.emptyMap();
//    }
//}





}