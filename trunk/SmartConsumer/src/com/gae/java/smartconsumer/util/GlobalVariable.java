/**
 * GlobalVariable.java
 * 14 Oct 2012
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.util;

/**
 * Global variable.
 * @version v1.0 - 14/10/2012 - NguyenPT
 * @author NguyenPT
 */
public final class GlobalVariable {
    /**
     * Constructor.
     */
    private GlobalVariable() {
    }
    /**
     * Project name string.
     */
    public static final String PROJECT_NAME = "Smart Consumer";
    /**
     * Hanoi city Id.
     */
    public static final int CITY_HANOI = 22;
    /**
     * Ho Chi Minh city Id.
     */
    public static final int CITY_TPHCM = 29;
    /**
     * Da Nang city Id.
     */
    public static final int CITY_DANANG = 15;
    /**
     * Nha Trang city Id.
     */
    public static final int CITY_NHATRANG = 68;
    /**
     * Hai Phong city Id.
     */
    public static final int CITY_HAIPHONG = 26;
    /**
     * Vung Tau city Id.
     */
    public static final int CITY_VUNGTAU = 67;
    /**
     * Can Tho city Id.
     */
    public static final int CITY_CANTHO = 14;
    /**
     * Maximum try.
     */
    public static final int MAX_TRY = 20;
    /**
     * VND.
     */
    public static final String VND = "VNĐ";
    /**
     * Non breaking space.
     */
    public static final String NON_BREAKING_SPACE = "&nbsp;";

    //----- Static string Vietnamese -------
    /**
     * Status of Deal string.
     */
    public static final String STATUS = "Trạng thái";
    /**
     * Deal list string.
     */
    public static final String DEAL_INFO = "Danh sách Deal";
    /**
     * Deal number string.
     */
    public static final String DEAL_NUMBER = "Số lượng Deal";
    /**
     * Update string.
     */
    public static final String UPDATE = "Cập nhật";
    /**
     * Deal Id string.
     */
    public static final String DEAL_ID = "Mã Deal";
    /**
     * Deal title string.
     */
    public static final String DEAL_TITLE = "Tiêu đề";
    /**
     * Deal description string.
     */
    public static final String DEAL_DESCRIPTION = "Mô tả";
    /**
     * Deal address string.
     */
    public static final String DEAL_ADDRESS = "Địa chỉ";
    /**
     * Deal link string.
     */
    public static final String DEAL_LINK = "Đường dẫn";
    /**
     * Deal price string.
     */
    public static final String DEAL_PRICE = "Giá";
    /**
     * Deal basic price string.
     */
    public static final String DEAL_BASIC_PRICE = "Giá gốc";
    /**
     * Deal save string.
     */
    public static final String DEAL_SAVE = "Tiết kiệm";
    /**
     * Deal number buyer string.
     */
    public static final String DEAL_NUMBER_BUYER = "Số người mua";
    /**
     * Deal remain time string.
     */
    public static final String DEAL_REMAIN_TIME = "Thời gian còn lại";
    /**
     * Deal update time.
     */
    public static final String DEAL_UPDATE_TIME = "Thời gian cập nhật";
    /**
     * Deal remove string.
     */
    public static final String DEAL_REMOVE = "Xóa Deal";
    /**
     * Deal restore string.
     */
    public static final String DEAL_RESTORE = "Phục hồi Deal";
    /**
     * Deal create new string.
     */
    public static final String DEAL_NEW = "Tạo mới";
    /**
     * Deal image string.
     */
    public static final String DEAL_IMAGE = "Hình ảnh";
    /**
     * Deal unit price string.
     */
    public static final String DEAL_UNIT_PRICE = "Đơn giá";
    /**
     * Deal method string.
     */
    public static final String DEAL_METHOD = "Phương thức giao hàng";
    /**
     * Deal method Voucher string.
     */
    public static final String DEAL_METHOD_VOUCHER = "(Giao Voucher)";
    /**
     * Deal method Product string.
     */
    public static final String DEAL_METHOD_PRODUCT = "(Giao Sản phẩm)";
    /**
     * Deal collector string.
     */
    public static final String DEAL_COLLECTOR = "Lọc thông tin Deal từ các website";
    /**
     * Login string.
     */
    public static final String LOGIN = "Đăng nhập";
    /**
     * Logout string.
     */
    public static final String LOGOUT = "Đăng xuất";
    /**
     * Websites string.
     */
    public static final String WEBSITES = "Danh sách website";

