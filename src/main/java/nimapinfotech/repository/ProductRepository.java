/**
 * 
 */
package nimapinfotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nimapinfotech.entity.ProductEntity;

/**
 * @author Girish.Musham
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
