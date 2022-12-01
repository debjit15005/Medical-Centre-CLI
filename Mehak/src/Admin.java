package medc;

public class Admin implements Runnable {
	
	private AppData appData;
	Admin(){
		
	}
	
	public void run() {
		while(true) {
			continue;
		}
	}
	
	public void createAndAddNewNotice(Doctor D) {
		synchronized (appData.notices) {
		appData.notices.add(new Notice(D));
		}
	}
	
	public void deleteNoticeNumber(int noticeNum) {
		synchronized (appData.notices) {
		appData.notices.remove(noticeNum - 1);
		}
	}
	
	
}
