package top.annokshon.kungfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.annokshon.kungfu.entity.DojoApply;
import top.annokshon.kungfu.mapper.DojoApplyMapper;

import java.util.List;

@Service
public class DojoApplyService {
    @Autowired
    private DojoApplyMapper dojoApplyMapper;

    /**
     * 根据id查找报名记录
     */
    public DojoApply findById(int id){
        return dojoApplyMapper.findById(id).get();
    }
    /**
     * 查找所有报名记录并按报名时间排序
     */
    public List<DojoApply> findAll(){
        Sort sort = new Sort(Sort.Direction.DESC,"kf_apply_time");
        return dojoApplyMapper.findAll(sort);
    }
    /**
     * 保存或更新报名记录
     */
    public void saveOrUpdate(DojoApply dojoApply){
        dojoApplyMapper.save(dojoApply);
    }
    /**
     * 删除报名记录
     */
    public void delete(DojoApply dojoApply){
        dojoApplyMapper.delete(dojoApply);
    }
    /**
     * 删除报名记录
     */
    public void deleteById(int id){
        dojoApplyMapper.deleteById(id);
    }
}
