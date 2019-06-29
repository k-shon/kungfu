package top.annokshon.kungfu.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.annokshon.kungfu.entity.User;

public interface UserMapper extends JpaRepository<User,Integer> {
	@Override
	List<User> findAll();

	@Override
	List<User> findAll(Sort sort);

	@Override
	<S extends User> S save(S s);

	@Override
	Optional<User> findById(Integer integer);

	@Override
	boolean existsById(Integer integer);

	@Override
	void deleteById(Integer integer);

	@Override
	void delete(User user);
}