    // ----- Website -----
    /**
     * Link 123 do string.
     */
    public static final String LINK_123DO = "http://123do.vn/";
    /**
     * Link Hot deal string.
     */
    public static final String LINK_HOTDEAL = "http://www.hotdeal.vn";
    /**
     * Link Mua chung string.
     */
    public static final String LINK_MUACHUNG = "http://muachung.vn/";
    /**
     * Link Nhommua string.
     */
    public static final String LINK_NHOMMUA = "http://www.nhommua.com";
    /**
     * Link Cungmua string.
     */
    public static final String LINK_CUNGMUA = "http://www.cungmua.com/";
    /**
     * Home page string.
     */
    public static final String HOME_PAGE = "Trang chủ";
    /**
     * View map string.
     */
    public static final String MAP_VIEW = "Xem bản đồ";
    /**
     * Deal manager string.
     */
    public static final String DEAL_MANAGER = "Quản lý Deal";
    /**
     * Search string.
     */
    public static final String SEARCH = "Tìm kiếm";
    /**
     * Price from string.
     */
    public static final String PRICE_FROM = "Giá từ";
    /**
     * Price to string.
     */
    public static final String PRICE_TO = "đến";
    // ------ Regular Expression string -----
    /**
     * HotDeal regular expression string.
     */
    public static final String HOTDEAL_REGEX = "<div\\s+class=\"product\".*?>"
                + ".*?<div\\s+class=\"product_image\".*?>"
                + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"   // Link 1
                    + ".*?<img.*?"
                    + "data-original=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>" // Image link 4
                + ".*?</a>"
            + ".*?</div>"
            + ".*?<div\\s+class=\"product_title\".*?>"
                + ".*?<a.*?>"
                    + "(.*?)"                                               // Title 7
                + "</a>"
                + ".*?<div\\s+class=\"product_type.*?\".*?>"
                    + "(.*?)"                                               // Is voucher 8
                + "</div>"
            + ".*?</div>"
            + ".*?<div\\s+class=\"product_desc\".*?>"
                + "(.*?)"                                                   // Description 9
            + "</div>"
            + ".*?<div\\s+class=\"product_price\".*?>"
                + ".*?<span\\s+class=\"product_sprice\".*?>"
                    + "(.*?)"                                               // Price 10
                + "</span>"
                + ".*?<br.*?/>"
                + ".*?<span\\s+class=\"product_oprice\".*?>"
                    + "(.*?)"                                               // Basic price 11
                + "</span>"
            + "</div>"
            + ".*?<div\\s+class=\"product_box\".*?>"
                + ".*?<div\\s+class=\"product_save_percent\".*?>"
                    + ".*?<span\\s+class=\"title\".*?>"
                    + ".*?</span>"
                    + ".*?<br.*?/>"
                    + ".*?<span\\s+class=\"key\".*?>"
                        + "(.*?)"                                           // Save 12
                    + "</span>"
                + ".*?</div>"
                + ".*?<div\\s+class=\"product_bought\".*?>"
                    + ".*?<span\\s+class=\"title\".*?>"
                    + ".*?</span>"
                    + ".*?<br.*?/>"
                    + ".*?<span\\s+class=\"key\".*?>"
                        + "(.*?)"                                           // Number buyer 13
                    + "</span>"
                + ".*?</div>"
                + ".*?<div\\s+class=\"product_timeout\".*?>"
                    + ".*?<span\\s+class=\"title\".*?>"
                    + ".*?</span>"
                    + ".*?<br.*?/>"
                    + ".*?<span\\s+class=\"key\".*?>"
                        + "(.*?)"                                           // Time out 14
                    + "</span>"
                + ".*?</div>"
            + ".*?</div>"
        + ".*?</div>";                                                      // End div
    /**
     * Regular expression get address type A from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_A = "<div\\s+class=\"cr\".*?>"
            + ".*?<p\\s+class=\"font14 text_bo text_up bob\".*?>"
            + "(.*?)"   // 1 Title
        + "</p>"
        + ".*?<p>"
            + "(.*?)"   // 2 Address
            + "<br.*?/>"
            + "(.*?)"   // 3 Phone
            + "<br.*?/>"
            + "(.*?)"   // Email
        + "</p>"
        + ".*?<p>"
            + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?target=\"_blank\".*?>"
                + "(.*?)" // Website
            + "</a>"
        + ".*?</p>"
    + ".*?</div>";
    /**
     * Regular expression get address type A1 from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_A1 = "<div\\s+class=\"cr\".*?>"
            + ".*?<p\\s+class=\"font14 text_bo text_up bob\".*?>"
                + "(.*?)"   // 1 Title
            + "</p>"
            + ".*?<p>"
                + "(.*?)"   // 2 Address
            + "</p>"
            + ".*?<p>"
                + "(.*?)"   // 3 Phone
            + "</p>"
            + ".*?<p>"
                + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?target=\"_blank\".*?>"
                    + "(.*?)" // Website
                + "</a>"
            + ".*?</p>"
            + "</div>";
    /**
     * Regular expression get address type B from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_B = "<div\\s+class=\"product-location\".*?>"
                + ".*?<h2\\s+style=\"line-height:22px;padding-bottom:10px\".*?>"
                    + "(.*?)"   // 1 Title
                + "</h2>"
                + "<br.*?/>"
                + ".*?<br.*?/>"
                + "<p>"
                    + "(.*?)"   // 2 Location
                + "</p>"
                + "<p>"
                    + "(.*?)"   // 3 Address
                + "</p>"
                + "<p>"
                    + "(.*?)"   // 4 Hot line
                + "</p>"
            + "</div>";
    /**
     * Regular expression get address type B1 from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_B1 = "<div\\s+class=\"product-location\".*?>"
            + ".*?<h2\\s+style=\"line-height:22px;padding-bottom:10px\".*?>"
                + "(.*?)"   // 1 Title
            + "</h2>"
            + ".*?<img.*?src=(\"([^\"]*\")|'[^']*').*?/>"
            + ".*?<br.*?/>"
            + ".*?<br.*?/>"
            + ".*?<p>"
                + "(.*?)"   // 4 Location
                + "<br.*?/>"
                + "(.*?)"   // 5 Address
                + ".*?<br.*?/>"
                + "(.*?)"   // 6 Phone
                + "<br.*?/>"
                + ".*?<br.*?/>"
            + ".*?</p>"
        + ".*?</div>";
    /**
     * Regular expression get address type B2 from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_B2 = "<div\\s+class=\"product-location\".*?>"
            + "<h2\\s+style=\"line-height:22px;padding-bottom:10px\".*?>"
                + "(.*?)"   // 1 Title
            + "</h2>"
            + "<br.*?/>"
            + ".*?<br.*?/>"
            + "<p>"
                + "(.*?)"   // 2 Address
                + "<br.*?/>"
                + "(.*?)"   // 3 Phone
                + "<br.*?/>"
                + "(.*?)"   // 4 Phone
            + "</p>"
            + "<p>"
                + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?target=\"_blank\".*?>"
                    + "<span.*?>"
                        + "(.*?)"   // 5 Website
                    + "</span>"
                + "</a>"
            + "</p>"
        + ".*?</div>";
    /**
     * Regular expression get address type B3 from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_B3 = "<div\\s+class=\"product-location\".*?>"
            + ".*?<h2\\s+style=\"line-height:22px;padding-bottom:10px\".*?>"
                + "(.*?)"   // 1 Title
            + "</h2>"
            + "<br.*?/>"
            + ".*?<br.*?/>"
            + "<p>"
                + "(.*?)"   // 2 Address
            + "</p>"
        + "</div>";
    /**
     * Regular expression get address type B4 from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_ADDRESS_TYPE_B4 = "<div\\s+class=\"product-location\".*?>"
            + ".*?<h2\\s+style=\"line-height:22px;padding-bottom:10px\".*?>"
                + "(.*?)"   // 1 Title
            + "</h2>"
            + ".*?<img.*?src=(\"([^\"]*\")|'[^']*').*?/>"
            + ".*?<br.*?/>"
            + ".*?<br.*?/>"
            + ".*?<p>"
                + "(.*?)"   // 4 Location
            + "</p>"
            + ".*?<p>"
                + "(.*?)"   // 5 Phone
            + "</p>"
            + ".*?<p>"
                + ".*?<strong>"
                + ".*?</strong>"
            + ".*?</p>"
        + ".*?</div>";
    /**
     * Timeout to get an address.
     */
    public static final int GET_ADDRESS_TIMEOUT = 5000;
    /**
     * Timeout to get a page.
     */
    public static final int GET_DEAL_PAGE_TIMEOUT = 300000;
    /**
     * HotDeal default address.
     */
    public static final String HOTDEAL_DEFAULT_ADDRESS = "294 Hòa Bình - Hiệp Tân - Tân Phú - TPHCM";
    /**
     * No address string.
     */
    public static final String NO_ADDRESS = "NO_ADDRESS";
    /**
     * Number of Deals on Home page.
     */
    public static final int DEAL_PER_PAGE_HOME = 30;
    /**
     * Number of Deals on Deal Manager page.
     */
    public static final int DEAL_PER_PAGE_DEALMANAGER = 80;
    /**
     * Previous string.
     */
    public static final String PREVIOUS = "Trang trước";
    /**
     * Next string.
     */
    public static final String NEXT = "Trang sau";
    /**
     * Max of page in paginator.
     */
    public static final int MAX_PAGE = 10;
    /**
     * Date time format use in this system.
     */
    public static final String DATE_TIME_FORMAT = "yyyy.MM.dd.HH.mm.ss";

    //----- Static string USER -------
    /**
     * Nixforest email.
     */
    public static final String NIXFOREST = "nixforest21991920";
    /**
     * Cu Duy Khoa email.
     */
    public static final String DUYKHOA = "dkhoa47";
}
