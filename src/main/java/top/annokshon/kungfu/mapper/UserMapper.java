package top.annokshon.kungfu.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.annokshon.kungfu.entity.User;
@Repository
public interface UserMapper {

	User findByUsername(String name);

	List<User> findAll();

	List<User> findAll(Sort sort);

	//保存用户信息并返回id
	int save(User user);

	Optional<User> findById(Integer integer);

	boolean existsById(Integer integer);

	void deleteById(Integer integer);

	void delete(User user);
}
