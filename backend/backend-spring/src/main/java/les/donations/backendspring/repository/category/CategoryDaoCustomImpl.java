package les.donations.backendspring.repository.category;

import les.donations.backendspring.model.Category;
import les.donations.backendspring.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDaoCustomImpl extends AbstractHibernateRepository<Category> implements CategoryDaoCustom {

    @Override
    public List<Category> getCategories(Boolean onlyActive) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoryRoot = criteriaQuery.from(Category.class);

        criteriaQuery = criteriaQuery.select(categoryRoot);
        // gets only the active categories
        if(onlyActive != null && onlyActive){
            criteriaQuery.where(criteriaBuilder.isTrue(categoryRoot.get("active")));
        }
        return getCurrentSession().createQuery(criteriaQuery).list();
    }
}
