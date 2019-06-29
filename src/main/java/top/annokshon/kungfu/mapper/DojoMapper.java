package top.annokshon.kungfu.mapper;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.annokshon.kungfu.entity.Dojo;

public interface DojoMapper extends JpaRepository<Dojo, Integer> {
	@Override
	List<Dojo> findAll();

	@Override
	List<Dojo> findAll(Sort sort);

	@Override
	<S extends Dojo> S save(S s);

	@Override
	Optional<Dojo> findById(Integer integer);

	@Override
	boolean existsById(Integer integer);

	@Override
	void deleteById(Integer integer);

	@Override
	void delete(Dojo dojo);
}
