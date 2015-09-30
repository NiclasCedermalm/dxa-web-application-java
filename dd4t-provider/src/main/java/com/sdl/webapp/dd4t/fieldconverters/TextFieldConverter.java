package com.sdl.webapp.dd4t.fieldconverters;

import com.sdl.webapp.dd4t.ModelBuilderPipeline;
import org.dd4t.contentmodel.FieldType;
import org.dd4t.contentmodel.impl.BaseField;
import org.springframework.stereotype.Component;

import com.sdl.webapp.dd4t.EntityBuilder;

import java.util.List;

@Component
public class TextFieldConverter extends AbstractFieldConverter {

    private static final FieldType[] SUPPORTED_FIELD_TYPES = {FieldType.TEXT};

    @Override
    public FieldType[] supportedFieldTypes() {
        return SUPPORTED_FIELD_TYPES;
    }

    @Override
    protected List<?> getFieldValues(BaseField field, Class<?> targetClass, ModelBuilderPipeline builder) throws FieldConverterException {
        return field.getTextValues();
    }
}
