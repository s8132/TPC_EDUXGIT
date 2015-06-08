package pl.edu.pjwstk.s8132.service;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;
import pl.edu.pjwstk.s8132.web.controller.api.json.SubjectJSON;

@Component
public class CustomSubjectMapper extends CustomMapper<Subject, SubjectJSON>{

    @Autowired private UserProfileDao userProfileDao;
    @Autowired private MapperFacade mapper;

    @Override
    public void mapAtoB(Subject subject, SubjectJSON subjectJSON, MappingContext context) {
        UserProfile userProfile = userProfileDao.getById(subject.getTeacherId());
        subjectJSON.setTeacherEmail(userProfile.getEmail());
        subjectJSON.setTeacherFirstName(userProfile.getFirstName());
        subjectJSON.setTeacherLastName(userProfile.getLastName());
        subjectJSON.setSubjectId(subject.getId());
        subjectJSON.setName(subject.getName());
        subjectJSON.setCode(subject.getCode());
        subjectJSON.setStatus(subject.getStatus());
    }

    @Override
    public void mapBtoA(SubjectJSON subjectJSON, Subject subject, MappingContext context) {
        super.mapBtoA(subjectJSON, subject, context);
    }
}
