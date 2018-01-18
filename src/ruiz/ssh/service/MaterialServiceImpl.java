package ruiz.ssh.service;

import java.util.List;

import ruiz.ssh.dao.MaterialDao;
import ruiz.ssh.model.Material;

public class MaterialServiceImpl implements MaterialService {

	private MaterialDao materialDao;
	
	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	@Override
	public boolean addMaterial(Material material) {
		return materialDao.add(material);
	}

	@Override
	public boolean deleteMaterial(int id) {
		return materialDao.delete(id);
	}

	@Override
	public boolean updateMaterial(Material material) {
		return materialDao.update(material);
	}

	@Override
	public  List<Material>  queryById(int id) {

		return materialDao.queryById(id);
	}

	@Override
	public List<Material> queryAll() {

		return materialDao.getAll();
	}

	@Override
	public List<Material> match(String s) {

		return materialDao.match(s);
	}

	@Override
	public Material tbadd(String name, String type, String detail, int weight, int volume) {
		
		return materialDao.tbadd(name, type, detail, weight, volume);
	}

	@Override
	public boolean tbupdate(int id, String name, String type, String detail, int weight, int volume) {
		
		return materialDao.tbupdate(id, name, type, detail, weight, volume);
	}

}
