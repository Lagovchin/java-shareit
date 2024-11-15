package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoResponse;
import ru.practicum.shareit.exceptions.NotFoundDataException;
import ru.practicum.shareit.exceptions.NotOwnerException;

import java.util.List;

public interface BookingService {

    BookingDtoResponse add(BookingDto bookingDto, long idUser) throws NotFoundDataException;

    BookingDtoResponse approve(long idBooking, long idUser, boolean approved) throws NotOwnerException, NotFoundDataException;

    List<BookingDtoResponse> getByOwner(long idUser, String state) throws NotFoundDataException;

    BookingDtoResponse get(long id) throws NotFoundDataException;

    List<BookingDtoResponse> getByBooker(long idUser, String param);

    void delete(long id);
}
