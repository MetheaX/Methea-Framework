package io.methea.repository.configuration.uri;

        import io.methea.domain.configuration.uri.entity.TMstURI;
        import io.methea.domain.configuration.uri.view.URIView;
        import io.methea.repository.hibernateextension.HibernateExtensionRepository;
        import org.springframework.data.repository.CrudRepository;

/**
 * Author : DKSilverX
 * Date : 15/09/2019
 */
public interface URIRepository extends CrudRepository<TMstURI, String>, HibernateExtensionRepository<URIView, String> {
}
