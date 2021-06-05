package io.methea.service.abs;

import io.methea.constant.MetheaConstant;
import io.methea.domain.binder.abs.AbstractMetheaBinder;
import io.methea.domain.entity.BaseEntity;
import io.methea.domain.entity.abs.AbstractMetheaEntity;
import io.methea.domain.view.abs.AbstractMetheaView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.repository.hibernateextension.domain.HibernatePage;
import io.methea.utils.MBeanUtils;
import io.methea.utils.Pagination;
import io.methea.utils.PrincipalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
public abstract class AbstractSimpleMetheaService<E extends AbstractMetheaEntity<E>, B extends AbstractMetheaBinder<B>, ID,
        V extends AbstractMetheaView<V>> implements SimpleMetheaService<E, B, ID, V> {

    private static final Logger log = LoggerFactory.getLogger(AbstractSimpleMetheaService.class);
    private static final String STATUS = "status";
    private final Class<V> view;
    private final CrudRepository repository;
    private final HibernateExtensionRepository<V, ID> extensionRepository;
    @Inject
    private ApplicationEventPublisher publisher;

    public AbstractSimpleMetheaService(Class<V> view, CrudRepository repository,
                                       HibernateExtensionRepository<V, ID> extensionRepository) {
        this.view = view;
        this.repository = repository;
        this.extensionRepository = extensionRepository;
    }

    @Inject
    protected HttpServletRequest request;

    @Transactional
    public E saveEntity(E entity, B binder) {
        try {
            BeanUtils.copyProperties(binder, entity, MBeanUtils.getNullProperties(binder));
            setCreateAuditLog(entity);
            entity.setCreate(true);
            repository.save(entity);
            publisher.publishEvent(entity);
            return entity;
        } catch (Exception ex) {
            log.error("=========> Save entity error: ", ex);
        }
        return null;
    }

    @Transactional
    public E modifyEntity(ID id, B binder) {
        try {
            Optional<E> optional = repository.findById(id);
            if (optional.isPresent()) {
                E entity = optional.get();
                BeanUtils.copyProperties(binder, entity, MBeanUtils.getNullProperties(binder));
                setModifiedAuditLog(entity);
                entity.setUpdate(true);
                repository.save(entity);
                publisher.publishEvent(entity);
                return entity;
            }
        } catch (Exception ex) {
            log.error("=========> Modify entity error: ", ex);
        }
        return null;
    }

    @Transactional
    public boolean activateEntity(ID id) {
        boolean isActivate = false;
        try {
            Optional<E> optional = repository.findById(id);
            if (optional.isPresent()) {
                E entity = optional.get();
                setStatusAuditLog(entity, MetheaConstant.ACTIVE_STATUS);
                entity.setActivate(true);
                repository.save(entity);
                publisher.publishEvent(entity);
                isActivate = true;
            }
        } catch (Exception ex) {
            log.error("=========> Activate entity error: ", ex);
            throw new RuntimeException(ex);
        }
        return isActivate;
    }

    @Transactional
    public boolean deactivateEntity(ID id) {
        boolean isDeactivate = false;
        try {
            Optional<E> optional = repository.findById(id);
            if (optional.isPresent()) {
                E entity = optional.get();
                setStatusAuditLog(entity, MetheaConstant.INACTIVE_STATUS);
                entity.setDeactivate(true);
                repository.save(entity);
                publisher.publishEvent(entity);
                isDeactivate = true;
            }
        } catch (Exception ex) {
            log.error("=========> Deactivate entity error: ", ex);
            throw new RuntimeException(ex);
        }
        return isDeactivate;
    }

    public List<V> getAllEntityViewByFilter(Map<String, Object> param, Pagination pagination) {
        try {
            HibernatePage<V> page = extensionRepository.getByQuery(param, view,
                    pagination.getSize(), pagination.getOffSet());
            pagination.setTotalCounts(page.getTotalCount());
            return page.getContent();
        } catch (Exception ex) {
            log.info("=========> getAllEntityViewByFilter error: ", ex);
        }
        return null;
    }

    public V getEntityViewById(ID id) {
        try {
            return extensionRepository.getEntityById(view, id);
        } catch (Exception ex) {
            log.info("=========> getEntityViewById error: ", ex);
        }
        return null;
    }

    public Optional<E> getEntityById(ID id) {
        try {
            return repository.findById(id);
        } catch (Exception ex) {
            log.info("=========> getEntityById error: ", ex);
        }
        return null;
    }

    private void setCreateAuditLog(E entity) {
        BaseEntity obj = new BaseEntity(MetheaConstant.ACTIVE_STATUS, PrincipalUtils.getUserLoginId(request), LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()), PrincipalUtils.getUserLoginId(request), LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        BeanUtils.copyProperties(obj, entity);
    }

    protected void setModifiedAuditLog(E entity) {
        BaseEntity obj = new BaseEntity(PrincipalUtils.getUserLoginId(request), LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        BeanUtils.copyProperties(obj, entity, STATUS, "createdDateTime", "createdUser");
    }

    private void setStatusAuditLog(E entity, String status) {
        BaseEntity obj = new BaseEntity(status, PrincipalUtils.getUserLoginId(request), LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        BeanUtils.copyProperties(obj, entity, "createdDateTime", "createdUser");
    }
}
