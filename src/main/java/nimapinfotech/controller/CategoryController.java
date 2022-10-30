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

/**
 * @author Girish.Musham
 *
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@GetMapping("/cat")
	public String display() {
		return "hello world";
	}

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@GetMapping("/{id}")
	public ResponseEntity<CategoryEnitty> findCategoryById(@PathVariable Integer id) throws DataNotFoundException {
		CategoryEnitty entity = null;
		try {
			entity = categoryServiceImpl.findById(id);
		} catch (Exception e) {
			if (entity == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<CategoryEnitty>(entity, HttpStatus.OK);
	}

	@GetMapping("")
	public List<CategoryEnitty> findAllCategory(
			@RequestParam(value = "page", defaultValue = "2", required = false) Integer page,
			@RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize) throws DataNotFoundException {
		List<CategoryEnitty> entity = categoryServiceImpl.findAll(page, pageSize);
		if (entity.isEmpty()) {
			throw new DataNotFoundException("Data Not Found");
		}
		return entity;
	}

	@PostMapping("")
	public ResponseEntity<CategoryEnitty> saveCategory(@RequestBody CategoryEnitty categoryEnitty)
			throws DataNotFoundException {
		categoryServiceImpl.saveCategory(categoryEnitty);
		return new ResponseEntity<CategoryEnitty>(categoryEnitty, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) throws DataNotFoundException {
		CategoryEnitty categoryEnitty = categoryServiceImpl.findById(id);
		if (categoryEnitty == null) {
			throw new DataNotFoundException("Data Not Found");

		}
		categoryServiceImpl.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryEnitty> updateCategories(@PathVariable Integer id,
			@RequestBody CategoryEnitty categoryEnitty) throws DataNotFoundException {
		try {

			CategoryEnitty entity = null;
			entity = categoryServiceImpl.findById(id);

			List<ProductEntity> product = categoryEnitty.getProduct();
			if (entity == null && product.isEmpty()) {
				throw new DataNotFoundException("Data Not Found");
			}
			entity.setCategoryName(categoryEnitty.getCategoryName());
			entity.setCategoryType(categoryEnitty.getCategoryType());
			categoryServiceImpl.saveCategory(entity);
		} catch (Exception e) {
			throw new DataNotFoundException("Data Not Found");

		}
		return new ResponseEntity<CategoryEnitty>(HttpStatus.OK);
	}

}
