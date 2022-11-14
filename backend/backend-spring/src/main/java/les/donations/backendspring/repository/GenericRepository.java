package les.donations.backendspring.repository;

import java.io.Serializable;

public class GenericRepository<T extends Serializable> extends AbstractHibernateRepository<T> implements IGenericRepository<T>{
}
