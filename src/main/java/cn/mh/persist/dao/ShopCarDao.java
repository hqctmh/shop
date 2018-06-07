package cn.mh.persist.dao;


import cn.mh.entity.ShopCar;
import cn.mh.persist.example.ShopCarExample;
import cn.mh.persist.example.ShopCarExample.Criteria;
import cn.mh.persist.mapper.ShopCarMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopCarDao {
    @Autowired
    private ShopCarMapper shopCarMapper;

    public boolean addShopCar(ShopCar shopCar){
        return shopCarMapper.insert(shopCar)>0;
    }
    public boolean updateShopCar(ShopCar shopCar){
        return shopCarMapper.updateByPrimaryKeySelective(shopCar)>0;
    }
    public ShopCar findShopCarById(String id){
        return shopCarMapper.selectByPrimaryKey(id);
    }
    public boolean delShopCar(String id){
        return shopCarMapper.deleteByPrimaryKey(id)>0;
    }

    public boolean delShopCarByGoodsId(List<String> GoodsIdList,String userId){
        ShopCarExample example=new ShopCarExample();
        Criteria criteria=example.createCriteria();
        criteria.andGoodsIdIn(GoodsIdList);
        criteria.andUserIdEqualTo(userId);
        return shopCarMapper.deleteByExample(example)>0;
    }

    public List<ShopCar> findShopCarByLimit(String userId,String storeId,String goodsId){
        ShopCarExample example=new ShopCarExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,userId, storeId,goodsId);
        List<ShopCar> list=shopCarMapper.selectByExample(example);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }




    private static void  staticSet(Criteria criteria,String userId,String storeId,String goodsId){
        if(StringUtil.isNotBlank(userId)){
            criteria.andUserIdEqualTo(userId);
        }
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(goodsId)){
            criteria.andGoodsIdEqualTo(goodsId);
        }
    }

}
