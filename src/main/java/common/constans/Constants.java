package common.constans;

import common.entities.Order;
import common.entities.User;
import common.entities.UserAuth;

public class Constants {
    public static final User USER = new User("qwer@qw.ru", "1234qwerty", "Kuslo");
    public static final User TEST_USER = new User("test-data@yandex.ru", "password", "Username");
    public static final UserAuth USER_AUTH = new UserAuth("qwer@qw.ru", "1234qwerty");
    public static final Order EMPTY_ORDER = new Order(new String[]{});
    public static final Order INVALID_INGREDIENTS_ORDER = new Order(new String[]{"qwer"});
    public static final String RESOURCE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_URI = "/api/auth/register";
    public static final String LOGIN_URI = "/api/auth/login";
    public static final String USER_URI = "/api/auth/user";
    public static final String ORDERS_URI = "/api/orders";
    public static final String INGREDIENTS_URI = "/api/ingredients";
}
