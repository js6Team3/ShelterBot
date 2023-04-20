package js6team3.tbot.service.impl;

import js6team3.tbot.entity.Cat;
import js6team3.tbot.entity.Dog;
import js6team3.tbot.service.ValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(Cat cat) {
        return cat != null
                && cat.getNickname() != null
                && !StringUtils.isEmpty(cat.getNickname())
                && cat.getBreed() != null
                && !StringUtils.isEmpty(cat.getBreed())
                && cat.getAge() > 0
                && cat.getAge() < 30
                && cat.getDescription() != null
                && !StringUtils.isEmpty(cat.getDescription());
    }

    @Override
    public boolean validate(Dog dog) {
        return dog != null
                && dog.getNickname() != null
                && !StringUtils.isEmpty(dog.getNickname())
                && dog.getBreed() != null
                && !StringUtils.isEmpty(dog.getBreed())
                && dog.getAge() > 0
                && dog.getAge() < 30
                && dog.getDescription() != null
                && !StringUtils.isEmpty(dog.getDescription());
    }
}
