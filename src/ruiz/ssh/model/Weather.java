package ruiz.ssh.model;

public class Weather {
	private int id;
    private int num;
    private String date;
    private String w1;
    private String w2;
    private String high;
    private String low;
    private String wind;
    private String direction;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getW1() {
		return w1;
	}
	public void setW1(String w1) {
		this.w1 = w1;
	}
	public String getW2() {
		return w2;
	}
	public void setW2(String w2) {
		this.w2 = w2;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
    
}
