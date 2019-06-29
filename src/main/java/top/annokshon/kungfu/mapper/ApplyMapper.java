package top.annokshon.kungfu.mapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.annokshon.kungfu.entity.Apply;

import java.util.List;
import java.util.Optional;

public interface ApplyMapper extends JpaRepository<Apply,Integer> {
    @Override
    List<Apply> findAll();

    @Override
    List<Apply> findAll(Sort sort);

    @Override
    <S extends Apply> S save(S s);

    @Override
    Optional<Apply> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Apply apply);
}
