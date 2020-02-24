package pageUIs;

public class AbstractPageUI {

	//Header
	public static final String HEADER_MY_ACCOUNT_LINK="//a[@class='ico-account']";
	public static final String HEADER_HOME_LINK="//div[@class='header-logo']//a";
	public static final String HEADER_LOGIN_LINK="//a[@class='ico-login']";
	//Footer
	
	public static final String FOOTER_SITE_MAP="//a[contains(text(),'Sitemap')]";
	public static final String FOOTER_MY_ACCOUNT="//a[contains(text(),'My account')]";
	public static final String FOOTER_SHOPING_CART="//a[contains(text(),'Shopping cart')]";
	
	public static final String FOOTER_DYNAMIC_LOCATOR="//div[@class='footer']//a[contains(text(),'%s')]";
	
}
