package ru.netology.sender;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.localization.LocalizationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageSenderTest {

    @Test
    void russianIp_returnsRussianMessage() {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        when(geoService.byIp("172.123.12.19"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);

        assertEquals("Добро пожаловать", sender.send("172.123.12.19"));
    }

    @Test
    void americanIp_returnsEnglishMessage() {
        GeoService geoService = mock(GeoService.class);
        LocalizationService localizationService = mock(LocalizationService.class);

        when(geoService.byIp("96.123.12.19"))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);

        assertEquals("Welcome", sender.send("96.123.12.19"));
    }
}
