package rosalind

import grails.gorm.services.Service

@Service(EducationLevel)
interface EducationLevelService {

    EducationLevel get(Serializable id)

    List<EducationLevel> list(Map args)

    Long count()

    void delete(Serializable id)

    EducationLevel save(EducationLevel educationLevel)

}
