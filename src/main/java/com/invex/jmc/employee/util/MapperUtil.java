package com.invex.jmc.employee.util;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Utility class that provides helper methods for mapping objects and lists of
 * objects between different types using {@link ModelMapper}.
 *
 * <p>This component centralizes object-to-object mapping operations, promoting
 * reusable and consistent transformations across the application.</p>
 */
@Component
public class MapperUtil {

  private final ModelMapper modelMapper;

  /**
   * Creates a new instance of {@code MapperUtil} with the provided {@link ModelMapper}.
   *
   * <p>The {@code ModelMapper} bean is typically configured and exposed in
   * {@link com.invex.jmc.employee.config.MapperConfig}.</p>
   *
   * @param modelMapper the {@code ModelMapper} instance used for object conversions.
   */
  public MapperUtil(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  /**
   * Maps a list of source objects into a list of destination objects.
   *
   * @param sourceList the list of source objects to be mapped
   * @param destinationClass the target class into which each element should be mapped
   * @param <S> the source type
   * @param <D> the destination type
   * @return a new list containing the mapped destination objects
   */
  public <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
    List<D> list = new ArrayList<>();
    for (S element : sourceList) {
      D mapped = modelMapper.map(element, destinationClass);
      list.add(mapped);
    }
    return list;
  }

  /**
   * Maps a single source object into an instance of the specified destination type.
   *
   * @param source the object to be mapped
   * @param destinationType the target type class
   * @param <D> the destination type
   * @return the mapped destination object
   */
  public <D> D map(Object source, Class<D> destinationType) {
    return modelMapper.map(source, destinationType);
  }
}
