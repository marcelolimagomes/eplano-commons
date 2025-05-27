package br.com.eplano.commons.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseDTO<E> {
    // Converte entidade para DTO
    public static <D, E> D fromEntity(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : dtoClass.getDeclaredFields()) {
                dtoField.setAccessible(true);
                Field entityField;
                try {
                    entityField = entity.getClass().getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    Object value = entityField.get(entity);

                    // Se for Enum, converte para EnumNameValueDTO
                    if (value != null && value.getClass().isEnum()
                            && dtoField.getType().getSimpleName().equals("EnumNameValueDTO")) {
                        Field descField = value.getClass().getDeclaredField("descricao");
                        descField.setAccessible(true);
                        String descricao = (String) descField.get(value);
                        dtoField.set(dto, new EnumNameValueDTO(descricao, ((Enum<?>) value).name()));
                    }
                    // Se for um campo DTO (relacionamento), converte recursivamente
                    else if (value != null
                            && dtoField.getType().getPackage() != null
                            && dtoField.getType().getPackage().getName().startsWith("br.com.eplano.")
                            && !dtoField.getType().equals(EnumNameValueDTO.class)) {
                        // Busca método fromEntity na classe do DTO relacionado
                        try {
                            dtoField.set(dto, dtoField.getType()
                                    .getMethod("fromEntity", Object.class, Class.class)
                                    .invoke(null, value, dtoField.getType()));
                        } catch (Exception e) {
                            log.warn("Erro ao converter relacionamento para DTO: {} - {} - {}", dtoField.getName(),
                                    e.getCause(),
                                    e.getMessage());
                            log.error("", e);
                        }
                    }
                    // Se for uma lista de DTOs
                    else if (value != null
                            && List.class.isAssignableFrom(dtoField.getType())
                            && value instanceof List<?>) {
                        try {
                            // Descobre o tipo do DTO da lista
                            Class<?> dtoListType = (Class<?>) ((java.lang.reflect.ParameterizedType) dtoField
                                    .getGenericType())
                                    .getActualTypeArguments()[0];
                            List<Object> dtoList = new ArrayList<>();
                            for (Object item : (List<?>) value) {
                                dtoList.add(fromEntity(item, dtoListType));
                            }
                            dtoField.set(dto, dtoList);
                        } catch (Exception e) {
                            log.warn("Erro ao converter lista para DTO: {} - {}", dtoField.getName(), e.getMessage());
                        }
                    }
                    // Se for um campo simples
                    else {
                        dtoField.set(dto, value);
                    }
                } catch (NoSuchFieldException e) {
                    log.warn("Campo não existe na DTO: {} - {}", dtoField.getName(), e.getMessage());
                } catch (IllegalAccessException e) {
                    log.warn("Erro ao acessar o campo: {} - {}", dtoField.getName(), e.getMessage());
                } catch (IllegalArgumentException e) {
                    log.warn("Erro ao converter o campo: {} - {}", dtoField.getName(), e.getMessage());
                }
            }
            return dto;
        } catch (Exception e) {
            log.error("Erro ao converter entidade para DTO: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao converter entidade para DTO", e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public E toEntity(Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : this.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                Field entityField;
                try {
                    entityField = entityClass.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    Object value = dtoField.get(this);

                    // Não sobrescreve valorReceita manualmente
                    if ("valorReceita".equals(dtoField.getName())) {
                        continue;
                    }

                    // Se for EnumNameValueDTO, converte para Enum
                    if (value != null && value.getClass().getSimpleName().equals("EnumNameValueDTO")
                            && entityField.getType().isEnum()) {
                        String name = (String) value.getClass().getMethod("getId").invoke(value);
                        Object enumValue = Enum.valueOf((Class<Enum>) entityField.getType(), name);
                        entityField.set(entity, enumValue);
                    }
                    // Se for um campo DTO (relacionamento), converte recursivamente para entidade
                    else if (value != null
                            && value.getClass().getPackage() != null
                            && value.getClass().getPackage().getName().startsWith("br.com.eplano.")
                            && !value.getClass().equals(EnumNameValueDTO.class)) {
                        try {
                            // Busca método toEntity na classe do DTO relacionado
                            Object relatedEntity = value.getClass()
                                    .getMethod("toEntity", Class.class)
                                    .invoke(value, entityField.getType());
                            entityField.set(entity, relatedEntity);
                        } catch (Exception e) {
                            log.warn("Erro ao converter relacionamento para entidade: {} - {}", dtoField.getName(),
                                    e.getMessage());
                        }
                    }
                    // Se for uma lista de DTOs
                    else if (value != null
                            && value instanceof List<?>
                            && List.class.isAssignableFrom(entityField.getType())) {
                        try {
                            Class entityListType = (Class<?>) ((java.lang.reflect.ParameterizedType) entityField
                                    .getGenericType())
                                    .getActualTypeArguments()[0];
                            List<Object> entityList = new ArrayList<>();
                            for (Object item : (List<?>) value) {
                                entityList.add(((BaseDTO<Object>) item).toEntity(entityListType));
                            }
                            entityField.set(entity, entityList);
                        } catch (Exception e) {
                            log.warn("Erro ao converter lista para entidade: {} - {}", dtoField.getName(),
                                    e.getMessage());
                        }
                    }
                    // Se for um campo simples
                    else {
                        entityField.set(entity, value);
                    }
                } catch (NoSuchFieldException e) {
                    log.warn("Campo não existe na entidade: {} - {}", dtoField.getName(), e.getMessage());
                } catch (IllegalAccessException e) {
                    log.warn("Erro ao acessar o campo: {} - {}", dtoField.getName(), e.getMessage());
                } catch (IllegalArgumentException e) {
                    log.warn("Erro ao converter o campo: {} - {}", dtoField.getName(), e.getMessage());
                }
            }
            return entity;
        } catch (Exception e) {
            log.error("Erro ao converter DTO para entidade: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao converter DTO para entidade", e);
        }
    }
}