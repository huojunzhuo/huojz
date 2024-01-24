package com.meituan.themehuojz;

import com.meituan.themehuojz.po.Hotel;
import com.meituan.themehuojz.po.Linkman;
import com.meituan.themehuojz.handler.NumberHandler;
import com.meituan.themehuojz.service.impl.AService;
import com.meituan.themehuojz.service.impl.BService;
import com.meituan.themehuojz.service.impl.CService;

import java.util.Arrays;
import java.util.List;

/**
 * Description: 题目执行客户端
 * @Author huojz
 * @Create 2024/1/22 10:05
 * @Version 1.0
 */
public class Client {

    /**
     * 1、题目：编写一个Java函数，实现批量获取数据的功能（BService.get(List<Integer> ids)）。具体要求如下：
     * 1)提供一个函数BService.get(List<Integer> ids)，支持最多传入100个id；
     * 2)在BService.get((List<Integer> ids)函数内部，需要将传入的id列表分批（每批10个id）进行调用AService.get(List<Integer> ids)函数获取数据；
     * 3)BService.get((List<Integer> ids)函数需要返回所有批次获取的数据的合并结果，即一个包含所有数据的List<Integer>；
     *
     * 2、根据所提供的方法，完成题目：批量根据供应商ID（partnerID）获取供应商下所关联的酒店名称（poiName）以及酒店的联系人姓名（contactName）;
     * 方法1）AService.getHotelByParnterId(List<Long> partnerId),根据供应商ID，返回关联的poiId集合;
     * 方法2）BService.batchGetHotelByPoiId(List<Long> poiIds),批量根据酒店id集合，返回酒店信息集合，酒店信息包含酒店ID，酒店名称；
     * 方法3）CService.batchGetContactBypoiIds(List<Long> poiIds),批量根据酒店id集合，返回酒店下联系人信息集合，酒店联系人信息包含酒店ID(poiID),联系人名称（contactName）;
     * 小贴士：同一个酒店联系人若有多个，任意取一个；默认一次查询可返回数据，无需考虑分批查询；方法中可能因为某些数据问题，导致数据缺失，结合日常工作场景，完整考虑代码健壮性；
     *
     * 3、编写一个Java函数，通过调用AService.get()、BService.get()、CService.get()三个接口，获取三个整数，然后将这三个整数累加，最终返回累加的值。要求：
     * 小贴士：调用三个接口的操作需要并行执行，以提高效率；累加操作需要在获取三个整数的操作完成后进行，因此需要保证三个整数均已获取后才能进行累加操作；考虑多线程安全问题。
     * 客户端方法
     * @param args
     */
    public static void main(String[] args) {
        NumberHandler numberHandler = new NumberHandler();
        AService aService = new AService(numberHandler);
        BService bService = new BService(numberHandler);
        CService cService = new CService(numberHandler);

        //题目1执行***************************************************************************
        List<Integer> param = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
        BService.get(param);

        //题目2执行***************************************************************************
        //题目2存在部分伪代码，未设置database不要直接run
        List<Long> partnerIds = Arrays.asList(1L, 2L, 3L);
        List<Long> poIds = aService.getHotelByParnterId(partnerIds);
        List<Hotel> hotels = bService.batchGetHotelByPoiId(poIds);
        List<Linkman> linkmen = cService.batchGetContactBypoiIds(poIds);

        //题目3执行***************************************************************************
        Thread threadA = new Thread(new AService(numberHandler), "AServiceThread");
        threadA.start();
        Thread threadB = new Thread(new BService(numberHandler), "BServiceThread");
        threadB.start();
        Thread threadC = new Thread(new CService(numberHandler), "CServiceThread");
        threadC.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        numberHandler.sumValue();
        Integer sum = numberHandler.getSum();
        System.out.println("sum = " + sum);

    }
}
