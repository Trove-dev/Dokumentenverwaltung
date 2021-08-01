package db;

import Verarbeitung.ServiceLocator;

public interface PersistenzIF {
		public void speicher(String dateiname, ServiceLocator sl);
		public ServiceLocator lade(String dateiname);
	}

