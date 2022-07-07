package jiangsir.teacherexam.Config;

import java.util.TreeMap;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Scopes.ApplicationScope;

public class AppConfigService {

	public int insert(AppConfig appConfig) throws DataException {
		new AppConfigDAO().truncate();
		ApplicationScope.setAppConfig(appConfig);
		return new AppConfigDAO().insert(appConfig);
	}

	public void update(AppConfig appConfig) throws DataException {
		// try {
		// appConfig.readServerConfig();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		new AppConfigDAO().truncate();
		new AppConfigDAO().insert(appConfig);
		ApplicationScope.setAppConfig(appConfig);
	}

	public void delete(int id) {
		new AppConfigDAO().delete(id);
	}

	public AppConfig getAppConfig() {
		TreeMap<String, Object> fields = new TreeMap<String, Object>();
		for (AppConfig appConfig : new AppConfigDAO().getAppConfigByFields(
				fields, "id DESC", 0)) {
			return appConfig;
		}
		return new AppConfig();
	}

}
