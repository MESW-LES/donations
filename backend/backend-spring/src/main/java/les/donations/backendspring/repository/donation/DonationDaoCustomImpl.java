package les.donations.backendspring.repository.donation;

import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.Donation;
import les.donations.backendspring.model.DonationProcess;
import les.donations.backendspring.model.Status;
import les.donations.backendspring.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DonationDaoCustomImpl extends AbstractHibernateRepository<Donation> implements DonationDaoCustom{

    @Override
    public List<Donation> getDonations(Integer donationProcessStatus) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Donation> criteriaQuery = criteriaBuilder.createQuery(Donation.class);
        Root<Donation> donationRoot = criteriaQuery.from(Donation.class);

        // join with DonationProcess
        donationRoot.join("donationProcess");

        criteriaQuery = criteriaQuery.select(donationRoot);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.isTrue(donationRoot.get("active")));

        Status status = Status.getById(donationProcessStatus);
        // if its to get the donation of a specific status
        if(status != null){
            predicates.add(criteriaBuilder.equal(donationRoot.get("donationProcess").get("status"), status));
        }

        // gets only the active donations
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return getCurrentSession().createQuery(criteriaQuery).list();
    }
}
