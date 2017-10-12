package tspGeneticAlgorithm;

public class City {

	private int cityId;
	private int xCoordinate;
	private int yCoordinate;
	
	public City(int cityId, int xCoordinate, int yCoordinate) {
		this.cityId = cityId;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public double getDistance(City anotherCity) {
		double distance = 0;
		double diffX = xCoordinate - anotherCity.getxCoordinate();
		double diffY = yCoordinate - anotherCity.getyCoordinate();
		distance = Math.sqrt(diffX * diffX + diffY * diffY);
		return distance;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
		return result;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", xCoordinate=" + xCoordinate
				+ ", yCoordinate=" + yCoordinate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}
}