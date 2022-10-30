/**
 * 
 */
package nimapinfotech.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Girish.Musham
 *
 */
@Entity
@Table(name = "category")
public class CategoryEnitty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@Column(name = "category_type")
	private String categoryType;
	
	@OneToMany(targetEntity = ProductEntity.class,cascade =  CascadeType.ALL)
	@JoinColumn(name = "cat_id" , referencedColumnName = "id")
	List<ProductEntity> product = new ArrayList<>();

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public List<ProductEntity> getProduct() {
		return product;
	}

	public void setProduct(List<ProductEntity> product) {
		this.product = product;
	}


	public CategoryEnitty() {

	}

	public CategoryEnitty(int id, String categoryName,String categoryType) {
		super();
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

}
