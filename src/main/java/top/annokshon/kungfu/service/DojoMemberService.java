package top.annokshon.kungfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.annokshon.kungfu.entity.DojoMember;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.mapper.DojoMemberMapper;
import top.annokshon.kungfu.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kshon
 * @description 武馆成员服务
 * @date 2019-08-21 21:28
 */
@Service
public class DojoMemberService {
    @Autowired
    private DojoMemberMapper dojoMemberMapper;
    @Autowired
    private ObjectUtils objectUtils;
    /**
     * 根据id查找武馆成员
     */
    public DojoMember findById(int id){
        return dojoMemberMapper.findById(id).get();
    }
    /**
     * 查找所有武馆成员并按入馆排序
     */
    public List<DojoMember> findAll(){
        List<String> properties = new ArrayList<String>();
        properties.add("inTime");
        Sort sort = new Sort(Sort.Direction.DESC,properties);
        return dojoMemberMapper.findAll(sort);
    }
    /**
     * 保存或更新武馆成员
     */
    public void saveOrUpdate(DojoMember dojoMember) throws Exception {
        if(dojoMember.getId()==0 || dojoMember.getPerson() == null) {
            throw new Exception("成员id为空");
        }else{
            DojoMember oldDojoMember = dojoMemberMapper.findById(dojoMember.getId()).get();
            dojoMember = (DojoMember) objectUtils.copyByUnModify(dojoMember,oldDojoMember, new Person());
        }
        dojoMemberMapper.save(dojoMember);
    }
    /**
     * 删除武馆成员
     */
    public void delete(DojoMember dojoMember){
        dojoMemberMapper.delete(dojoMember);
    }
    /**
     * 删除武馆成员
     */
    public void deleteById(int id){
        dojoMemberMapper.deleteById(id);
    }
}
