package top.annokshon.kungfu.mapper;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.annokshon.kungfu.entity.Dojo;

public interface DojoMapper extends JpaRepository<Dojo, Integer> {
	@Override
	List<Dojo> findAll();

	//根据geoCode的相似度查找距离最近的武馆列表
	@Query(value = "select d from Dojo d  where d.geoCode like concat(?1,'%') ")
	List<Dojo> findByGeoCode(String geo_code);

	//根据价格查找
	List<Dojo>  findByDojoApplyPrice(int price);

	@Override
	List<Dojo> findAll(Sort sort);

	@Override
	<S extends Dojo> S save(S s);

	@Override
	Optional<Dojo> findById(Integer integer);

	@Override
	boolean existsById(Integer integer);

	@Override
	long count();

	@Override
	void deleteById(Integer integer);

	@Override
	void deleteAll();
}
