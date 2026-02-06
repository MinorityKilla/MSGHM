package ru.netology.sender;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.localization.LocalizationService;

public class MessageSenderImpl implements MessageSender {
    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;
    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    public String send(String ip) {
        Location location = geoService.byIp(ip);
        if (location == null) {
            return "";
        }
        Country country = location.getCountry();
        String message = localizationService.locale(country);
        return message;
    }
}