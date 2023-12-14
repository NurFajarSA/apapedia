package com.apapedia.webapp.restservice;

import com.apapedia.webapp.dto.response.ChartDTO;
import com.apapedia.webapp.model.Order;

import java.util.Map;
import java.util.UUID;

public interface OrderResrService {
    Map<String, Long> chart();
}
