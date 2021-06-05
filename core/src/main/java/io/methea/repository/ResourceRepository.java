package io.methea.repository;

        import io.methea.domain.entity.TResource;
        import io.methea.domain.view.URIView;
        import io.methea.repository.hibernateextension.HibernateExtensionRepository;
        import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface ResourceRepository extends CrudRepository<TResource, String>, HibernateExtensionRepository<URIView, String> {
}
