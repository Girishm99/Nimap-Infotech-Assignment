/**
 * 
 */
package nimapinfotech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nimapinfotech.entity.CategoryEnitty;
import nimapinfotech.entity.ProductEntity;
import nimapinfotech.exception.DataNotFoundException;
import nimapinfotech.services.CategoryServiceImpl;
import nimapinfotech.services.ProductServiceImpl;

/**
 * @author Girish.Musham
 *
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	
	
	
	@GetMapping("/product")
	public String display() {
		return "hello world";
	}


	@GetMapping("/{id}")
	public ResponseEntity<ProductEntity> findProductById(@PathVariable Integer id) throws DataNotFoundException {
		ProductEntity entity = null;
		try {
			entity = productServiceImpl.findById(id);
		} catch (Exception e) {
			if (entity == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<ProductEntity>(entity, HttpStatus.OK);		
				
	}

	@GetMapping("")
	public List<ProductEntity> findAllProduct(@RequestParam(value = "page", defaultValue = "2", required = false) Integer page,
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) throws DataNotFoundException {
		List<ProductEntity> entity = productServiceImpl.findAllProduct(page,pageSize);
		if (entity.isEmpty()) {
			throw new DataNotFoundException("Data Not Found");
		}
		return entity;
	}

	@PostMapping("")
	public ResponseEntity<ProductEntity> saveCategory(@RequestBody ProductEntity productEntity)
			throws DataNotFoundException {
		productServiceImpl.saveProduct(productEntity);
		return new ResponseEntity<ProductEntity>(productEntity, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) throws DataNotFoundException {
		ProductEntity categoryEnitty = productServiceImpl.findById(id);
		if (categoryEnitty == null) {
			throw new DataNotFoundException("Data Not Found");

		}
		productServiceImpl.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductEntity> updateCategories(@PathVariable Integer id,
			@RequestBody ProductEntity productEntity) throws DataNotFoundException {
		try {

			ProductEntity entity = null;
			entity = productServiceImpl.findById(id);
			if (entity == null ) {
				throw new DataNotFoundException("Data Not Found");
			}
			entity.setProductName(productEntity.getProductName());
			entity.setProductPrice(productEntity.getProductPrice());
			productServiceImpl.saveProduct(entity);
		} catch (Exception e) {
			throw new DataNotFoundException("Data Not Found");

		}
		return new ResponseEntity<ProductEntity>(HttpStatus.OK);
	}
	
}
