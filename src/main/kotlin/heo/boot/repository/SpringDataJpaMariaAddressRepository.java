package heo.boot.repository;

import heo.boot.domain.Address;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMariaAddressRepository extends JpaRepository<Address, Long> {
  List<Address> findByName(String name); //테이블 컬럼에 의존적인 select는 직접 만들어줘야함.
  //JPQL -> select a from Address a where a.name = :name
  List<Address> findByNameAndAddr(String name, String addr); //where name='가' and addr='나' And, Or,...
  List<Address> findByNameContaining(String name); //XXXContaining()은 like 연산자 역할
  Page<Address> findByOrderBySeqDesc(Pageable pageable);
}














