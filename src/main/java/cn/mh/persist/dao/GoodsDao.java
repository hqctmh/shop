package cn.mh.persist.dao;


import cn.mh.entity.Goods;
import cn.mh.persist.example.GoodsExample;
import cn.mh.persist.example.GoodsExample.Criteria;
import cn.mh.persist.mapper.GoodsMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class GoodsDao {
    @Autowired
    private GoodsMapper goodsMapper;

    public boolean addGoods(Goods goods){
        return goodsMapper.insert(goods)>0;
    }
    public boolean updateGoods(Goods goods){
        return goodsMapper.updateByPrimaryKeySelective(goods)>0;
    }
    public Goods findGoodsById(String id){
        return goodsMapper.selectByPrimaryKey(id);
    }
    public boolean delGoods(String id){
        return goodsMapper.deleteByPrimaryKey(id)>0;
    }

    public List<Goods> findGoodsByIds(Set<String> set){
        GoodsExample example=new GoodsExample();
        Criteria criteria=example.createCriteria();
        List<String> ids=new ArrayList<>(set);
        criteria.andIdIn(ids);
        List<Goods> list=goodsMapper.selectByExample(example);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }



    public List<Goods> findGoodsByLimit(String iid,String subId,String storeId,String name,String delFlag){
        GoodsExample example=new GoodsExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,iid, subId, storeId, name, delFlag);
        List<Goods> list=goodsMapper.selectByExample(example);
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public Long getGoodsCount(String iid,String subId,String storeId,String name,String delFlag){
        GoodsExample example=new GoodsExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,iid, subId, storeId, name,delFlag);
        return goodsMapper.countByExample(example);
    }

    public List<Goods> findGoodsForPage(int pageNum,int pageSize,String iid,String subId,String storeId,String name,String delFlag){
        GoodsExample example=new GoodsExample();
        Criteria criteria=example.createCriteria();
        staticSet(criteria,iid, subId, storeId, name,delFlag);
        example.setPaging(true);
        example.setRowIndex(pageNum);
        example.setPageSize(pageSize);
        example.setOrderByClause("create_time desc");
        List<Goods> list=goodsMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }



    private static void  staticSet(Criteria criteria,String iid,String subId,String storeId,String name,String delFlag){
        if(StringUtil.isNotBlank(iid)){
            criteria.andItemIdEqualTo(iid);

        }
        if(StringUtil.isNotBlank(subId)){
            criteria.andSubitemIdEqualTo(subId);
        }
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(name)){
            criteria.andGoodsNameLike("%"+name+"%");
        }
        if(StringUtil.isNotBlank(delFlag)){
            criteria.andDelFlagEqualTo(delFlag);
        }
    }

}
