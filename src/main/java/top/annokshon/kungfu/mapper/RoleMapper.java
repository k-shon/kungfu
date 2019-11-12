package top.annokshon.kungfu.mapper;

import org.springframework.stereotype.Repository;
import top.annokshon.kungfu.entity.Role;

import java.util.Map;

/**
 * @author kshon
 * @description
 * @date 2019-10-10 23:29
 */
@Repository
public interface RoleMapper {
    Role findById(int id);
    void saveToUserRole(Map map);
}
