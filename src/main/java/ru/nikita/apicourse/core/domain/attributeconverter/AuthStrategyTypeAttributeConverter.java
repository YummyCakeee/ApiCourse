package ru.nikita.apicourse.core.domain.attributeconverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.nikita.apicourse.core.domain.auth.type.AuthStrategyType;

@Converter(autoApply = true)
public class AuthStrategyTypeAttributeConverter implements AttributeConverter<AuthStrategyType, Short> {
    @Override
    public Short convertToDatabaseColumn(AuthStrategyType attribute) {
        return attribute.getCode();
    }

    @Override
    public AuthStrategyType convertToEntityAttribute(Short code) {
        return AuthStrategyType.fromCode(code);
    }
}
