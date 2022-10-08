package category;

import account.Account;
import account.AccountDto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.insert(category);
    }
    public void deleteCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category != null && (category.getExpenses() == null || category.getExpenses().isEmpty())) {
            categoryRepository.delete(category);
        }
    }
}




