package top.annokshon.kungfu.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.annokshon.kungfu.entity.Person;
import top.annokshon.kungfu.entity.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonMapper {

    Person findByUser(@Param("id") int userId);

    int update(Person person);

    List<Person> findAll();

    List<Person> findAll(Sort sort);

    int save(Person person);

    Person findById(Integer id);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void deleteAll();
    void delete(Person person);
}
