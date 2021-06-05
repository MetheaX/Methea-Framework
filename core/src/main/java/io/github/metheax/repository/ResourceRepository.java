package io.github.metheax.repository;

        import io.github.metheax.domain.entity.TResource;
        import io.github.metheax.domain.view.URIView;
        import io.github.metheax.repository.hibernateextension.HibernateExtensionRepository;
        import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface ResourceRepository extends CrudRepository<TResource, String>, HibernateExtensionRepository<URIView, String> {
}
