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
     * Category manager string.
     */
    public static final String CATEGORY_MANAGER = "Quản lý chuyên mục";
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
     * Serial number string.
     */
    public static final String SERIAL_NUMBER = "Số thứ tự";
    /**
     * Add category string.
     */
    public static final String ADD_CATEGORY = "Thêm chuyên mục";
    /**
     * Id string.
     */
    public static final String ID = "Id";
    /**
     * Category name string.
     */
    public static final String CATEGORY_NAME = "Tên chuyên mục";
    /**
     * Category name error string.
     */
    public static final String CATEGORY_NAME_ERROR = "Vui lòng nhập tên chuyên mục";
    /**
     * Category description string.
     */
    public static final String CATEGORY_DESCRIPTION = "Mô tả chuyên mục";
    /**
     * Category parent string.
     */
    public static final String CATEGORY_PARENT = "Chuyên mục cha";
    /**
     * Category remove string.
     */
    public static final String CATEGORY_REMOVE = "Xóa chuyên mục";
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
     * List link get deal from.
     */
    public static final String[] LIST_LINKS = new String[] {
        "http://51deal.vn/",                // DEAL51           0
        "http://babydeal.vn/",              // BABYDEAL         1
        "http://www.congdongmua.com",       // CONGDONGMUA      2
        "http://www.crazydeal.vn/",         // CRAZYDEAL        3
        "http://cucre.vn",                  // CUCRE            4
        "http://www.cungdep.vn/",           // CUNGDEP          5
        "http://www.cungmua.com/",          // CUNGMUA          6
        "http://cungmuasam.net/",           // CUNGMUASAM       7
        "http://cungsam.com/",              // CUNGSAM          8
        "http://datmua.vn/",                // DATMUA           9
        "http://deal1.vn",                  // DEAL1            10
        "http://deal14.vn/",                // DEAL14           11
        "http://deal18.vn/",                // DEAL18           12
        "http://deal8.vn/",                 // DEAL8            13
        "http://dealmoi.vn",                // DEALMOI          14
        "http://dealshop.vn/",              // DEALSHOP         15
        "http://www.dealsoc.vn/",           // DEALSOC          16
        "http://dealtravel.vn/",            // DEALTRAVEL       17
        "http://dealzone.vn/",              // DEALZONE         18
        "http://diadiemvang.net/",          // DIADIEMVANG      19
        "http://www.dienmay.com/",          // DIENMAY          20
        "http://everyday.vn/",              // EVERYDAY         21
        "http://www.ewinwin.vn/",           // EWINWIN          22
        "http://giagood.vn/",               // GIAGOOD          23
        "http://hoadeal.vn/",               // HOADEAL          24
        "http://hocbong.loidich.com/",      // LOIDICH          25
        "http://www.hoinhapdeal.vn",        // HOINHAPDEAL      26
        "http://hoishopping.com/",          // HOISHOPPING      27
        "http://www.hotdeal.vn",            // HOTDEAL          28
        "http://hottour.vn",                // HOTTOUR          29
        "http://kenhmua.com",               // KENHMUA          30
        "http://muachung.vn",               // MUACHUNG         31
        "http://muamoingay.com",            // MUAMOINGAY       32
        "http://muanhanh.vn/",              // MUANHANH         33
        "http://necdeal.com/",              // NECDEAL          34
        "http://nhanhmua.vn/",              // NHANHMUA         35
        "http://nhommuare.com/",            // NHOMMUARE        36
        "http://nhommuasam.vn",             // NHOMMUASAM       37
        "http://oladeal.vn/",               // OLADEAL          38
        "http://qbata.com/",                // QBATA            39
        "http://remoingay.com/",            // REMOINGAY        40
        "http://retunggiay.vn/",            // RETUNGGIAY       41
        "http://www.runhau.vn/",            // RUNHAU           42
        "http://saigonmua.vn",              // SAIGONMUA        43
        "http://www.sieumua.com/",          // SIEUMUA          44
        "http://smartdeal.vn/",             // SMARTDEAL        45
        "http://sucmanhnhom.com/",          // SUCMANHNHOM      46
        "http://travelpon.com/",            // TRAVELPON        47
        "http://uudaigia.com",              // UUDAIGIA         48
        "http://www.shop360plus.com/",      // SHOP360PLUS      49
        "http://vinadeal.vn/",              // VINADEAL         50
        "http://deal.vivumuasam.com/",      // VIVUMUASAM       51
        "http://vndeal.net/",               // VNDEAL           52
        "http://vndoan.com/",               // VNDOAN           53
        "http://voucherhot.com",            // VOUCHERHOT       54
        "http://windeal.vn/",               // WINDEAL          55
        "https://xdeal.vn/",                // XDEAL            56
        "http://yeah1deal.vn",              // YEAH1DEAL        57
        "http://yesdeal.vn",                // YESDEAL          58
        "http://www.nhommua.com",           // NHOMMUA          59
        "http://www.dealvip.vn/"            // DEALVIP          60
    };
    /**
     * Enum list link id.
     * @version 1.0 27/01/2013
     * @author NguyenPT
     */
    public static enum LIST_LINKS_ID {
        /** http://51deal.vn/. */
        DEAL51,
        /** http://babydeal.vn/. */
        BABYDEAL,
        /** http://www.congdongmua.com. */
        CONGDONGMUA,
        /** http://www.crazydeal.vn/. */
        CRAZYDEAL,
        /** http://cucre.vn. */
        CUCRE,
        /** http://www.cungdep.vn/. */
        CUNGDEP,
        /** http://www.cungmua.com/. */
        CUNGMUA,
        /** http://cungmuasam.net/. */
        CUNGMUASAM,
        /** http://cungsam.com/. */
        CUNGSAM,
        /** http://datmua.vn/. */
        DATMUA,
        /** http://deal1.vn. */
        DEAL1,
        /** http://deal14.vn/. */
        DEAL14,
        /** http://deal18.vn/. */
        DEAL18,
        /** http://deal8.vn/. */
        DEAL8,
        /** http://dealmoi.vn. */
        DEALMOI,
        /** http://dealshop.vn/. */
        DEALSHOP,
        /** http://www.dealsoc.vn/. */
        DEALSOC,
        /** http://dealtravel.vn/. */
        DEALTRAVEL,
        /** http://dealzone.vn/. */
        DEALZONE,
        /** http://diadiemvang.net/. */
        DIADIEMVANG,
        /** http://www.dienmay.com/. */
        DIENMAY,
        /** http://everyday.vn/. */
        EVERYDAY,
        /** http://www.ewinwin.vn/. */
        EWINWIN,
        /** http://giagood.vn/. */
        GIAGOOD,
        /** http://hoadeal.vn/. */
        HOADEAL,
        /** http://hocbong.loidich.com/. */
        LOIDICH,
        /** http://www.hoinhapdeal.vn. */
        HOINHAPDEAL,
        /** http://hoishopping.com/. */
        HOISHOPPING,
        /** http://www.hotdeal.vn. */
        HOTDEAL,
        /** http://hottour.vn. */
        HOTTOUR,
        /** http://kenhmua.com. */
        KENHMUA,
        /** http://muachung.vn. */
        MUACHUNG,
        /** http://muamoingay.com. */
        MUAMOINGAY,
        /** http://muanhanh.vn/. */
        MUANHANH,
        /** http://necdeal.com/. */
        NECDEAL,
        /** http://nhanhmua.vn/. */
        NHANHMUA,
        /** http://nhommuare.com/. */
        NHOMMUARE,
        /** http://nhommuasam.vn. */
        NHOMMUASAM,
        /** http://oladeal.vn/. */
        OLADEAL,
        /** http://qbata.com/. */
        QBATA,
        /** http://remoingay.com/. */
        REMOINGAY,
        /** http://retunggiay.vn/. */
        RETUNGGIAY,
        /** http://www.runhau.vn/. */
        RUNHAU,
        /** http://saigonmua.vn. */
        SAIGONMUA,
        /** http://www.sieumua.com/. */
        SIEUMUA,
        /** http://smartdeal.vn/. */
        SMARTDEAL,
        /** http://sucmanhnhom.com/. */
        SUCMANHNHOM,
        /** http://travelpon.com/. */
        TRAVELPON,
        /** http://uudaigia.com. */
        UUDAIGIA,
        /** http://www.shop360plus.com/. */
        SHOP360PLUS,
        /** http://vinadeal.vn/. */
        VINADEAL,
        /** http://deal.vivumuasam.com/. */
        VIVUMUASAM,
        /** http://vndeal.net/. */
        VNDEAL,
        /** http://vndoan.com/. */
        VNDOAN,
        /** http://voucherhot.com. */
        VOUCHERHOT,
        /** http://windeal.vn/. */
        WINDEAL,
        /** https://xdeal.vn/. */
        XDEAL,
        /** http://yeah1deal.vn. */
        YEAH1DEAL,
        /** http://yesdeal.vn. */
        YESDEAL,
        /** http://www.nhommua.com. */
        NHOMMUA,
        /** http://www.dealvip.vn/. */
        DEALVIP
    };
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
     * Regular expression get category from HotDeal.vn.
     */
    public static final String HOTDEAL_REGEX_CATEGORY = "<div\\s+class=\"breadcrumbs\".*?>"
            + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"
            + ".*?"
        + ".*?</a>"
        + ".*?<img.*?src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>"
        + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"
            + "(.*?)"
        + "</a>"
        + ".*?<img.*?src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>" // 11
        + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"  // 14
            + "(.*?)"                                               // 17
        + "</a>"
        + ".*?<img.*?src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>"
        + ".*?</div>";
    /*public static final String DEALVIP_REGEX1 = "<div\\s+class=\"index_title\".*?>"
                        + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>" // Link 1
                            + "(.*?)"       // Title 4
                        + "</a>"
                    + ".*?</div>";
    public static final String DEALVIP_REGEX2 = "<div\\s+style=\"position:relative\".*?>"
                            + ".*?<span.*?>"
                                + ".*?<img.*?src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>"
                                + "(.*?)"   // Voucher 8
                            + "</span>"
                            + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"
                                + ".*?<img.*?src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>" // ImageLink 12
                            + ".*?</a>"
                        + ".*?</div>";
    public static final String DEALVIP_REGEX3 = "<div\\s+style=\"color:#666; padding:10px 0 0 0; font-size:80%\".*?>"
                        + "(.*?)"       // Description 15
                    + "</div>";
    public static final String DEALVIP_REGEX4 = "<ul\\s+class=\"index_price\".*?>"
                        + ".*?<li.*?>"
                            + "(.*?)"   // Basic price 16
                        + "</li>"
                        + ".*?<li.*?>"
                            + "(.*?)"   // Price 17
                        + "</li>"
                    + ".*?</ul>";
    public static final String DEALVIP_REGEX5 = "<p\\s+class=\"btn-small2 btn-right\".*?>"
                        + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?title=\"(.*?)\".*?>" // Description_2 21
                        + ".*?</a>"
                    + ".*?</p>";*/
    /**
     * DealVip regular expression string.
     */
    public static final String DEALVIP_REGEX = "<div\\s+class=\"index_title\".*?>"
                            + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>" // Link 1
                                + "(.*?)"       // Title 4
                            + "</a>"
                        + ".*?</div>"
                        + ".*?<div\\s+class=\"index_img\"\\s+id=\".*?\".*?>"
                            + ".*?<div\\s+style=\"position:relative\".*?>"
                                + ".*?<span\\s+class=\"trans\"\\s+align=\"center\".*?>"
                                    + ".*?<img\\s+src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>"    // 5
                                    + "(.*?)"   // Voucher 8
                                + "</span>"
                                + ".*?<a\\s+href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?>"
                                    + ".*?<img\\s+src=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?/>" // ImageLink 12
                                + ".*?</a>"
                            + ".*?</div>"
                        + ".*?</div>"
                        + ".*?<div\\s+style=\"color:#666; padding:10px 0 0 0; font-size:80%\".*?>"
                            + "(.*?)"       // Description 15
                        + "</div>"
                        + ".*?<ul\\s+class=\"index_price_s\".*?>"
                            + ".*?<li>"
                                + ".*?"
                            + "</li>"
                            + ".*?<li>"
                                + ".*?"
                            + "</li>"
                        + ".*?</ul>"
                        + ".*?<ul\\s+class=\"index_price\".*?>"
                            + ".*?<li.*?>"
                            + "(.*?)"   // Basic price 16
                        + "</li>"
                        + ".*?<li.*?>"
                            + "(.*?)"   // Price 17
                        + "</li>"
                    + ".*?</ul>"
                    + ".*?<p\\s+class=\"btn-small2 btn-right\".*?>"
                        + ".*?<a.*?href=(\"([^\"]*\")|'[^']*'|([^'\">\\s]+)).*?title=\"(.*?)\".*?>" // Description_2 21
                        + ".*?</a>"
                    + ".*?</p>"
                    + ".*?<ul\\s+class=\"index_note\"\\s+style=\"position:relative\".*?>"
                        + ".*?<li>"
                            + ".*?<p>"
                            + ".*?</p>"
                            + ".*?<span>"
                                + "(.*?)"   // Save 22
                            + "</span>"
                        + ".*?</li>"
                        + ".*?<li>"
                            + ".*?<p>"
                            + ".*?</p>"
                            + ".*?<span>"
                                + "(.*?)"   // Number Buyer 23
                            + "</span>"
                        + ".*?</li>"
                        + ".*?<li>"
                            + ".*?<p>"
                            + ".*?</p>"
                            + ".*?<span>"
                                + "(.*?)"   // Remain Time 24
                            + "</span>"
                        + ".*?</li>"
                    + ".*?</ul>";
    /**
     * Voucher string in DealVip.
     */
    public static final String DEALVIP_GIAOVOUCHER = "Giao Voucher tận nơi";
    /**
     * Timeout to get an address.
     */
    public static final int GET_ADDRESS_TIMEOUT = 5000;
    /**
     * Timeout to get a page.
     */
    public static final int GET_DEAL_PAGE_TIMEOUT = 30000;
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
    /**
     * Voucher string.
     */
    public static final String VOUCHER = "(Giao Voucher)";

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
