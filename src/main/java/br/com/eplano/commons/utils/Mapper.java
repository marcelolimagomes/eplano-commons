package br.com.eplano.commons.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.eplano.commons.dto.EntityDTO;
import br.com.eplano.commons.dto.EnumNameValueDTO;
import br.com.eplano.commons.entity.BaseEntity;
import br.com.eplano.commons.interfaces.DescricaoEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mapper {

    // Converte entidade para DTO
    @SuppressWarnings("unchecked")
    public static <D extends EntityDTO, E extends BaseEntity> D fromEntity(E entity, Class<D> dtoClass) {
        if (entity == null)
            return null;
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : dtoClass.getDeclaredFields()) {
                dtoField.setAccessible(true);
                try {
                    Field entityField = getField(entity.getClass(), dtoField.getName());
                    if (entityField == null)
                        continue;
                    entityField.setAccessible(true);
                    Object value = entityField.get(entity);

                    // Enum para EnumNameValueDTO
                    if (value != null && value.getClass().isEnum()
                            && dtoField.getType().equals(EnumNameValueDTO.class)) {
                        String descricao = null;
                        try {
                            descricao = (String) value.getClass().getMethod("getDescricao").invoke(value);
                        } catch (Exception e) {
                            descricao = value.toString();
                        }
                        dtoField.set(dto, new EnumNameValueDTO(descricao, ((Enum<?>) value).name()));
                    }
                    // Campo array (ex: byte[])
                    else if (value != null && dtoField.getType().isArray()) {
                        dtoField.set(dto, value);
                    }
                    // Relacionamento: objeto customizado
                    else if (value != null
                            && isCustomClass(dtoField.getType())
                            && !dtoField.getType().equals(EnumNameValueDTO.class)) {
                        Object relatedDto = fromEntity((BaseEntity) value, (Class<EntityDTO>) dtoField.getType());
                        dtoField.set(dto, relatedDto);
                    }
                    // Lista de objetos
                    else if (value != null
                            && List.class.isAssignableFrom(dtoField.getType())
                            && value instanceof List<?>) {
                        Class<?> dtoListType = getListGenericType(dtoField);
                        if (dtoListType != null) {
                            List<Object> dtoList = new ArrayList<>();
                            for (Object item : (List<?>) value) {
                                if (item != null)
                                    dtoList.add(fromEntity((BaseEntity) item, (Class<EntityDTO>) dtoListType));
                            }
                            dtoField.set(dto, dtoList);
                        }
                    }
                    // Campo simples
                    else {
                        dtoField.set(dto, value);
                    }
                } catch (Exception e) {
                    log.warn("Erro ao mapear campo '{}' para DTO: {}", dtoField.getName(), e.getMessage());
                }
            }
            return dto;
        } catch (Exception e) {
            log.error("Erro ao converter entidade para DTO: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao converter entidade para DTO", e);
        }
    }

    // Converte DTO para entidade
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <E extends BaseEntity, D extends EntityDTO> E toEntity(D dto, Class<E> entityClass) {
        if (dto == null)
            return null;
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : dto.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                try {
                    Field entityField = getField(entityClass, dtoField.getName());
                    if (entityField == null)
                        continue;
                    entityField.setAccessible(true);
                    Object value = dtoField.get(dto);

                    // EnumNameValueDTO para Enum
                    if (value != null && value.getClass().equals(EnumNameValueDTO.class)
                            && entityField.getType().isEnum()) {
                        String name = (String) value.getClass().getMethod("getId").invoke(value);
                        Object enumValue = Enum.valueOf((Class<Enum>) entityField.getType(), name);
                        entityField.set(entity, enumValue);
                    }
                    // Campo array (ex: byte[])
                    else if (value != null && entityField.getType().isArray()) {
                        entityField.set(entity, value);
                    }
                    // Relacionamento: objeto customizado
                    else if (value != null
                            && isCustomClass(entityField.getType())
                            && !value.getClass().equals(EnumNameValueDTO.class)) {
                        BaseEntity relatedEntity = toEntity((EntityDTO) value,
                                (Class<BaseEntity>) entityField.getType());
                        entityField.set(entity, relatedEntity);
                    }
                    // Lista de objetos
                    else if (value != null
                            && value instanceof List<?>
                            && List.class.isAssignableFrom(entityField.getType())) {
                        Class<?> entityListType = getListGenericType(entityField);
                        if (entityListType != null) {
                            List<Object> entityList = new ArrayList<>();
                            for (Object item : (List<?>) value) {
                                entityList.add(toEntity((EntityDTO) item, (Class<BaseEntity>) entityListType));
                            }
                            entityField.set(entity, entityList);
                        }
                    }
                    // Campo simples
                    else {
                        entityField.set(entity, value);
                    }
                } catch (Exception e) {
                    log.warn("Erro ao mapear campo '{}' para entidade: {}", dtoField.getName(), e.getMessage());
                }
            }
            return entity;
        } catch (Exception e) {
            log.error("Erro ao converter DTO para entidade: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao converter DTO para entidade", e);
        }
    }

    // Utilitário: busca campo na hierarquia de classes
    private static Field getField(Class<?> clazz, String name) {
        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField(name);
            } catch (NoSuchFieldException ignored) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    // Utilitário: verifica se é classe customizada do projeto (não Java padrão)
    private static boolean isCustomClass(Class<?> clazz) {
        return clazz != null
                && !clazz.isPrimitive()
                && !clazz.getName().startsWith("java.")
                && !clazz.isEnum()
                && !clazz.equals(EnumNameValueDTO.class)
                && !List.class.isAssignableFrom(clazz);
    }

    // Utilitário: obtém tipo genérico de listas
    private static Class<?> getListGenericType(Field field) {
        try {
            java.lang.reflect.Type genericType = field.getGenericType();
            if (genericType instanceof java.lang.reflect.ParameterizedType) {
                java.lang.reflect.Type[] args = ((java.lang.reflect.ParameterizedType) genericType)
                        .getActualTypeArguments();
                if (args.length > 0 && args[0] instanceof Class<?>) {
                    return (Class<?>) args[0];
                }
            }
        } catch (Exception e) {
            log.error("Erro ao recuperar getListGenericType", e);
        }
        return null;
    }

    // Métodos utilitários para EnumNameValueDTO
    public static EnumNameValueDTO toEnumDTO(DescricaoEnum enumValue) {
        if (enumValue == null) {
            return null;
        }
        return new EnumNameValueDTO(enumValue.getDescricao(), enumValue.name());
    }

    public static <T extends Enum<T>> T fromEnumDTO(EnumNameValueDTO dto, Class<T> enumClass) {
        if (dto == null || dto.getId() == null) {
            throw new NullPointerException("dto is null");
        }
        T result = Enum.valueOf(enumClass, dto.getId());
        if (result != null) {
            return result;
        } else if (dto.getId() == null) {
            throw new NullPointerException("dto is null");
        } else {
            throw new IllegalArgumentException("No enum constant " + enumClass.getCanonicalName() + "." + dto.getId());
        }
    }
}