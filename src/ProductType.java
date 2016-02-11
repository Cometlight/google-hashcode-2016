/**
 * Created by Zopo on 11.02.2016.
 */
public class ProductType {
    int _weight;
    int _id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + _weight;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (_id != other._id)
			return false;
		if (_weight != other._weight)
			return false;
		return true;
	}
    
    
}
