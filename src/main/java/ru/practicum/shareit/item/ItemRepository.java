package ru.practicum.shareit.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByOwner_id(long id);

    @Query(value = "SELECT DISTINCT * FROM ITEMS " +
            "WHERE UPPER(name) LIKE %:searchString% OR UPPER(description) LIKE %:searchString%", nativeQuery = true)
    List<Item> search(String searchString);
}
