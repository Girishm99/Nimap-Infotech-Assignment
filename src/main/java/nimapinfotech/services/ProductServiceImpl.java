/**
 * 
 */
package nimapinfotech.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import nimapinfotech.entity.CategoryEnitty;
import nimapinfotech.entity.ProductEntity;
import nimapinfotech.repository.ProductRepository;

/**
 * @author Girish.Musham
 *
 */
@Service
@Transactional
public class ProductServiceImpl {

	
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductEntity findById(Integer id) {
        return productRepository.findById(id).get();
    }
	
	public List<ProductEntity> findAllProduct(Integer pageNumber,Integer pageSize) {
		Pageable page1 =   PageRequest.of(pageNumber,pageSize);
		Page<ProductEntity> productPagePost = productRepository.findAll(page1);
		List<ProductEntity> allProduct = productPagePost.getContent();
		return allProduct;
	}
	
	public void saveProduct(ProductEntity productEntity) {
		productRepository.save(productEntity);
	}
	
	public void deleteCategory(Integer id) {
		productRepository.deleteById(id);
	}
}
