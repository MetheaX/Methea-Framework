package io.methea.service.abs;

import io.methea.constant.MConstant;
import io.methea.domain.basebinder.abs.AbstractMetheaBinder;
import io.methea.domain.baseentity.BaseEntity;
import io.methea.domain.baseentity.abs.AbstractMetheaEntity;
import io.methea.domain.baseview.abs.AbstractMetheaView;
import io.methea.repository.hibernateextension.HibernateExtensionRepository;
import io.methea.repository.hibernateextension.domain.HibernatePage;
import io.methea.util.MBeanUtils;
import io.methea.util.Pagination;
import io.methea.util.PrincipalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Author : DKSilverX
 * Date : 17/04/2020
 */
public abstract class AbstractMetheaService<E extends AbstractMetheaEntity<E>, B extends AbstractMetheaBinder<B>, ID,
        V extends AbstractMetheaView<V>> {

    private static Logger log = LoggerFactory.getLogger(AbstractMetheaService.class);
    private static final String STATUS = "status";
    private final Class<V> view;
    private final CrudRepository repository;
    private final HibernateExtensionRepository<V, ID> extensionRepository;

    public AbstractMetheaService(Class<V> view, CrudRepository repository,
                                 HibernateExtensionRepository<V, ID> extensionRepository) {
        this.view = view;
        this.repository = repository;
        this.extensionRepository = extensionRepository;
    }

    @Inject
    protected HttpServletRequest request;

    public E saveEntity(E entity, B binder) {
        try {
            BeanUtils.copyProperties(binder, entity, MBeanUtils.getNullProperties(binder));
            setCreateAuditLog(entity);
            repository.save(entity);
            return entity;
        } catch (Exception ex) {
            log.error(">>>>> Save entity error: ", ex);
        }
        return null;
    }

    public E modifyEntity(ID id, B binder) {
        try {
            Optional<E> optional = repository.findById(id);
            if (optional.isPresent()) {
                E entity = optional.get();
                BeanUtils.copyProperties(binder, entity, MBeanUtils.getNullProperties(binder));
                setModifiedAuditLog(entity);
                repository.save(entity);
                return entity;
            }
        } catch (Exception ex) {
            log.error(">>>>> Modify entity error: ", ex);
        }
        return null;
    }

    public boolean activateEntity(ID id) {
        boolean isActivate = false;
        try {
            Optional<E> optional = repository.findById(id);
            if (optional.isPresent()) {
                E entity = optional.get();
                setStatusAuditLog(entity, MConstant.ACTIVE_STATUS);
                repository.save(entity);
                isActivate = true;
            }
        } catch (Exception ex) {
            log.error(">>>>> Activate entity error: ", ex);
        }
        return isActivate;
    }

    public boolean deactivateEntity(ID id) {
        boolean isDeactivate = false;
        try {
            Optional<E> optional = repository.findById(id);
            if (optional.isPresent()) {
                E entity = optional.get();
                setStatusAuditLog(entity, "I");
                repository.save(entity);
                isDeactivate = true;
            }
        } catch (Exception ex) {
            log.error(">>>>> Deactivate entity error: ", ex);
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
            log.info(">>>>> getAllEntityViewByFilter error: ", ex);
        }
        return null;
    }

    public V getEntityViewById(ID id) {
        Map<String, Object> param = new HashMap<>();
        try {
            return extensionRepository.getEntityById(view, id);
        } catch (Exception ex) {
            log.info(">>>>> getEntityViewById error: ", ex);
        }
        return null;
    }

    private void setCreateAuditLog(E entity) {
        BaseEntity obj = new BaseEntity(MConstant.ACTIVE_STATUS, PrincipalUtils.getUserLoginId(request), LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()), PrincipalUtils.getUserLoginId(request), LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()));
        BeanUtils.copyProperties(obj, entity);
    }

    private void setModifiedAuditLog(E entity) {
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
