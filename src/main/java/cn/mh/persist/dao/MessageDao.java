package cn.mh.persist.dao;


import cn.mh.entity.Message;
import cn.mh.persist.example.MessageExample;
import cn.mh.persist.mapper.MessageMapper;
import cn.mh.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDao {
    @Autowired
    private MessageMapper messageMapper;

    public boolean addMessage(Message message){
        return messageMapper.insert(message)>0;
    }
    public boolean updateMessage(Message message){
        return messageMapper.updateByPrimaryKeySelective(message)>0;
    }
    public Message findMessageById(String id){
        return messageMapper.selectByPrimaryKey(id);
    }
    public boolean delMessage(String id){
        return messageMapper.deleteByPrimaryKey(id)>0;
    }

    public Long count(String storeId,String status,String goodsId){
        MessageExample example=new MessageExample();
        MessageExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        if(StringUtil.isNotBlank(goodsId)){
            criteria.andGoodsIdEqualTo(goodsId);
        }
        return messageMapper.countByExample(example);
    }

    public List<Message> findMessageByLimit(String storeId,String status,String goodsId){
        MessageExample example=new MessageExample();
        MessageExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        if(StringUtil.isNotBlank(goodsId)){
            criteria.andGoodsIdEqualTo(goodsId);
        }
        List<Message> list=messageMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    public List<Message> findMessageForPage(int pageNum, int pageSize, String storeId, String status, String goodsId){
        MessageExample example=new MessageExample();
        MessageExample.Criteria criteria=example.createCriteria();
        if(StringUtil.isNotBlank(storeId)){
            criteria.andStoreIdEqualTo(storeId);
        }
        if(StringUtil.isNotBlank(status)){
            criteria.andStatusEqualTo(status);
        }
        if(StringUtil.isNotBlank(goodsId)){
            criteria.andGoodsIdEqualTo(goodsId);
        }
        example.setPaging(true);
        example.setRowIndex(pageNum);
        example.setPageSize(pageSize);

        List<Message> list=messageMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
    


}
