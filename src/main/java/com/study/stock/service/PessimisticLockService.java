package com.study.stock.service;

import com.study.stock.domain.Stock;
import com.study.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockService {
    private final StockRepository stockRepository;

    public PessimisticLockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);

        stock.decreaseQuantity(quantity);

        stockRepository.save(stock);
    }
}
