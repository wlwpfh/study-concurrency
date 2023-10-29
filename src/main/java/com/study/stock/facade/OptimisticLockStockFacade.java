package com.study.stock.facade;

import com.study.stock.service.OptimisticLockService;
import org.springframework.stereotype.Component;

@Component
public class OptimisticLockStockFacade {
    private final OptimisticLockService service;

    public OptimisticLockStockFacade(OptimisticLockService service){
        this.service = service;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        /* 업데이트 실패 시 재시도 */
        while(true){
            try{
                service.decrease(id, quantity);
                break;
            } catch (Exception e){
                Thread.sleep(50);
            }
        }
    }

}
