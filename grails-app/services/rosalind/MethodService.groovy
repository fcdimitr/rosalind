package rosalind

import grails.gorm.services.Service

@Service(Method)
interface MethodService {

    Method get(Serializable id)

    List<Method> list(Map args)

    Long count()

    void delete(Serializable id)

    Method save(Method method)

}
