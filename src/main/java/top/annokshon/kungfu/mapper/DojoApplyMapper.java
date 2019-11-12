package top.annokshon.kungfu.mapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import top.annokshon.kungfu.entity.DojoApply;

import java.util.List;
import java.util.Optional;

public interface DojoApplyMapper extends JpaRepository<DojoApply,Integer> {
    @Override
    List<DojoApply> findAll();

    @Override
    List<DojoApply> findAll(Sort sort);

    @Override
    <S extends DojoApply> S save(S s);

    @Override
    Optional<DojoApply> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(DojoApply apply);
}
