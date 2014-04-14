package by.leshiy.db;

public enum Role {

    ADMINISTRATOR("администратор"),

    ABITURIENT("абитуриент"),

    GUEST("гость");

    private String name;

    private Role(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public static Role getRole(int roleNumber) {
	for (Role role : Role.values()) {
	    if (role.ordinal() == roleNumber) {
		return role;
	    }
	}
	return null;
    }
}