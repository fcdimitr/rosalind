package rosalind

import grails.gorm.services.Service

@Service(Cohort)
interface CohortService {

    Cohort get(Serializable id)

    List<Cohort> list(Map args)

    Long count()

    void delete(Serializable id)

    Cohort save(Cohort cohort)

}
