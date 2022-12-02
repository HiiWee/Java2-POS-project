package com.project.controller;

import com.project.service.SellService;
import com.project.view.sell.DetailTableFrame;
import com.project.view.sell.listener.DetailTableFrameListener;

public class SellController {
    private final SellService sellService = new SellService();
    private final DetailTableFrame detailTableFrame = DetailTableFrame.getInstance();
    private final DetailTableFrameListener detailTableFrameListener = new DetailTableFrameListener();


    /**
     * DB에서 현재 존재하는 메뉴 가져와서 심기
     */
    public void initSellPage() {
        detailTableFrame.initProduct(sellService.findAllProduct());
        detailTableFrameListener.addActionOnDetailTableFrameListener();
    }
}
