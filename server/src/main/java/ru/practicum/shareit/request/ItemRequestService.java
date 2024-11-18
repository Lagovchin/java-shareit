package ru.practicum.shareit.request;

import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {

    ItemRequestDto add(long idRequester, String description) throws NotFoundDataException;

    ItemRequestDto finById(long id) throws NotFoundDataException;

    List<ItemRequestDto> findAll(long idRequestor);

    List<ItemRequestDto> findByRequester(long idRequestor);
}
