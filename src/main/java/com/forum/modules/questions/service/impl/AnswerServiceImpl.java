package com.forum.modules.questions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.common.utils.dozer.DozerUtils;
import com.forum.modules.questions.DO.AnswerDO;
import com.forum.modules.questions.DO.UserQuestion;
import com.forum.modules.questions.VO.AnswerVO;
import com.forum.modules.questions.dao.AnswerDao;
import com.forum.modules.questions.dao.QuestionDao;
import com.forum.modules.questions.service.AnswerService;
import com.forum.modules.questions.service.QuestionService;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerDao,AnswerDO> implements AnswerService {

    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @Override
    public Map<String,Object> getList(Integer questionId) {
        List<AnswerDO> answerDOS = answerDao.selectList(new QueryWrapper<AnswerDO>().
                eq("flag", 0).
                eq("querstion_id", questionId).
                eq("comment_id", 0));
        List<AnswerVO> answerVOList = new ArrayList<>(); // 需要返回的数据
        List<AnswerDO> answerDOList = new ArrayList<>();
        for (AnswerDO answerDO : answerDOS) {
            answerDOList = answerDao.selectList(new QueryWrapper<AnswerDO>().
                    eq("flag", 0).
                    eq("comment_id", answerDO.getId()));
            AnswerVO answerVO = DozerUtils.map(answerDO, AnswerVO.class);
            // 获取用户昵称 头像地址
            answerVO.setNickName(userService.getById(answerDO.getCreateBy()).getNickName());
            // 获取此回答的评论内容
            answerVO.setComments(DozerUtils.mapList(answerDOList, AnswerVO.class));
            // 返回数据
            answerVOList.add(answerVO);
        }
        // 获取关注者数量
        int attentionNumber = questionDao.attentionNumber(questionId).size();
        // 获取浏览数量
        Integer volumeNumber = questionService.getById(questionId).getVolumeNumber();
        int answerNumber = answerDao.selectList(new QueryWrapper<AnswerDO>().eq("querstion_id", questionId).eq("flag", 0)).size();
        Map<String,Object> map = new HashMap<>();
        map.put("answers",answerVOList);
        map.put("attentionNumber",attentionNumber); // 关注者数量
        map.put("volumeNumber",volumeNumber); // 浏览数量
        map.put("answerNumber",answerNumber); // 回答数量
        return map;
    }
}