package com.study.stock.service;

import com.study.stock.domain.Stock;
import com.study.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //@Transactional
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow(); // stock 조회
        stock.decreaseQuantity(quantity); // 재고 감소

        stockRepository.saveAndFlush(stock); // 갱신된 값 저장
    }
}
