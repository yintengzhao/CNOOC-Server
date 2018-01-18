package ruiz.ssh.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {
	public List<T> queryById(int id);
    public boolean add(T obj);
    public boolean update(T obj);
    public boolean delete(int i);
    public List<T> getAll();
    public List<T> match(String s);
    //public List<T> getLatest(int number);
    //public List<T> pagedByHql(int page);
    //public long count();
    //public List<T> findBySql(String sql, String [] variables, Serializable...values);
    //public T getByParameter(String paraName, Serializable para, String symbol);
}
