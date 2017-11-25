package cl.moriahdp.tarbaychile.utils;

//import com.squareup.okhttp.MediaType;

/**
 * Class to manage constant values to whole application
 *
 * @author  Magnet SPA
 * Created 13/3/17
 */

public final class Constant {

    public static final String customFont = "fonts/Lato-Regular.ttf";
    public static final String APP_NAME = "vigia";
    public static final String PATTERN_AT_LEAST_ONE_NUMBER = ".*\\d+.*";
//    public static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    public static final String USER_AGENT = "User-Agent";
    public static final String USER_AUTHORIZATION = "Authorization";

    public static final int CATEGORY_MUNICIPAL_INFORMATION_ID = 1;
    public static final int CATEGORY_ROAD_PROBLEMS_ID = 2;
    public static final int CATEGORY_PUBLIC_PLACE_ID = 3;
    public static final int CATEGORY_ENVIRONMENT_ID = 4;
    public static final int CATEGORY_POLLUTION_ID = 5;
    public static final int USER_ADMIN = 1;
    public static final int USER_MUNICIPAL_OFFICIAL = 2;
    public static final int USER_NEIGHBORHOOD_LEADER = 3;
    public static final int USER_CITIZEN = 4;
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_PICK_PHOTO = 2;
    public static final int REPORT_ROFILE_WIDTH = 400;
    public static final int REPORT_PROFILE_HEIGHT = 400;

    public static final String USER_EMAIL = "email";
    public static final String USER_FULL_NAME = "full_name";
    public static final String USER_COUNTY = "county";
    public static final String USER_COVERAGE_RADIUS = "coverage_radius";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_AVATAR = "avatar";
    public static final String USER_AUTH_TOKEN = "auth_token";
    public static final String USER_PASSWORD = "password";
    public static final String USER_GROUP = "group";
    public static final int USER_PASSWORD_MIN_LENGTH = 6;

    public static final String REPORT_TITLE = "title";
    public static final String REPORT_LOCATION = "location";
    public static final String REPORT_LONGITUDE = "longitude";
    public static final String REPORT_LATITUDE = "latitude";
    public static final String REPORT_DESCRIPTION = "description";
    public static final String REPORT_CATEGORY = "category";
    public static final String REPORT_IMAGE = "image";
    public static final int REPORT_UPLOAD_SUCCESSFUL = 1;
    public static final int REPORT_UPLOAD_ERROR = 2;
    public static final int PROFILE_UPDATE_SUCCESSFUL = 3;
    public static final int PROFILE_UPDATE_ERROR = 4;
    public static final int PROFILE_UPDATE_PASSWORD_SUCCESSFUL = 5;
    public static final int PROFILE_UPDATE_PASSWORD_ERROR = 6;
    public static final int REQUEST_PERMISSION_CODE = 1;
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String MAP_FILTER_TITLE_INITIAL = "FILTRAR";
    public static final String MAP_FILTER_TITLE_CLOSE = "OCULTAR";

}