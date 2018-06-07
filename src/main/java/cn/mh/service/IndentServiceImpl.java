package cn.mh.service;

import cn.mh.api.IIndentService;
import cn.mh.entity.*;
import cn.mh.entity.enums.IndentStatusEnum;
import cn.mh.entity.enums.ReturnStatusEnum;
import cn.mh.entity.model.*;
import cn.mh.persist.dao.*;
import cn.mh.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service("IndentService")
@Transactional

public class IndentServiceImpl extends BaseService implements IIndentService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private IndentDao indentDao;
    @Autowired
    private IndentDetailDao indentDetailDao;
    @Autowired
    private StoreIndentDao storeIndentDao;
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private IndentViewDao indentViewDao;
    @Autowired
    private ReturnMainDao returnMainDao;
    @Autowired
    private ReturnDetailDao returnDetailDao;
    @Autowired
    private ReturnViewDao returnViewDao;
    @Autowired
    private MessageDao messageDao;


    @Override
    public ServiceResult addIndent(Map<String, Integer> jsonMap, String userId, String addressId) {
        ServiceResult result = new ServiceResult();
        try {
            if (jsonMap.size() <= 0) {
                result.setError(1, "数据为空");
                return result;
            }
            Set<String> keySet = jsonMap.keySet();
            Map<String, StoreIndentModel> storeIndentModelMap = new HashMap<>();
            Map<String, Goods> goodsMap = new HashMap<>();
            List<StoreIndent> storeIndentList = new ArrayList<>();
            List<IndentDetail> indentDetailList = new ArrayList<>();
            int allAmount = 0;
            BigDecimal allPrice = new BigDecimal(0);
            List<Goods> goodsList = goodsDao.findGoodsByIds(keySet);
            if (goodsList == null || goodsList.size() <= 0) {
                result.setError(1, "商品查询为空");
                return result;
            }
            for (Goods goods : goodsList) {
                goodsMap.put(goods.getId(), goods);
                //计算订单总价
                BigDecimal price = goods.getPrice().multiply(new BigDecimal(jsonMap.get(goods.getId())));
                allPrice = allPrice.add(price);
                //计算每个店铺的订单价格
                StoreIndentModel model = storeIndentModelMap.get(goods.getStoreId());
                if (model == null) {
                    model = new StoreIndentModel();
                    model.setAmount(jsonMap.get(goods.getId()));
                    model.setPrice(price);
                    storeIndentModelMap.put(goods.getStoreId(), model);
                } else {
                    model.setAmount(model.getAmount() + jsonMap.get(goods.getId()));
                    model.setPrice(model.getPrice().add(price));
                }
                //
                IndentDetail indentDetail = new IndentDetail();
                indentDetail.setId(UuidUtils.newid());
                indentDetail.setGoodsId(goods.getId());
                indentDetail.setAmount(jsonMap.get(goods.getId()));
                indentDetail.setPrice(price);
                indentDetailList.add(indentDetail);
                //
            }
            /**
             * 计算订单总数量
             */
            for (Map.Entry<String, Integer> entry : jsonMap.entrySet()) {
                allAmount += entry.getValue();
            }

            /**
             * 添加订单信息
             */
            Indent indent = new Indent();
            indent.setAddressId(addressId);
            indent.setId(UuidUtils.newid());
            indent.setUserId(userId);
            indent.setAmount(allAmount);
            indent.setPrice(allPrice);
            indent.setCreateTime(new Date());
            indentDao.addIndent(indent);

            /**
             * 添加店铺订单信息
             */
            for (Map.Entry<String, StoreIndentModel> entry : storeIndentModelMap.entrySet()) {
                StoreIndent storeIndent = new StoreIndent();
                storeIndent.setId(UuidUtils.newid());
                entry.getValue().setStoreIndentId(storeIndent.getId());
                storeIndent.setStoreId(entry.getKey());
                storeIndent.setAmount(entry.getValue().getAmount());
                storeIndent.setPrice(entry.getValue().getPrice());
                storeIndent.setCreateTime(new Date());
                storeIndent.setUpdateTime(new Date());
                storeIndent.setStatus("0");
                storeIndent.setReturnStatus("0");
                storeIndent.setOrderNum(UuidUtils.newOrderNum());
                storeIndent.setIndentId(indent.getId());
                storeIndentList.add(storeIndent);

                for (IndentDetail indentDetail : indentDetailList) {
                    String goodsId = indentDetail.getGoodsId();
                    String storeId = goodsMap.get(goodsId).getStoreId();
                    if (storeId.equals(entry.getKey())) {
                        indentDetail.setStoreIndentId(storeIndent.getId());
                    }
                    indentDetail.setIndentId(indent.getId());
                }
            }
            storeIndentDao.addStoreIndentBatch(storeIndentList);
            /**
             * 添加订单详情信息
             */
            indentDetailDao.addIndentDetailBatch(indentDetailList);
            /**
             * 删除购物车
             */
            shopCarDao.delShopCarByGoodsId(new ArrayList<>(keySet), userId);

            result.setSucceed();
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult payIndent(List<String> storeIndentIdList, String userId) {
        ServiceResult result = new ServiceResult();
        try {
            List<StoreIndent> storeIndentList = null;
            if (storeIndentIdList == null) {
                Indent indent = indentDao.findLastIndent(userId);
                storeIndentList = storeIndentDao.findStoreIndentbyIndentId(indent.getId());
                for (StoreIndent storeIndent : storeIndentList) {
                    storeIndentIdList = new ArrayList<>();
                    storeIndentIdList.add(storeIndent.getId());
                }
            } else {
                storeIndentList = storeIndentDao.findStoreIndentbyIds(storeIndentIdList);
            }
            List<IndentDetail> detailList = indentDetailDao.findIndentDetailByStoreIndentIds(storeIndentIdList);
            if (detailList == null || detailList.size() == 0) {
                this.rollback();
                return result.setError(2, "订单详情查询为空");
            }
            User user = userDao.findUserById(userId);
            if (user == null) {
                this.rollback();
                return result.setError(4, "用户查询失败");
            }
            BigDecimal allprice = new BigDecimal(0);
            for (StoreIndent storeIndent : storeIndentList) {
                allprice = allprice.add(storeIndent.getPrice());
                storeIndent.setStatus("1");
                storeIndent.setUpdateTime(new Date());
            }
            if (allprice.compareTo(user.getBalance()) > 0) {
                this.rollback();
                return result.setError(5, "用户余额不足");
            }
            user.setBalance(user.getBalance().subtract(allprice));
            userDao.update(user);
            /**
             * 增加店铺余额
             */
            for(StoreIndent storeIndent:storeIndentList){
                Store store=storeDao.findStoreById(storeIndent.getStoreId());
                store.setBalance(store.getBalance().add(storeIndent.getPrice()));
                storeDao.updateStore(store);
            }
            for (IndentDetail indentDetail : detailList) {
                Goods goods = goodsDao.findGoodsById(indentDetail.getGoodsId());
                if (goods == null) {
                    this.rollback();
                    return result.setError(6, "商品查询失败");
                }
                if (indentDetail.getAmount() > goods.getCount()) {
                    this.rollback();
                    return result.setError(7, "库存不足");
                }
                goods.setCount(goods.getCount() - indentDetail.getAmount());
                goodsDao.updateGoods(goods);
                Statistics statistics=statisticsDao.findStatisticsById(indentDetail.getGoodsId());
                statistics.setPrice(statistics.getPrice().add(indentDetail.getPrice()));
                statistics.setAmount(statistics.getAmount()+indentDetail.getAmount());
                statisticsDao.updateStatistics(statistics);
            }
            storeIndentDao.updateBantch(storeIndentList);
            result.setSucceed();

        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findGoodsForIndent(Map<String, Integer> jsonMap) {
        ServiceResult result = new ServiceResult();
        try {
            Set<String> keySet = jsonMap.keySet();
            List<Goods> goodsList = goodsDao.findGoodsByIds(keySet);
            Map<String, List<Goods>> map = new HashMap<>();
            for (Goods goods : goodsList) {
                List<Goods> list = map.get(goods.getStoreId());
                goods.setCount(jsonMap.get(goods.getId()));
                if (list == null || list.size() == 0) {
                    list = new ArrayList<>();
                    list.add(goods);
                    map.put(goods.getStoreId(), list);
                } else {
                    list.add(goods);
                }
            }
            List<String> storeIds = new ArrayList<>(map.keySet());
            List<Store> storeList = storeDao.findStoreByIds(storeIds);
            result.setAttribute("goodsMap", map);
            result.setAttribute("storeList", storeList);
            result.setSucceed();
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findIndentGoodsForPage(int pageNum, int pageSize, String userId, String storeId, String status) {
        ServiceResult result = new ServiceResult();
        try {
            String[] strs = status.split(",");
            List<String> statusList = Arrays.asList(strs);
            int count = indentViewDao.getCount(userId, storeId, "", statusList).intValue();
            if (count > 0) {
                List<IndentView> viewList = indentViewDao.findIndentViewForPage(result.createPage(count, pageNum, pageSize), pageSize, userId, storeId, "", statusList);
                Map<String, Map<String, Object>> map = new HashMap<>();
                for (IndentView view : viewList) {
                    Map<String, Object> indentMap = map.get(view.getStoreIndentId());
                    if (indentMap == null) {
                        indentMap = new HashMap<>();
                        Store store = storeDao.findStoreById(view.getStoreId());
                        indentMap.put("orderNum", view.getOrderNum());
                        indentMap.put("storeName", store.getStoreName());
                        indentMap.put("status", view.getStatus());
                        indentMap.put("createTime", view.getCreateTime());
                        List<DoModel> doModelList = new ArrayList<>();
                        DoModel doModel = new DoModel();
                        if (StringUtil.isNotBlank(userId)) {
                            switch (view.getStatus()) {
                                case "0":
                                    doModel.setName("付款");
                                    doModel.setUrl("payByBtnPro?status=0");
                                    doModelList.add(doModel);
                                    doModel = new DoModel();
                                    doModel.setName("取消订单");
                                    doModel.setUrl("updateStatus?status=4");
                                    doModelList.add(doModel);
                                    break;
                                case "2":
                                    doModel.setName("确认收货");
                                    doModel.setUrl("updateStatus?status=3");
                                    doModelList.add(doModel);
                                    doModel = new DoModel();
                                    doModel.setName("申请退货");
                                    doModel.setUrl("updateStatus?status=5");
                                    doModelList.add(doModel);
                                    break;
                                default:
                                    doModelList = null;
                                    break;
                            }
                        } else {
                            switch (view.getStatus()) {
                                case "1":
                                    doModel.setName("发货");
                                    doModel.setUrl("updateStatus?status=2");
                                    doModelList.add(doModel);
                                    break;
                                default:
                                    doModelList = null;
                                    break;
                            }
                        }
                        indentMap.put("do", doModelList);
                        List<IndentGoodsModel> goodsModelList = new ArrayList<>();
                        indentMap.put("goodsModelList", goodsModelList);
                        map.put(view.getStoreIndentId(), indentMap);
                    }
                    List<IndentGoodsModel> goodsList = (List<IndentGoodsModel>) indentMap.get("goodsModelList");
                    Goods goods = goodsDao.findGoodsById(view.getGoodsId());
                    IndentGoodsModel model = new IndentGoodsModel();
                    model.setGoodsId(view.getGoodsId());
                    model.setGoodsName(goods.getGoodsName());
                    model.setPrice(goods.getPrice());
                    model.setPic(goods.getPic());
                    model.setDetailAmount(view.getDetailAmount());
                    model.setCreateTime(DateUtil.convertToString(view.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    goodsList.add(model);
                }
                List<OrderModel> orderModelList = new ArrayList<>();
                for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                    OrderModel orderModel = new OrderModel();
                    orderModel.setCreateTime((Date) entry.getValue().get("createTime"));
                    orderModel.setOrderNum((String) entry.getValue().get("orderNum"));
                    orderModel.setStoreName((String) entry.getValue().get("storeName"));
                    orderModel.setStatusName(IndentStatusEnum.getName((String) entry.getValue().get("status")));
                    orderModel.setStatusValue((String) entry.getValue().get("status"));
                    orderModel.setModelList((List) entry.getValue().get("goodsModelList"));
                    orderModel.setStoreIndentId(entry.getKey());
                    orderModel.setDoModelList((List) entry.getValue().get("do"));
                    orderModelList.add(orderModel);
                }
//                Collections.sort(orderModelList, new Comparator<OrderModel>() {
//                    @Override
//                    public int compare(OrderModel o1, OrderModel o2) {
//                        return -(o1.getCreateTime().compareTo(o2.getCreateTime()));
//                    }
//                });
                //Collections.sort(orderModelList,(OrderModel o1,OrderModel o2)->o2.getCreateTime()-o1.getCreateTime());
                orderModelList.sort((OrderModel o1, OrderModel o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
                result.setAttribute("result", orderModelList);
                result.setSucceed();
            }
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult updateStoreIndent(String storeIndentId, String status) {
        ServiceResult result = new ServiceResult();
        try {
            StoreIndent storeIndent = new StoreIndent();
            storeIndent.setId(storeIndentId);
            storeIndent.setStatus(status);
            storeIndentDao.updateStoreIndent(storeIndent);
            result.setSucceed();
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findStoreIndentById(String storeIndentId) {
        ServiceResult result = new ServiceResult();
        try {
            StoreIndent storeIndent = storeIndentDao.findStoreIndentById(storeIndentId);
            if (storeIndent != null) {
                result.setAttribute("result", storeIndent);
                result.setSucceed();
            }
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult addReturnNote(String storeIndentId) {
        ServiceResult result = new ServiceResult();
        try {
            StoreIndent storeIndent = storeIndentDao.findStoreIndentById(storeIndentId);
            List<IndentDetail> list = indentDetailDao.findIndentDetailByLimit("", storeIndentId);
            ReturnMain returnMain = new ReturnMain();
            returnMain.setId(UuidUtils.newid());
            returnMain.setIndentId(storeIndent.getIndentId());
            returnMain.setStoreIndentId(storeIndentId);
            returnMain.setCreateTime(new Date());
            returnMain.setUpdateTime(new Date());
            returnMain.setStatus("0");
            List<ReturnDetail> returnDetailList = new ArrayList<>();
            for (IndentDetail detail : list) {
                ReturnDetail returnDetail = new ReturnDetail();
                returnDetail.setId(UuidUtils.newid());
                returnDetail.setMainId(returnMain.getId());
                returnDetail.setGoodsId(detail.getGoodsId());
                returnDetail.setAccount(detail.getAmount());
                returnDetailList.add(returnDetail);
            }
            returnMainDao.addReturnMain(returnMain);
            returnDetailDao.addBatch(returnDetailList);
            result.setSucceed();
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult findReturnDetailForPage(int pageNum, int pageSize, String storeIndentId, String storeId, String userId, String status) {
        ServiceResult result = new ServiceResult();
        try {
            int count = returnViewDao.countByLimit(storeIndentId, storeId, userId, status).intValue();
            if (count > 0) {
                List<ReturnView> viewList = returnViewDao.findReturnViewForPage(result.createPage(count, pageNum, pageSize), pageSize, storeIndentId, storeId, userId, status);
                Map<String, ReturnModel> map = new HashMap<>();
                for (ReturnView view : viewList) {
                    ReturnModel returnModel = map.get(view.getMainId());
                    if (returnModel == null) {
                        returnModel = new ReturnModel();
                        returnModel.setReturnMainId(view.getMainId());
                        returnModel.setStoreIndentId(view.getIndentId());
                        returnModel.setStatusName(ReturnStatusEnum.getName(view.getStatus()));
                        returnModel.setStatusValue(view.getStatus());
                        returnModel.setOrderNum(view.getOrderNum());
                        returnModel.setPrice(view.getPrice());
                        returnModel.setCreateTime(view.getCreateTime());
                        returnModel.setCreateTimeStr(DateUtil.convertToString(view.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                        Store store=storeDao.findStoreById(view.getStoreId());
                        returnModel.setStoreName(store.getStoreName());
                        List<DoModel> doModelList = new ArrayList<>();
                        DoModel domodel = new DoModel();
                        if (StringUtil.isNotBlank(userId)) {//买家查询
                            switch (view.getStatus()) {
                                case "1":
                                    domodel.setName("发货");
                                    domodel.setUrl("updateReturnStatus?status=3");
                                    doModelList.add(domodel);
                                    break;
                                default:
                                    doModelList = null;
                            }
                        } else {//店铺查询
                            switch (view.getStatus()) {
                                case "0":
                                    domodel.setName("同意");
                                    domodel.setUrl("updateReturnStatus?status=1");
                                    doModelList.add(domodel);
                                    domodel=new DoModel();
                                    domodel.setName("拒绝");
                                    domodel.setUrl("updateReturnStatus?status=2");
                                    doModelList.add(domodel);
                                    break;
                                case "3":
                                    domodel.setName("确认收货");
                                    domodel.setUrl("updateReturnStatus?status=4");
                                    doModelList.add(domodel);
                                    break;
                                default:
                                    doModelList = null;
                            }
                        }
                        returnModel.setDoModelList(doModelList);
                        returnModel.setGoodsList(new ArrayList<Goods>());
                        map.put(returnModel.getReturnMainId(),returnModel);
                    }
                    Goods goods = goodsDao.findGoodsById(view.getGoodsId());
                    goods.setCount(view.getAccount());
                    returnModel.getGoodsList().add(goods);
                }
                List<ReturnModel> returnModelList = new ArrayList<>();
                for (Map.Entry<String, ReturnModel> entry : map.entrySet()) {
                    returnModelList.add(entry.getValue());
                }
                returnModelList.sort((ReturnModel o1, ReturnModel o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
                result.setAttribute("result", returnModelList);
                result.setSucceed();
            }
        } catch (Exception e) {
            result.setException(e);
            this.rollback();
        }
        return result;

    }

    @Override
    public ServiceResult updateReturnMain(String mainId, String status) {
        ServiceResult result=new ServiceResult();
        try{
            ReturnMain returnMain=new ReturnMain();
            returnMain.setId(mainId);
            returnMain.setStatus(status);
            returnMainDao.updateReturnMain(returnMain);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult returnGoods(String mainId) {
        ServiceResult result=new ServiceResult();
        try{
            List<ReturnView> list=returnViewDao.findReturnViewByMainId(mainId);
            if(list!=null&&list.size()>0){
                User user=userDao.findUserById(list.get(0).getUserId());
                user.setBalance(user.getBalance().add(list.get(0).getPrice()));
                Store store=storeDao.findStoreById(list.get(0).getStoreId());
                store.setBalance(store.getBalance().subtract(list.get(0).getPrice()));
                userDao.update(user);
                storeDao.updateStore(store);
                for(ReturnView view:list){
                    Goods goods=goodsDao.findGoodsById(view.getGoodsId());
                    goods.setCount(goods.getCount()+view.getAccount());
                    goodsDao.updateGoods(goods);
                }
                result.setSucceed();
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult statisticsSell(String storeId) {
        ServiceResult result=new ServiceResult();
        try{
            List<Date> list=DateUtil.getDayListFromOneWeek(new Date());
            List<BigDecimal> priceList=new ArrayList<>();
            for(Date date:list){
                BigDecimal price=storeIndentDao.sumPrice(date,storeId);
                if(price==null){
                    price=new BigDecimal(0);
                }
                priceList.add(price);
            }
            result.setAttribute("result",priceList);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;

    }

    @Override
    public ServiceResult statisticsSellGoods(String storeId) {
        ServiceResult result=new ServiceResult();
        try{
            List<Statistics> list=statisticsDao.findAllStatistics(storeId);
            List<StatisticsModel> modelList=new ArrayList<>();
            BigDecimal price=new BigDecimal(0);
            StatisticsModel model=null;
            for(int i=0;i<list.size();i++){
                if(i<5){
                    Goods goods=goodsDao.findGoodsById(list.get(i).getId());
                    model=new StatisticsModel();
                    model.setName(goods.getGoodsName());
                    model.setPrice(list.get(i).getPrice());
                    modelList.add(model);
                }else{
                    price=price.add(list.get(i).getPrice());
                }
            }
            model=new StatisticsModel();
            model.setName("其他");
            model.setPrice(price);
            modelList.add(model);
            result.setAttribute("result",modelList);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    @Override
    public ServiceResult message(String goodsId, String userId,String msg) {
        ServiceResult result=new ServiceResult();
        try{
            Goods goods=goodsDao.findGoodsById(goodsId);
            Message message=new Message();
            message.setId(UuidUtils.newid());
            message.setStoreId(goods.getStoreId());
            message.setUserId(userId);
            message.setGoodsId(goodsId);
            message.setContent(msg);
            message.setStatus("0");
            messageDao.addMessage(message);
            result.setSucceed();
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;

    }

    @Override
    public ServiceResult myMessage(int pageNum,int pageSize,String storeId) {
        ServiceResult result=new ServiceResult();
        try{
            int count=messageDao.count(storeId,"0","").intValue();
            if(count>0) {
                List<Message> list=messageDao.findMessageForPage(result.createPage(count,pageNum, pageSize),pageSize,storeId, "0","");
                if(list!=null&&list.size()>0){
                    List<MessageModel> modelList=new ArrayList<>();
                    for(Message message:list){
                        MessageModel model=new MessageModel();
                        model.setId(message.getId());
                        model.setContent(message.getContent());
                        Goods goods=goodsDao.findGoodsById(message.getGoodsId());
                        model.setGoodsName(goods.getGoodsName());
                        User user=userDao.findUserById(message.getUserId());
                        model.setUserName(user.getName());
                        modelList.add(model);
                    }
                    result.setAttribute("result",modelList);
                    result.setSucceed();
                }
            }
        }catch (Exception e){
            result.setException(e);
            this.rollback();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
