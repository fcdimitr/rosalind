package rosalind

import grails.gorm.services.Service

@Service(ExperimentType)
interface ExperimentTypeService {

    ExperimentType get(Serializable id)

    List<ExperimentType> list(Map args)

    Long count()

    void delete(Serializable id)

    ExperimentType save(ExperimentType experimentType)

}
