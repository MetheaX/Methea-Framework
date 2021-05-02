package io.methea.repository.configuration.uri;

        import io.methea.domain.configuration.resource.entity.TResource;
        import io.methea.domain.configuration.resource.view.URIView;
        import io.methea.repository.hibernateextension.HibernateExtensionRepository;
        import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface URIRepository extends CrudRepository<TResource, String>, HibernateExtensionRepository<URIView, String> {
}
