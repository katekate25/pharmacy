package com.epam.training.epharmacy.controller.util;

import com.epam.training.epharmacy.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.DELIVERY_DATE_FORMAT_PATTERN;

public class ControllerUtils {

    private static final DateFormat deliveryTimeFormatter = new SimpleDateFormat(DELIVERY_DATE_FORMAT_PATTERN);

    private ControllerUtils() {
    }

    public static User getUserFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session != null) {
           return (User) session.getAttribute("user");
        }
        return null;
    }

    public static String getMinDeliveryDate() {
        final int ONE_DAY = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, ONE_DAY);
        return deliveryTimeFormatter.format(calendar.getTime());
    }

    public static String getMaxDeliveryDate() {
        final int TWO_WEEKS = 2;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, TWO_WEEKS);
        return deliveryTimeFormatter.format(calendar.getTime());
    }
}
