/*
 * To the class could work as a ZhavaBean, it must conform to the naming
 * conventions of methods, constructors, and behavior:
 * 1. The class must have a constructor without parameters and declared public
 * 2. Class properties must be accessible through the get, set, and other methods
 * 3. The class must be serializable.
 * 4. The class must be overridden methods equals(), hashCode() and toString().
 */
package player;

public class PersonBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    private String name = null;
    private boolean deceased = false;

    /** No-arg constructor */
    public PersonBean() {
    }

    /**
     * Getter for property <code>name</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property <code>name</code>.
     * @param value
     */
    public void setName(final String value) {
        name = value;
    }

    /**
     * Getter for property "deceased"
     * Different syntax for a boolean field (is vs. get)
     */
    public boolean isDeceased() {
        return deceased;
    }

    /**
     * Setter for property <code>deceased</code>.
     * @param value
     */
    public void setDeceased(final boolean value) {
        deceased = value;
    }

 	@Override
	public boolean equals(Object obj) {
 		if (this == obj) {
 			return true;
 		}
 		if (obj == null || getClass() != obj.getClass()) {
 			return false;
 		}
 		PersonBean that = (PersonBean) obj;
 		if (deceased != that.deceased) {
 			return false;
 		}
 		return !(name != null ? !name.equals(that.name) : that.name != null);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (deceased ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PersonBean {" +
			"name='" + name + "'" +
			", deceased=" + deceased + "}";
	}    
}