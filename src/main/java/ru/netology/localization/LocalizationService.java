package ru.netology.localization;

import ru.netology.entity.Country;

public interface LocalizationService {
    String locale(Country country);
}