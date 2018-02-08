package ruiz.ssh.service;

import java.util.List;

import ruiz.ssh.dao.ScheduleDao;
import ruiz.ssh.model.Schedule;

public class ScheduleServiceImpl implements ScheduleService {

	private ScheduleDao scheduleDao;
	
	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public boolean addSchedule(Schedule sch) {

		return scheduleDao.add(sch);
	}

	@Override
	public boolean deleteSchedule(int id) {

		return scheduleDao.delete(id);
	}

	@Override
	public boolean updateSchedule(Schedule sch) {

		return scheduleDao.update(sch);
	}

	@Override
	public Schedule queryById(int id) {

		return scheduleDao.queryById(id);
	}

	@Override
	public List<Schedule> queryAll() {

		return scheduleDao.getAll();
	}

	@Override
	public List<Schedule> match(String s) {

		return scheduleDao.match(s);
	}

	@Override
	public Schedule tbadd(int id, String name, String info, String note, String userid, String starttime,
			String endtime) {

		return null;
	}

	@Override
	public boolean tbupdate(int id, String name, String info, String note, String userid, String starttime,
			String endtime) {

		return false;
	}

}
