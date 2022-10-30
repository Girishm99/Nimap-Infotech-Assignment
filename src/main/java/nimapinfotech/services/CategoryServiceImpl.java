/**
 * 
 */
package nimapinfotech.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import nimapinfotech.entity.CategoryEnitty;
import nimapinfotech.repository.CategoryRepository;

/**
 * @author Girish.Musham
 *
 */
@Service
@Transactional
public class CategoryServiceImpl  {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public CategoryEnitty findById(Integer id) {
        return categoryRepository.findById(id).get();
    } 
	
	public List<CategoryEnitty> findAll(Integer pageNumber,Integer pageSize) {
		Pageable page1 =   PageRequest.of(pageNumber,pageSize);
		Page<CategoryEnitty> catPagePost = categoryRepository.findAll(page1);
		List<CategoryEnitty> allCategories = catPagePost.getContent();
		return allCategories;
	}
	
	public void saveCategory(CategoryEnitty categoryEnitty) {
		categoryRepository.save(categoryEnitty);
	}
	
	public void deleteCategory(Integer id) {
		categoryRepository.deleteById(id);
	}
	

}
