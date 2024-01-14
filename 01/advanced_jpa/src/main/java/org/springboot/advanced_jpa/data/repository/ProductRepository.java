package org.springboot.advanced_jpa.data.repository;

import org.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // find...by
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    // exists...By
    boolean existsByNumber(Long number);

    // count...By
    long countByName(String name);

    // delete...By, remove...By
    void deleteByNumber(Long number);
    long removeByName(String name);

    // ...First<number>..., ...Top<number>...
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    // is, Equals (findByNumber 메서드와 같은 동작)
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    // Not (is 생략 가능)
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    // Null, NotNull (is 생략 가능)
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNotNull();
    List<Product> findByUpdatedAtNotNull();

//    // True, False (is 생략 가능) // 엔티티에 불리안값 추가하기 귀찮아서 주석처리
//    Product findByisActiveIsTrue();
//    Product findByisActiveTrue();
//    Product findByisActiveIsFalse();
//    Product findByisActiveFalse();

    // And, Or
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);

    // GreaterThen, LessThan, Between (is 생략 가능)
    List<Product> findByPriceIsGreaterThan(int price);
    List<Product> findByPriceGreaterThan(int print);
    List<Product> findByPriceGreaterThanEqual(int price);
    List<Product> findByPriceIsLessThan(int price);
    List<Product> findByPriceLessThan(int price);
    List<Product> findByPriceLessThanEqual(int price);
    List<Product> findByPriceIsBetween(int lowPrice, int HighPrice);
    List<Product> findByPriceBetween(int lowPrice, int HighPrice);

    // 부분 일치 키워드 (is 생략 가능)
    List<Product> findByNameIsLike(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameIsContaining(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameContains(String name);
    List<Product> findByNameIsStartingWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameIsEndingWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndsWith(String name);

    // Asc(오름차순), Desc(내림차순)
    List<Product> findByNameOrderByNumberAsc(String name); // 이름검색 숫자 오름차순
    List<Product> findByNameOrderByNumberDesc(String name); // 내림차순
    List<Product> findByNameOrderByPriceAscStockDesc(String name); // 이름검색 가격 오름차순 재고 내림차순
    List<Product> findByName(String name, Sort sort); // 매개변수 활용

    // 페이징
    Page<Product> findByName(String name, Pageable pageable);

    // 쿼리 어노테이션
    @Query("SELECT p FROM Product AS p WHERE p.name = ?1") // 비추
    List<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    List<Product> findByNameParam(@Param("name") String name);
    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);
}
